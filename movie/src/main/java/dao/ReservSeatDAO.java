package dao;

import java.util.List;

import vo.ReservationVO;

public interface ReservSeatDAO {
	
	/**
	 *  회차 당 영화 예매 자리 조회
	 * 
	 * @return List<ReservationVO>
	 */
	public List<ReservationVO> reservSeatList(int inningNo);
	
}
