package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import util.SqlExecutor;
import vo.ReservationVO;

public class ReservationDAO {

	/**
	 *  유저 당 예매 전체 조회
	 * 
	 * @return List<UserVO>
	 */
	
	public List<ReservationVO> reservList(int userNo) {
		List<ReservationVO> reservList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			
			sql.append("select u.user_no, r.reserv_no, m.movie_title, i.movie_time, t.theater_name, s.reserv_col, s.reserv_row ");
			sql.append("  from tb_reserv_seat s ");
			sql.append("  inner join tb_reservation r ");
			sql.append("  on s.reserv_no = r.reserv_no ");
			sql.append("  inner join tb_inning i ");
			sql.append("  on r.inning_no = i.inning_no ");
			sql.append("  inner join tb_movie m ");
			sql.append("  on m.movie_no = i.movie_no ");
			sql.append("  inner join tb_theater t ");
			sql.append("  on i.theater_no = t.theater_no ");
			sql.append("  inner join tb_user u ");
			sql.append("  on r.user_no = u.user_no ");
			sql.append("  where r.user_no = ? ");
			
			
			sql.append(" order by r.reserv_no desc ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			
			pstmt.setInt(1, userNo);
			
			ResultSet rs = pstmt.executeQuery();
			int idx = 1;
			while (rs.next()) {
				ReservationVO reservVO = new ReservationVO();
				reservVO.setTempNo(idx++);
				reservVO.setUserNo(rs.getInt("user_no"));
				reservVO.setReservNo(rs.getInt("reserv_no"));
				reservVO.setMovieTitle(rs.getString("movie_title"));
				reservVO.setMovieTime(rs.getString("movie_time"));
				reservVO.setTheaterName(rs.getString("theater_name"));
				reservVO.setReservCol(rs.getInt("reserv_col"));
				reservVO.setReservRow(rs.getInt("reserv_row"));
				
				
				reservList.add(reservVO);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		
		
		
		return reservList;
		
	}
	
	
	
	
	
	
	/**
	 * 유저 예매 삭제
	 * 
	 * @param reservNo
	 * @return
	 */
	public int deleteReserv(int reservNo, int userNo) {
		
		int result = 0;
		String[] reservDel = new String[3];
		Object[] params = new Object[3];
		try {
			reservDel[0] = "delete from tb_reserv_seat where reserv_no = ?";
			reservDel[1] = "delete from tb_reservation where reserv_no = ?";
			reservDel[2] = "update tb_user set reserv_cnt = reserv_cnt - 1 where user_no = ?";
			params[0] = reservNo;
			params[1] = reservNo;
			params[2] = userNo;
			result = SqlExecutor.update(
	                   reservDel, false, params,  reservNo);
		} catch (Exception e) {
			//e.getMessage();
			e.printStackTrace();
		} 
		return result;
	}
	
	
	public int countRserv(int inningNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			
			sql.append(" select count(*) as reserv_cnt from tb_reservation ");
			sql.append("  where inning_no = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			
			pstmt.setInt(1, inningNo);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("reserv_cnt");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		
		
		
		return 0;
	}
}
