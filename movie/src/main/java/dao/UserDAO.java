package dao;

import java.util.List;
import vo.UserVO;

public interface UserDAO {
	// 관리자 전용 유저 전체 조회
	List<UserVO> selectAdminList();
	
	// 사용자 로그인 조회
	UserVO selectOneUser(UserVO vo);
	// 회원 추가 (회원 등록)
	int insertUser(UserVO vo);
	// 회원 정보 수정
	int updateUser(UserVO vo);
	// 회원 예매수 증가
	int updateUserReservAdd(int userNo);
	// 회원 예매수 감소
	int updateUserReservRemove(int userNo);
	// 회원 삭제(탈퇴한 회원)
	int deleteUser(int userNo);

	UserVO selectOneUserByUserNo(int userNo);
	
	// 비밀번호 변경
	int updateUserByPassword(UserVO vo);
}
