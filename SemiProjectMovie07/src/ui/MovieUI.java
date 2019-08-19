package ui;

import java.util.ArrayList;
import java.util.List;

import dao.MovieDAO;
import util.CommUtil;
import vo.MovieVO;
public class MovieUI {
	//DAO 객체생성
	
	// 영화 등록  C
	// 영화 삭제 D
	// 영화 수정 U
	
	// 영화리스트 조회 R
	MovieDAO movieDAO = new MovieDAO();
	InningUI inningUI = new InningUI();
	
	public void selectMovieList(int userNo) {
		
		movieOuter : 
		while (true) {
			
			List<MovieVO> list = movieDAO.selectMovie();
			
			int no = list.size();
			System.out.println("현재 상영중인 영화는 " + list.size() + "개 입니다. ");
			System.out.println("------------------------------");
			System.out.println("제목\t배우\t감독\t등급");
			System.out.println("------------------------------");
			for (MovieVO movie : list) {
				System.out.printf(
						"%3d\t%-30s\t%-15s\t%-5s\t%-10s", 
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
			
			int userNum = CommUtil.getInt("메뉴 중 상세 조회할 글 번호나 처리할 항목을 선택하세요 : ");
			
			if (userNum == 0) break movieOuter;
			
			while (userNum > list.size() || userNum < 0) {
				System.out.println("잘못 된 메뉴번호 입니다. 다시입력하세요. ");
				userNum = CommUtil.getInt("메뉴 중 상세 조회할 글 번호나 처리할 항목을 선택하세요 : ");
			}
			MovieVO vo = null;
			for (MovieVO movieVO : list) {
				if (list.size() - userNum + 1 != movieVO.getTempNo()) continue;
				vo = movieVO;
			}
			if (vo != null) {
				inningUI.selectInningList(vo.getMovieNo(), userNo);
				
			}
			
		}
		
	}
}
 