package ui;

import java.util.ArrayList;
import java.util.List;

import dao.MovieDAO;
import util.CommUtil;
import vo.MovieVO;
public class MovieUI {
	public void service() {
		while (true) {
			
			List<MovieVO> list = MovieDAO.selectMovie();
			
			int no = list.size();
			System.out.println("현재 상영중인 영화는 " + list.size() + "개 입니다. ");
			System.out.println("------------------------------");
			System.out.println("제목\t배우\t감독\t등급");
			System.out.println("------------------------------");
			for (MovieVO movie: list) {
				System.out.printf(
						"%3d\t%-30s\t%-15s\t%-5s", 
						no--,
						movie.getMovieTitle(), 
						movie.getMovieDirector(),
						movie.getMovieActor(),
						movie.getViewingGrade());
				System.out.println();
			}
			System.out.println("------------------------------");
			System.out.println("0. 이전");
			System.out.println("------------------------------");
			int userNum = CommUtil.getInt("메뉴 중 상세 조회할 글 번호나 처리할 항목을 선택하세요 : ");
			
			if (userNum == 0) break;
			
			
		}
	}
	public static void main(String[] args) {
		new MovieUI().service();
	}
}
