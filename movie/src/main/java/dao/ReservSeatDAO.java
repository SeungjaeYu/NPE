package dao;

import java.util.List;

import vo.ReservSeatVO;
import vo.ReservationVO;

public interface ReservSeatDAO {
	
	/**
	 *  회차 당 영화 예매 자리 조회
	 * 
	 * @return List<ReservationVO>
	 */
	List<ReservationVO> reservSeatList(int inningNo);
	
	/**
	 *  영화 예매 좌석 삭제
	 */
	int deleteReservSeat(int reservNo);
	
	/**
	 *  영화 예매 좌석 추가
	 */
	int insertReservSeat(ReservSeatVO reservSeatVO);
	
	
}
