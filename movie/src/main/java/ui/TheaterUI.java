package ui;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import dao.MovieDAO;
import dao.TheaterDAO;
import util.CommUtil;
import vo.TheaterVO;

public class TheaterUI {
	
	SqlSession session;
	
	private TheaterDAO theaterDAO;
	
	public TheaterUI() {
		session = db.MyAppSqlConfig.getSqlSessionInstance();
		theaterDAO = session.getMapper(TheaterDAO.class);
	}
	

	/**
	 * 상영관 등록
	 */
	public void registerTheater() {
		TheaterVO vo = new TheaterVO();

		String theaterName = CommUtil.getStr("상영관 이름을 입력하세요 : ");
		int seatRow = CommUtil.getInt("상영관의 행을 입력하세요 : ");
		int seatCol = CommUtil.getInt("상영관의 열을 입력하세요 : ");
		vo.setTheaterName(theaterName);
		vo.setSeatRow(seatRow);
		vo.setSeatCol(seatCol);

		int no = theaterDAO.insertTheater(vo);
		if (no == 0) {
			session.rollback();
			System.out.println();
			System.out.println("중복된 이름의 상영관이 있습니다.");
			return;
		}
		System.out.println();
		session.commit();
		System.out.println("상영관이 등록되었습니다.");
		
	}

	/**
	 * 상영관 수정 및 삭제 메뉴
	 */
	int modiMenu() {
		System.out.println("-----------------");
		System.out.println("1. 상영관 수정");
		System.out.println("2. 상영관 삭제");
		System.out.println("0. 이전");
		System.out.println("-----------------");
		return CommUtil.getInt("원하시는 서비스 번호를 입력해주세요 : ");
	}
	public void modifyTheater() {
		outer: while (true) {
			List<TheaterVO> list = theaterDAO.selectTheaterList();
			for (TheaterVO vo : list) {
				System.out.printf("%s\t %d행\t %d열\n", vo.getTheaterName(), vo.getSeatRow(), vo.getSeatCol());
			}
			switch (modiMenu()) {
			case 1:
				modiTheater();
				break;
			case 2:
				deleteTheater();
				break;
			case 0:
				break outer;
			default:
				System.out.println("잘못된 메뉴번호 입니다.");
				System.out.println("다시 선택해 주세요.");
			}
		}
	}

	/**
	 * 상영관 수정
	 */
	public void modiTheater() {
		String originalName = CommUtil.getStr("수정할 상영관이름을 입력하세요 : ");
		
		TheaterVO vo = theaterDAO.selectOneTheater(originalName);
		if (vo == null) {
			System.out.println("해당하는 상영관이 존재하지 않습니다.");
			return;
		}

		String theaterName = CommUtil.getStr("상영관의 새로운 이름을 입력하세요 : ");
		int seatRow = CommUtil.getInt("상영관의 행을 입력하세요 : ");
		int seatCol = CommUtil.getInt("상영관의 열을 입력하세요 : ");
		vo.setTheaterName(theaterName);
		vo.setSeatRow(seatRow);
		vo.setSeatCol(seatCol);

		int no = theaterDAO.updateTheater(vo);
		if (no == 0) {
			session.rollback();
			System.out.println();
			System.out.println("중복된 이름의 상영관이 있습니다.");
			return;
		}
		session.commit();
		System.out.println("상영관 수정이 완료되었습니다.");
	}

	/**
	 * 상영관 삭제
	 */
	public void deleteTheater() {
		String theaterName = CommUtil.getStr("삭제할 상영관이름을 입력하세요 : ");
		try {
			int result = theaterDAO.deleteTheater(theaterName);
			// System.out.println("result : " + result);
			if (result == 1) {
				session.commit();
				System.out.println();
				System.out.println("상영관 삭제가 완료되었습니다.");
				return;
			} 
			session.rollback();
			System.out.println("해당 상영관이 존재하지 않습니다.");
			
		} catch (Exception e) {
			if(e.getMessage().contains("child record found")) {
				session.rollback();
				System.out.println("예매정보가 있는 상영관입니다. ");
				return;
			}
		}
		

	}

	/**
	 * 상영관 관리
	 * @return
	 */
	int menu() {
		System.out.println("-----------------");
		System.out.println("1. 상영관 등록");
		System.out.println("2. 상영관 수정 및 삭제");
		System.out.println("0. 이전");
		System.out.println("-----------------");
		return CommUtil.getInt("원하시는 서비스 번호를 입력해주세요 : ");
	}

	public void theater() {
		outer2: while (true) {
			switch (menu()) {
			case 1:
				registerTheater();
				break;
			case 2:
				modifyTheater();
				break;
			case 0:
				// back();
				break outer2;
			default:
				System.out.println("잘못된 메뉴번호 입니다.");
				System.out.println("다시 선택해 주세요.");
			}
		}
	}
	/* 기능 테스트 메인
	 * public static void main(String[] args) { new TheaterUI().theater(); }
	 */

}
