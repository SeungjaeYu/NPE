package ui;

import dao.UserDAO;
import util.CommUtil;
import vo.UserVO;

public class MainUI extends CommUtil {

	UserDAO userDao = new UserDAO();
	
	UserUI user = new UserUI(userDao);
	
	UserVO userVO = null;
	
	String userId = "";
	
	boolean loginChk = false;
	
	public void service() {
		System.out.println("영화예매 프로그램");
		while (true) {
			switch (menu()) {
			
			
			case 1: if (!loginChk) loginUser();    else logout();    break;
			case 2: if (!loginChk) joinUser();   else selectUser();      	break;
			//case 3: if (!loginChk) findUser();    /*else writeEMail();*/     break;
			case 4: if (!loginChk) quit();    	 /*else recycleEMail(); */  break;
			case 5: if (loginChk) {
										logout();
										break; 
								  }
			case 0: quit();

			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
				break;
			}
		}
		
	}
	
	

	private int menu() {
		if (!loginChk) {
			System.out.println("□□□□□□□□□□□□□□□□□□□");
			System.out.println("1. 로그인");
			System.out.println("2. 화원가입");
			System.out.println("3. 영화조회");
			System.out.println("0. 종료");
			System.out.println("□□□□□□□□□□□□□□□□□□□");
		} else {
			System.out.println("--------------------------------");
			System.out.println("1. 로그아웃");
			System.out.println("2. 회원정보");
			System.out.println("3. 영화조회");
			System.out.println("4. 예매조회");
			System.out.println("0. 종료");
			System.out.println("--------------------------------");
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
	private void selectUser() {
		System.out.println("-------------------------------------");
		System.out.println("아이디 : " + userVO.getUserId());
		System.out.println("이메일 : " + userVO.getUserEmail());
		System.out.println("가입일 : " + userVO.getRegDate());
		System.out.println("영화예매수 : " + userVO.getReservCnt());
		System.out.println("-------------------------------------");
	}
	
	private void loginUser() {
		userVO = user.loginUser();
		if (!userVO.getUserId().equals("")) loginChk = true;
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
	/*
	private void findUser() {
		user.findUser();
	}
	*/
}
