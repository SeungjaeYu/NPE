package dao;

import java.util.List;

import vo.ReservationVO;

public interface ReservationDAO {

	/**
	 *  유저 당 예매 전체 조회
	 * 
	 * @return List<UserVO>
	 */
	
	public List<ReservationVO> reservList(int userNo);	
	
	/**
	 * 
	 *  예매 등록
	 * 
	 * @param inningNo
	 * @param userNo
	 * @param reservRow
	 * @param reservCol
	 * @return
	 */
	
	public int insertReserv(ReservationVO reservationVO); 
	/**
	 * 유저 예매 삭제
	 * 
	 * @param reservNo
	 * @return
	 */
	public int deleteReserv(int reservNo);
	
	int countReserv(int inningNo);
	
	ReservationVO reservSelectOne(int reservNo);
	
	
}
