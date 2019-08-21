package ui;

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
		outer : while (true) {
			switch (menu()) {
			case 1:
				registerMovie();
				break;
			case 2:
				modifyMovie();
				break;
			case 0: break outer;
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
		int result = movieDAO.insertMovie(movieVO);
		int movieInning = CommUtil.getInt("영화의 회차수를 입력하세요 :");
		for ( int i = 1; i<= movieInning; i++) {
			
			System.out.printf("%d회차", i);
			String movieTime = CommUtil.getStr("의 시간을 입력하세요 : ");
			System.out.printf("%d회차", i);
			String theaterName = CommUtil.getStr("의 상영관을 입력하세요 :");
			
			TheaterVO theaterVO = theaterDAO.selectOneTheater(theaterName);
			
			InningVO inningVO = new InningVO();
			inningVO.setMovieInning(i);
			inningVO.setMovieNo(movieVO.getMovieNo());
			inningVO.setMovieTime(movieTime);
			inningVO.setTheaterNo(theaterVO.getTheaterNo());
			
			
			result += inningDAO.insertInning(inningVO);
					
					
					
					
					
					
					
//					.insertMovie(movieTitle, movieDirector,movieActor,viewingGrade,
//					movieInning,movieTime, theaterName);
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
		outer2 : while(true) {
		List<MovieVO> list = movieDAO.selectMovie();
		int no = list.size();
		System.out.println("현재 상영중인 영화는 " + list.size() + "개 입니다. ");
		System.out.println("------------------------------");
		System.out.println("제목\t감독\t배우\t등급");
		System.out.println("------------------------------");
		for (MovieVO movie : list) {
			System.out.printf("%3d\t%-30s%-15s%-5s%-10s", no--, movie.getMovieTitle(),
					movie.getMovieDirector(), movie.getMovieActor(), movie.getViewingGrade());
			System.out.println();
		}
		
		switch (modiMenu()) {
		case 1: modiMovie(); break;
		case 2: deleteMovie(); break;
		case 0: break outer2;
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
			
		int num = movieDAO.updateMovie(vo);
		if (num == 0) {
			session.rollback();
			System.out.println();
			System.out.println("중복된 이름의 영화가 있습니다.");
			return;
		}
		session.commit();
		System.out.println("영화 수정이 완료되었습니다.");
		
	}
	
	public void deleteMovie() {
		String movieTitle = CommUtil.getStr("삭제할 영화제목을 입력하세요 : ");
		try {
			MovieVO vo = movieDAO.selectOneMovie(movieTitle);
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
			if(e.getMessage().contains("child record found")) {
				session.rollback();
				System.out.println("예매정보가 있는 영화입니다. ");
				return;
			}
		}
		
	}
	
	

}
