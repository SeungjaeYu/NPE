package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import util.SqlExecutor;
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
	
	/*
	{	insertReserv(int inningNo, int userNo, int reservRow, int reservCol) 
		
		String[] reservDel = new String[3];
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int chkCnt = 0;
		
		String seq = "select tb_reservation_seq.nextval as reserv_seq from dual "; // 시퀀스의 값을 이용하여 예매좌석 테이블에도 넣어야 하므로 미리 가져온다.
		
		
		// check = selectKey 활용하여 넘기기
		reservDel[0] = " insert into tb_reservation (reserv_no, inning_no, user_no) values (?, ?, ?) ";
		reservDel[1] = " insert into tb_reserv_seat (reserv_no, reserv_row, reserv_col) values (?, ?, ?) ";
		reservDel[2] = " update tb_user set reserv_cnt = reserv_cnt + 1 where user_no = ? ";
		

		try {
			
			con = ConnectionPool.getConnection();
			con.setAutoCommit(false);
			
			
			pstmt = con.prepareStatement(seq);
			ResultSet rs =  pstmt.executeQuery();
			int reservNo = 0;
			if (rs.next()) {
				reservNo = rs.getInt("reserv_seq");
			}
			ConnectionFactory.close(pstmt);
			
			if (reservNo == 0) return 0;
			
			pstmt = con.prepareStatement(reservDel[0]);
			pstmt.setObject(1, reservNo);
			pstmt.setObject(2, inningNo);
			pstmt.setObject(3, userNo);
			chkCnt += pstmt.executeUpdate();
			ConnectionFactory.close(pstmt);
			
			pstmt = con.prepareStatement(reservDel[1]);
			pstmt.setObject(1, reservNo);
			pstmt.setObject(2, reservRow);
			pstmt.setObject(3, reservCol);
			chkCnt += pstmt.executeUpdate();
			ConnectionFactory.close(pstmt);
			
			pstmt = con.prepareStatement(reservDel[2]);
			pstmt.setObject(1, userNo);
			
			chkCnt += pstmt.executeUpdate();
			ConnectionFactory.close(pstmt);
			
			
			
			con.commit();
			return chkCnt;
		} catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}

		return 0;
	}
	*/
	
	
	/**
	 * 유저 예매 삭제
	 * 
	 * @param reservNo
	 * @return
	 */
	public int deleteReserv(int reservNo);
	
	
	/*
	{	deleteReserv(int reservNo, int userNo);
		
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
	*/
	
	
	
}
