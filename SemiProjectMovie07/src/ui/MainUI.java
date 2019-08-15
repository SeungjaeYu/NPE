package ui;


import util.CommUtil;

public class MainUI {


	UserUI userUI = new UserUI();
	TheaterUI theaterUI = new TheaterUI();
	
	public void service() {
		System.out.println("영화예매 프로그램");
		while (true) {
			switch (menu()) {
			
			
			case 1: if (!userUI.loginChk) userUI.loginUser(); else userUI.logout();	break;
					
			case 2: if (userUI.loginChk && userUI.adminChk) userUI.selectUserList(); else if (!userUI.loginChk) userUI.join();  else userUI.selectUser();	break;
			
			//case 3: if (!loginChk) findUser();    /*else writeEMail();*/     break;
			case 4 : if (userUI.loginChk && userUI.adminChk) theaterUI.theater();  break;
			case 0: quit();

			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");

				break;
			}
		}
		
	}
	
	

	private int menu() {
		if (!userUI.loginChk) {
			System.out.println("--------------------------------");
			System.out.println("1. 로그인");
			System.out.println("2. 화원가입");
			System.out.println("3. 영화조회");
			System.out.println("0. 종료");
			System.out.println("--------------------------------");
		} else if (userUI.loginChk && userUI.adminChk) {
			System.out.println("--------------------------------");
			System.out.println("1. 로그아웃");
			System.out.println("2. 회원정보");
			System.out.println("3. 영화관리");
			System.out.println("4. 상영관관리");
			System.out.println("0. 종료");
			System.out.println("--------------------------------");
		} else { 
			System.out.println("--------------------------------");
			System.out.println("1. 로그아웃");
			System.out.println("2. 회원정보");
			System.out.println("3. 영화조회");
			System.out.println("4. 예매조회");
			System.out.println("0. 종료");
			System.out.println("--------------------------------");
		}
		
		return CommUtil.getInt("원하시는 서비스 번호를 입력해주세요 : ");
		
	}
	
	
	private void quit() {
		System.out.println("영화예매 시스템이 종료되었습니다.");
		System.exit(0);
	}
	
	
	
	/*
	private void findUser() {
		user.findUser();
	}
	*/
}
