package ui;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.InningDAO;
import dao.MovieDAO;
import dao.TheaterDAO;
import util.CommUtil;
import vo.InningVO;
import vo.MovieVO;
import vo.TheaterVO;

public class MovieUIAdmin {

	SqlSession session;

	private MovieDAO movieDAO;
	private TheaterDAO theaterDAO;
	private InningDAO inningDAO;

	public MovieUIAdmin() {
		session = db.MyAppSqlConfig.getSqlSessionInstance();
		movieDAO = session.getMapper(MovieDAO.class);
		theaterDAO = session.getMapper(TheaterDAO.class);
		inningDAO = session.getMapper(InningDAO.class);
	}

	public void movie() {
		outer: while (true) {
			switch (menu()) {
			case 1:
				registerMovie();
				break;
			case 2:
				modifyMovie();
				break;
			case 0:
				break outer;
			default:
				System.out.println("잘못된 번호 입니다.");
				System.out.println("다시 선택해 주세요.");

			}
		}
	}

	int menu() {
		System.out.println("------------------------------");
		System.out.println("  1. 영화등록");
		System.out.println("  2. 영화수정 및 삭제");
		System.out.println("  0. 이전");
		System.out.println("------------------------------");
		return CommUtil.getInt("원하는 서비스 번호를 입력해주세요 : ");
	}

	/**
	 * 영화 등록
	 */
	public void registerMovie() {

		String movieTitle = CommUtil.getStr("영화 제목을 입력하세요 :");
		String movieDirector = CommUtil.getStr("영화 감독을 입력하세요 :");
		String movieActor = CommUtil.getStr("배우를 입력하세요 :");
		String viewingGrade = CommUtil.getStr("영화 등급을 입력하세요 :");

		MovieVO movieVO = new MovieVO();
		movieVO.setMovieTitle(movieTitle);
		movieVO.setMovieDirector(movieDirector);
		movieVO.setMovieActor(movieActor);
		movieVO.setViewingGrade(viewingGrade);
		int result = 0;
		try{
			 result = movieDAO.insertMovie(movieVO);
		}catch(Exception e) {
			session.rollback();
			System.out.println();
			System.out.println("중복된 이름의 영화가 있습니다.");
			return;
		}
		int movieInning = CommUtil.getInt("영화의 회차수를 입력하세요 :");

		while (movieInning <= 0) {
			System.out.println("0보다 큰 회차를 입력해야 합니다.");
			movieInning = CommUtil.getInt("영화의 회차수를 입력하세요 :");
		}

		for (int i = 1; i <= movieInning; i++) {
			System.out.println("--------------------------------");
			int num = 1;
			for (String s : CommUtil.movieTimeList) {
				System.out.printf("%d\t%s%n", num++, s);
			}
			System.out.println("--------------------------------");
			System.out.printf("%d회차", i);
			int mvt = CommUtil.getInt("의 시간을 선택하세요 : ");
			String movieTime = null;
			 while (mvt <= 0  || mvt > CommUtil.movieTimeList.length ) {
				 System.out.println("잘못된 번호입니다. 다시 입력해주세요. ");
				 System.out.printf("%d회차", i);
				 mvt = CommUtil.getInt("의 시간을 선택하세요 : ");
			 }
			 movieTime = CommUtil.movieTimeList[mvt - 1];

			System.out.printf("%d회차", i);
			String theaterName = CommUtil.getStr("의 상영관을 입력하세요 :");
			TheaterVO theaterVO = theaterDAO.selectOneTheater(theaterName);
			while (theaterVO == null) {
				System.out.println("잘못된 상영관이름입니다. 다시 입력해주세요. ");
				System.out.printf("%d회차", i);
				theaterName = CommUtil.getStr("의 상영관을 입력하세요 :");
				theaterVO = theaterDAO.selectOneTheater(theaterName);
			}
			

			InningVO inningVO = new InningVO();
			inningVO.setMovieInning(i);
			inningVO.setMovieNo(movieVO.getMovieNo());
			inningVO.setMovieTime(movieTime);
			inningVO.setTheaterNo(theaterVO.getTheaterNo());
			try {
				result += inningDAO.insertInning(inningVO);
				
			}catch(Exception e) {
					System.out.println(theaterName+"의"+ movieTime+"에 상영정보가 존재합니다.");
					i--;
					continue;
			}
			

		}
		
		if (movieInning + 1 != result) {
			session.rollback();
			System.out.println("영화 등록에 실패했습니다. 영화 정보를 확인해주세요.");
			return;
		}
		session.commit();
		System.out.println("영화가 등록되었습니다.");

	}

	/**
	 * 영화 수정 및 삭제
	 */
	int modiMenu() {
		System.out.println("-----------------");
		System.out.println("1. 영화 수정");
		System.out.println("2. 영화 삭제");
		System.out.println("0. 이전");
		System.out.println("-----------------");
		return CommUtil.getInt("원하는 서비스 번호를 입력해주세요 : ");
	}

	public void modifyMovie() {
		outer2: while (true) {
			List<MovieVO> list = movieDAO.selectMovie();
			int no = list.size();
			System.out.println("현재 상영중인 영화는 " + list.size() + "개 입니다. ");
			System.out.println("------------------------------");
			System.out.println("   제목\t\t감독\t\t배우\t등급");
			System.out.println("------------------------------");
			if (list.isEmpty()) {
				System.out.println("상영중인 영화가 없습니다.");
				return;
			}
			for (MovieVO movie : list) {
				System.out.printf("%3d %-10s%-10s%-10s%-10s", no--, movie.getMovieTitle(), movie.getMovieDirector(),
						movie.getMovieActor(), movie.getViewingGrade());
				System.out.println();
			}

			switch (modiMenu()) {
			case 1:
				modiMovie();
				break;
			case 2:
				deleteMovie();
				break;
			case 0:
				break outer2;
			default:
				System.out.println("잘못된 메뉴번호 입니다.");
				System.out.println("다시 선택해 주세요.");

			}
		}
	}

	/**
	 * 영화 수정
	 */
	public void modiMovie() {
		String originalTitle = CommUtil.getStr("수정할 영화의 제목을 입력하세요 : ");
		MovieVO vo = movieDAO.selectOneMovie(originalTitle);
		if (vo == null) {
			System.out.println("해당하는 영화가 존재하지 않습니다.");
			return;
		}

		String movieTitle = CommUtil.getStr("수정할 제목을 입력하세요 :");
		String movieDirector = CommUtil.getStr("수정할 감독의 이름을 입력하세요 :");
		String movieActor = CommUtil.getStr("수정할 배우의 이름을 입력하세요 :");
		String viewingGrade = CommUtil.getStr("수정할 영화등급을 입력하세요 :");
		
		vo.setMovieTitle(movieTitle);
		vo.setMovieDirector(movieDirector);
		vo.setMovieActor(movieActor);
		vo.setViewingGrade(viewingGrade);
		try{
			 movieDAO.updateMovie(vo);
		}catch(Exception e) {
			session.rollback();
			System.out.println();
			System.out.println("중복된 이름의 영화가 있습니다.");
			return;
		}
			
		session.commit();
		System.out.println("영화 수정이 완료되었습니다.");

	}

	/**
	 * 영화 삭제
	 */
	public void deleteMovie() {
		String movieTitle = CommUtil.getStr("삭제할 영화제목을 입력하세요 : ");
		try {
			MovieVO vo = movieDAO.selectOneMovie(movieTitle);
			if (vo == null) {
				System.out.println("해당하는 영화가 존재하지 않습니다.");
				return;
			}
			int result = inningDAO.deleteInnning(vo.getMovieNo());
			result += movieDAO.deleteMovie(movieTitle);
			if (result >= 2) {
				session.commit();
				System.out.println();
				System.out.println("영화 삭제가 완료되었습니다.");
				return;
			}
			session.rollback();
			System.out.println("해당 영화가 존재하지 않습니다.");

		} catch (Exception e) {
			if (e.getMessage().contains("child record found")) {
				session.rollback();
				System.out.println("예매정보가 있는 영화입니다. ");
				return;
			}
		}

	}

}
