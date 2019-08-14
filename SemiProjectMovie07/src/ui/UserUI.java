package ui;

import java.util.List;

import dao.UserDAO;
import util.CommUtil;
import vo.UserVO;

public class UserUI {
	
	
	private UserDAO dao;
	
	public UserUI(UserDAO dao) {
		this.dao = dao;
	}
	
	
	
	public void join() {
		UserVO vo = new UserVO();
		String userId = CommUtil.getStr("아이디를 입력하세요 : ");
		String password = CommUtil.getStr("비밀번호를 입력하세요 : ");
		String userEmail = CommUtil.getStr("이메일 주소를 입력하세요 : ");
		
		vo.setUserId(userId);
		vo.setPassword(password);
		vo.setUserEmail(userEmail);
		int no = dao.insertUser(vo);
		
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
	
	
	
	
	public UserVO loginUser() {
		System.out.println("영화예매 계정으로 로그인.\n\n\n");
		
		String userId = CommUtil.getStr("아이디를 입력해주세요 : ");
		String password = CommUtil.getStr("비밀번호를 입력해주세요 : ");
		UserVO user = dao.selectOneUser(userId, password);
		if (user == null) {
			System.out.println("∴아이디나 비밀번호가 바르게 입력되지 않았습니다.");
			System.out.println("다시 로그인해주세요.");
			return null;
		}
		
		return user;
	}
	
	
	
	
	
}
