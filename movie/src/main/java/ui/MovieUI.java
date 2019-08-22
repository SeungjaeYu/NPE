package ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.InningDAO;
import dao.MovieDAO;
import dao.ReservationDAO;
import util.CommUtil;
import vo.MovieVO;

public class MovieUI {

	private MovieDAO movieDAO;
	private InningUI inningUI = new InningUI();

	public MovieUI() {
		SqlSession session = db.MyAppSqlConfig.getSqlSessionInstance();
		movieDAO = session.getMapper(MovieDAO.class);
	}

	/**
	 * 영화 전체 조회
	 * @param userNo
	 */
	public void selectMovieList(int userNo) {

		movieOuter: while (true) {
			CommUtil.clear();
			List<MovieVO> list = movieDAO.selectMovie();

			int no = list.size();
			System.out.println("현재 상영중인 영화는 " + list.size() + "개 입니다. ");
			System.out.println("------------------------------");
			System.out.println("   제목\t\t감독\t\t배우\t등급");
			System.out.println("------------------------------");
			if (list.isEmpty()) {
				System.out.println("상영중인 영화가 없습니다.");
				CommUtil.clear(2);
				return;
			}
			for (MovieVO movie : list) {
				System.out.printf("%3d %-10s%-10s%-10s%-10s", no--, movie.getMovieTitle(), movie.getMovieDirector(),
						movie.getMovieActor(), movie.getViewingGrade());
				System.out.println();
			}
			System.out.println("------------------------------");
			System.out.println("  0. 이전");
			System.out.println("------------------------------");

			int userNum = CommUtil.getInt("상세 조회할 영화 번호를 입력하세요. : ");
			
			if (userNum == 0) {
				CommUtil.clear();
				break movieOuter;
			}

			while (userNum > list.size() || userNum < 0) {
				System.out.println("잘못 된 번호 입니다. 다시입력하세요. ");
				userNum = CommUtil.getInt("상세 조회할 영화 번호를 입력하세요. : ");
			}
			MovieVO vo = list.get(list.size() - userNum);
			CommUtil.clear();
			inningUI.selectInningList(vo.getMovieNo(), userNo);
			
		}

	}
}
