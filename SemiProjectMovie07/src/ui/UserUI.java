package ui;


import dao.UserDAO;
import util.CommUtil;
import vo.UserVO;

public class UserUI {
	
	
	
	boolean loginChk = false;
	UserVO vo = null;
	
	UserDAO dao = new UserDAO();
	
	
	
	public void join() {
		UserVO userVO = new UserVO();
		String userId = CommUtil.getStr("아이디를 입력하세요 : ");
		String password = CommUtil.getStr("비밀번호를 입력하세요 : ");
		String userEmail = CommUtil.getStr("이메일 주소를 입력하세요 : ");
		
		userVO.setUserId(userId);
		userVO.setPassword(password);
		userVO.setUserEmail(userEmail);
		int no = dao.insertUser(userVO);
		
		if (no == 0) {
			System.out.println("============================");
			System.out.println("중복된 아이디의 회원이 있습니다!");
			return;
		}
		System.out.println("============================");
		System.out.println("회원가입이 완료되었습니다.");
	}
	
	
	
	
	/*
	public void findUser() {
		
		List<UserVO> list = dao.selectUser();
		System.out.println("비밀번호 찾기 메뉴를 선택하셨습니다.\n\n\n");
		
		
		String findStr = CommUtil.getStr("비밀번호를 찾을 아이디를 입력해주세요.");
		for (UserVO vo : list) {
//			if (!vo.getId().equals(findStr)) continue;
			
			String findHint = CommUtil.getStr("비밀번호 힌트를 입력해주세요.");
			
//			if	(!vo.getPasshint().equals(findHint)) continue;
			
			System.out.println("∴비밀번호 찾기에 성공하셨습니다.∴");
			System.out.println("--------------------");
//			System.out.println("아이디 : " + vo.getId());
//			System.out.println("비밀번호 : " + vo.getPasswd());
			System.out.println("--------------------");
			System.out.println("메인메뉴로 돌아갑니다");
			return;
					
		}
		
		System.out.println("비밀번호 찾기에 실패하셨습니다.");
	
	}
	*/
	
	
	
	/**
	 *  유저 로그인
	 */
	public void loginUser() {
		System.out.println("영화예매 계정으로 로그인.\n\n\n");
		
		String userId = CommUtil.getStr("아이디를 입력해주세요 : ");
		String password = CommUtil.getStr("비밀번호를 입력해주세요 : ");
		UserVO userVO = dao.selectOneUser(userId, password);
		if (userVO == null) {
			System.out.println("∴아이디나 비밀번호가 바르게 입력되지 않았습니다.");
			System.out.println("다시 로그인해주세요.");
			return;
		}
		System.out.println("환영합니다. " + userVO.getUserId() + "님\n" + CommUtil.getDate() + "에 로그인 하셨습니다.");
		loginChk = true;
		vo = userVO;
	}
	
	
	/**
	 * 유저 조회
	 */
	public void selectUser() {
		UserVO userVO = dao.selectOneUser(vo.getUserId(), vo.getPassword());
		System.out.println("-------------------------------------");
		System.out.println("아이디 : " + userVO.getUserId());
		System.out.println("이메일 : " + userVO.getUserEmail());
		System.out.println("가입일 : " + userVO.getRegDate());
		System.out.println("영화예매수 : " + userVO.getReservCnt());
		System.out.println("등급 : " + userVO.getGradeName());
		System.out.println("할인율 : " + (userVO.getDiscountRate() * 100) + "%" );
		System.out.println("-------------------------------------");
		System.out.println("1. 수정");
		System.out.println("2. 삭제");
		System.out.println("0. 이전");
		System.out.println("-------------------------------------");
		int userChkNum = CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 ");
		switch (userChkNum) {
		case 1 : updateUser();	break;
		case 2 : deleteUser();	break;
		case 0 : 						break;
		 default : break;
		}
		
		
	}
	
	
	/**
	 * 유저 수정
	 * 
	 */
	public void updateUser() {
		
		String userEmail = CommUtil.getStr("이메일 주소를 입력하세요 : ");
		
		vo.setUserEmail(userEmail);
		int no = dao.updateUser(vo);
		
		
		if (no == 0) {
			System.out.println("============================");
			System.out.println("회원정보 수정이 실패하였습니다!!");
			return;
		}
		System.out.println("============================");
		System.out.println("회원정보 수정이 완료되었습니다.");
	}
	
	/**
	 * 유저 삭제
	 * 
	 */
	public void deleteUser() {

		String delYN = CommUtil.getStr("정말로 회원 탈퇴를 하시겠습니까?(Y/N) ");
		if (delYN.equalsIgnoreCase("Y")) {
			int no = dao.deleteUser(vo);
			if (no == 0) {
				System.out.println("============================");
				System.out.println("회원삭제가 실패하였습니다!!");
				System.out.println("============================");
				return;
			}
				vo = null;
				loginChk = false;
				System.out.println("회원삭제가 완료되었습니다.");
				System.out.println("============================");
				return;
		}
		System.out.println("회원 삭제 취소되었습니다.");
		System.out.println("============================");
	}
	
	
	
	
//	private void loginUser() {
//		userVO = user.loginUser();
//		if (userVO != null) {
//			System.out.println("환영합니다. " + userVO.getUserId() + "님\n" + CommUtil.getDate() + "에 로그인 하셨습니다.");
//			loginChk = true;
//		}
//	}
	
	
	public void logout() {
		System.out.println("현재 시간 : " + CommUtil.getDate());
		System.out.println("에 안전하게 로그아웃 되었습니다.");
		vo = null;
		loginChk = false;
	}
	
	
	/**
	 * 
	 *  유저정보 관련 처리 메소드
	 */
	
	
	public void joinUser() {
//		userVO.join();
	}
	
	
	
}
