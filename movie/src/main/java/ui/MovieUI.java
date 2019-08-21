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
	//DAO 객체생성
	
	// 영화 등록  C
	// 영화 삭제 D
	// 영화 수정 U
	
	// 영화리스트 조회 R
	
	
	private MovieDAO movieDAO;
	private InningUI inningUI = new InningUI();
//	private ReservSeatUI reservSeatUI = new ReservSeatUI();
	
	public MovieUI() {
		SqlSession session = db.MyAppSqlConfig.getSqlSessionInstance();
		movieDAO = session.getMapper(MovieDAO.class);
	}
	
	
	
	
	
	
	
	public void selectMovieList(int userNo) {
		
		movieOuter : 
		while (true) {
			
			List<MovieVO> list = movieDAO.selectMovie();
			
			int no = list.size();
			System.out.println("현재 상영중인 영화는 " + list.size() + "개 입니다. ");
			System.out.println("------------------------------");
			System.out.println("제목\t배우\t감독\t등급");
			System.out.println("------------------------------");
			if (list.isEmpty()) {
				System.out.println("상영중인 영화가 없습니다.");
				return;
			}
			for (MovieVO movie : list) {
				System.out.printf(
						"%3d\t%-30s%-15s%-5s%-10s", 
						no--,
						movie.getMovieTitle(), 
						movie.getMovieDirector(),
						movie.getMovieActor(),
						movie.getViewingGrade());
				System.out.println();
			}
			System.out.println("------------------------------");
			System.out.println("  0. 이전");
			System.out.println("------------------------------");
			
			int userNum = CommUtil.getInt("상세 조회할 영화 번호를 입력하세요. : ");
			
			if (userNum == 0) break movieOuter;
			
			while (userNum > list.size() || userNum < 0) {
				System.out.println("잘못 된 번호 입니다. 다시입력하세요. ");
				userNum = CommUtil.getInt("상세 조회할 영화 번호를 입력하세요. : ");
			}
			MovieVO vo = list.get(list.size() - userNum);
			
			inningUI.selectInningList(vo.getMovieNo(), userNo);

			
		}
		
	}
}
 