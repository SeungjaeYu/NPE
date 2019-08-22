package ui;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.UserDAO;
import encrypt.SHA256Password;
import util.CommUtil;
import util.SendEmail;
import vo.UserVO;

public class UserUI {
	SHA256Password sha256 = new SHA256Password();
	SqlSession session;
	
	private UserDAO userDAO;
	
	public UserUI() {
		session = db.MyAppSqlConfig.getSqlSessionInstance();
		userDAO = session.getMapper(UserDAO.class);
	}
	
	
	boolean loginChk = false;
	UserVO vo = null;
	boolean adminChk = false;
	
	
	
	/**
	 * 회원가입
	 */
	public void join() {
		UserVO userVO = new UserVO();
		String userId = CommUtil.getStr("아이디를 입력하세요 : ");
		String password = CommUtil.getStr("비밀번호를 입력하세요 : ");
		String userEmail = CommUtil.getStr("이메일 주소를 입력하세요 : ");
		String randomNum = CommUtil.randomKey();
		System.out.println("인증번호를 메일로 발송중입니다....");
		try {
			new SendEmail(userEmail, "회원가입 인증번호 입니다.", randomNum);
		} catch (Exception e) {
			System.out.println("이메일 전송이 실패하였습니다. 올바른 형식의 이메일 주소를 입력하세요.");
			return;
		}
		System.out.println("인증번호를 메일로 발송 완료하였습니다.");
		
		String failMsg = "회원 가입이 실패하였습니다.";
		if (!CommUtil.emailChk(randomNum, failMsg)) return;
		System.out.println("인증이 완료되었습니다. ");
		
		
		userVO.setUserId(userId);
		userVO.setPassword(sha256.LockPassword(password));
		userVO.setUserEmail(userEmail);
		int no = userDAO.insertUser(userVO);
		
		if (no == 0) {
			session.rollback();
			System.out.println("============================");
			System.out.println("중복된 아이디의 회원이 있습니다. ");
			return;
		}
		session.commit();
		System.out.println("============================");
		System.out.println("회원가입이 완료되었습니다.");
	}
	
	
	
	
	
	/**
	 * 비밀번호 찾기/ 변경
	 */
	public void findUser() {
		
		List<UserVO> list = userDAO.selectAdminList();
		System.out.println("비밀번호 찾기 메뉴를 선택하셨습니다.\n\n\n");
		
		
		String findId = CommUtil.getStr("비밀번호를 찾을 아이디를 입력해주세요 : ");
		String findEmail = CommUtil.getStr("이메일 주소를 입력해주세요 : ");
		for (UserVO vo : list) {
			if (vo.getUserId().equals(findId) && vo.getUserEmail().equals(findEmail)) {
			
				System.out.println("인증번호를 메일로 발송중입니다....");
				String randomNum = CommUtil.randomKeyByPassword();
				try {
					new SendEmail(vo.getUserEmail(), "비밀번호 변경 인증번호 입니다.", randomNum);
				} catch (Exception e) {
					System.out.println("이메일 전송이 실패하였습니다. 올바른 형식의 이메일 주소를 입력하세요.");
					return;
				}
				System.out.println("인증번호를 메일로 발송 완료하였습니다.");
				String failMsg = "비밀번호 찾기가 실패하였습니다.";
				if (!CommUtil.emailChk(randomNum, failMsg)) return;
				System.out.println("인증이 완료되었습니다. ");
				String password = CommUtil.getStr("수정할 비밀번호를 입력하세요 : ");
				
				vo.setPassword(sha256.LockPassword(password));
				int no = userDAO.updateUser(vo);
				
				if (no == 0) {
					session.rollback();
					System.out.println("============================");
					System.out.println("비밀번호 변경이 실패하였습니다.");
					return;
				}
				session.commit();
				System.out.println("============================");
				System.out.println("비밀번호 변경이 완료되었습니다.");
				
			
			return;
			}	
		}
		
		System.out.println("비밀번호 찾기에 실패하셨습니다.");
	
	}
	
	
	
	
	/**
	 *  유저 로그인
	 */
	public void loginUser() {
		System.out.println("영화예매 계정으로 로그인.\n\n\n");
		UserVO user = new UserVO();
		user.setUserId( CommUtil.getStr("아이디를 입력해주세요 : "));
		user.setPassword(sha256.LockPassword(CommUtil.getStr("비밀번호를 입력해주세요 : ")));
		UserVO userVO = userDAO.selectOneUser(user);
		if (userVO == null) {
			System.out.println("∴아이디나 비밀번호가 바르게 입력되지 않았습니다.");
			System.out.println("다시 로그인해주세요.");
			return;
		}
		if (userVO.getUserId().equals("admin")) {
			adminChk = true;
		}
		System.out.println("환영합니다. " + userVO.getUserId() + "님\n" + CommUtil.getDate() + "에 로그인 하셨습니다.");
		loginChk = true;
		vo = userVO;
	}
	
	
	/**
	 * 회원 전체목록 조회
	 */
	public void selectUserList() {
		List<UserVO> userList = userDAO.selectAdminList();
		System.out.println("∴회원정보");
		System.out.println("아이디\t이메일\t가입일\t등급");
		System.out.println("----------------------------------------");
		if (userList.isEmpty()) {
			System.out.println("아직 가입한 회원이 없습니다. ");
			System.out.println("----------------------------------------");
			return;
		}
		
		for (UserVO userVO : userList) {
			System.out.print(userVO.getUserId() + "\t");
			System.out.print( userVO.getUserEmail() + "\t");
			System.out.print(CommUtil.getDate(userVO.getRegDate()) + "\t");
			System.out.println(userVO.getGradeName() + "\t");
			
		}
		System.out.println("----------------------------------------");
		
	} 
	
	 
	
	/**
	 * 유저 조회
	 */
	public void selectUser() {
		UserVO userVO = userDAO.selectOneUser(vo);
		System.out.println("-------------------------------------");
		System.out.println("아이디 : " + userVO.getUserId());
		System.out.println("이메일 : " + userVO.getUserEmail());
		System.out.println("가입일 : " + CommUtil.getDate(userVO.getRegDate()));
		System.out.println("영화예매수 : " + userVO.getReservCnt());
		System.out.println("등급 : " + userVO.getGradeName());
		System.out.println("할인율 : " + (userVO.getDiscountRate() * 100) + "%" );
		System.out.println("-------------------------------------");
		System.out.println("1. 회원정보수정");
		System.out.println("2. 탈퇴");
		System.out.println("0. 이전");
		System.out.println("-------------------------------------");
		int userChkNum = CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
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
		
		String userEmail = CommUtil.getStr("수정할 이메일 주소를 입력하세요 : ");
		String password = CommUtil.getStr("수정할 비밀번호를 입력하세요 : ");
		
		vo.setUserEmail(userEmail);
		vo.setPassword(sha256.LockPassword(password));
		int no = userDAO.updateUser(vo);
		
		
		if (no == 0) {
			session.rollback();
			System.out.println("============================");
			System.out.println("회원정보 수정 실패하였습니다.");
			return;
		}
		session.commit();
		System.out.println("============================");
		System.out.println("회원정보 수정 완료되었습니다.");
	}
	
	/**
	 * 유저 삭제
	 * 
	 */
	public void deleteUser() {

		String delYN = CommUtil.getStr("정말로 회원 탈퇴를 하시겠습니까?(Y/N) : ");
		if (adminChk) {
			System.out.println("관리자는 아이디 삭제를 할 수 없습니다. ");
			return;
		}
		if (delYN.equalsIgnoreCase("Y")) {
			int no = userDAO.deleteUser(vo.getUserNo());
			if (no == 0) {
				session.rollback(); 
				System.out.println("============================");
				System.out.println("회원 삭제 실패하였습니다. ");
				System.out.println("============================");
				return;
			}
				session.commit();
				vo = null;
				loginChk = false;
				System.out.println("회원 삭제 완료되었습니다.");
				System.out.println("============================");
				return;
		}
		System.out.println("회원 삭제 취소되었습니다.");
		System.out.println("============================");
	}
	

	
	/**
	 * 로그아웃
	 */
	public void logout() {
		System.out.print("현재 시간 : " + CommUtil.getDate());
		System.out.println("에 안전하게 로그아웃 되었습니다.");
		vo = null;
		loginChk = false;
		adminChk = false;
	}
	
	
}
