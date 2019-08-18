package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import vo.ReservationVO;

public class ReservSeatDAO_yoo {
	
	/**
	 *  회차 당 영화 예매 자리 조회
	 * 
	 * @return List<ReservationVO>
	 */
	
	public List<ReservationVO> reservSeatList(int inningNo) {
		List<ReservationVO> reservSeatList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			
			sql.append(" select r.reserv_no, r.inning_no, r.user_no, s.reserv_row, s.reserv_col from tb_reservation r ");
			sql.append("  inner join tb_reserv_seat s ");
			sql.append("  on r.reserv_no = s.reserv_no ");
			sql.append("  where r.inning_no = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			
			pstmt.setInt(1, inningNo);
			
			ResultSet rs = pstmt.executeQuery();
			int idx = 1;
			while (rs.next()) {
				ReservationVO reservVO = new ReservationVO();
				reservVO.setTempNo(idx++);
				reservVO.setReservNo(rs.getInt("reserv_no"));
				reservVO.setInningNo(rs.getInt("inning_no"));
				reservVO.setUserNo(rs.getInt("user_no"));
				reservVO.setReservRow(rs.getInt("reserv_row"));
				reservVO.setReservCol(rs.getInt("reserv_col"));
				
				reservSeatList.add(reservVO);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		
		
		
		return reservSeatList;
		
	}

}