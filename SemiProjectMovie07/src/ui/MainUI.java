package ui;

import dao.UserDAO;
import util.CommUtil;

public class MainUI extends CommUtil {

	UserDAO userDao = new UserDAO();
	
	UserUI user = new UserUI(userDao);
	
	
	String userId = "";
	
	boolean loginChk = false;
	
	public void service() {
		System.out.println("영화예매 프로그램");
		while (true) {
			switch (menu()) {
			
			
			case 1: if (!loginChk) joinUser();    /*else receiveEMail();*/ 	    break;
			case 2: if (!loginChk) loginUser();   /*else sendEMail();*/	break;
			case 3: if (!loginChk) findUser();    /*else writeEMail();*/     break;
			case 4: if (!loginChk) quit();    	 /*else recycleEMail(); */  break;
			case 5: if (loginChk) {
										logout();
										break; 
								  }

			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
				break;
			}
		}
		
	}
	
	private int menu() {
		if (!loginChk) {
			System.out.println("□□□□□□□□□□□□□□□□□□□");
			System.out.println("(1) 회원가입");
			System.out.println("(2) 로그인");
			System.out.println("(3) 비밀번호찾기");
			System.out.println("(4) 종료");
			System.out.println("□□□□□□□□□□□□□□□□□□□");
		} else {
//			System.out.println("--------------------------------");
//			System.out.println("1. 받은메일함");
//			System.out.println("2. 보낸메일함");
//			System.out.println("3. 메일쓰기");
//			System.out.println("4. 휴지통");
//			System.out.println("5. 로그아웃");
//			System.out.println("--------------------------------");
		}
		
		return getInt("원하시는 서비스 번호를 입력해주세요 : ");
		
	}
	
	
	private void quit() {
		System.out.println("영화예매 시스템이 종료되었습니다.");
		System.exit(0);
	}
	
	/**
	 *  로그인 관련 처리 메소드
	 * 
	 */
	
	
	private void loginUser() {
		userId = user.loginUser();
		if (!userId.equals("")) loginChk = true;
	}
	
	
	private void logout() {
		loginChk = false;
		userId = "";
		System.out.println("안전하게 로그아웃 되었습니다.");
	}
	
	
	/**
	 * 
	 *  유저정보 관련 처리 메소드
	 */
	
	
	private void joinUser() {
		user.join();
	}
	
	private void findUser() {
		user.findUser();
	}
	
}
