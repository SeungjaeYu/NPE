package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import vo.InningVO;

public class InningDAO {
	/**
	 * 메서드 명 : selectInning()
	 * 기능 정의 : 등록 된 회차 목록 조회
	 */
	
	public List<InningVO> selectInning(int movieNo) {
		List<InningVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			
			StringBuffer sql = new StringBuffer();
			sql.append("select i.inning_no, i.movie_time, th.theater_name, th.seat_col, th.seat_row ");
			sql.append("  from tb_inning i, tb_theater th ");
			sql.append(" where i.theater_no = th.theater_no and movie_no = ? ");
			sql.append(" order by i.movie_inning ");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, movieNo);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				InningVO inning = new InningVO();
				inning.setInningNo(rs.getInt("inning_no"));
				inning.setTheaterName(rs.getString("theater_name"));
				inning.setMovieTime(rs.getString("movie_time"));
				inning.setSeatRow(rs.getInt("seat_row"));
				inning.setSeatCol(rs.getInt("seat_col"));
				list.add(inning);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		return list;
	}
	
	/**
	 * 메서드 명 : selectOneInning()
	 * 기능 정의 : 등록 된 회차 하나 조회
	 */
	
	public InningVO selectOneInning(int inningNo) {
		InningVO inning = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			
			StringBuffer sql = new StringBuffer();
			sql.append("select i.inning_no, i.movie_time, th.theater_name, th.seat_col, th.seat_row ");
			sql.append("  from tb_inning i, tb_theater th ");
			sql.append(" where i.theater_no = th.theater_no and inning_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, inningNo);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println();
			if (rs.next()) {
				inning = new InningVO();
				inning.setInningNo(rs.getInt("inning_no"));
				inning.setTheaterName(rs.getString("theater_name"));
				inning.setMovieTime(rs.getString("movie_time"));
				inning.setSeatRow(rs.getInt("seat_row"));
				inning.setSeatCol(rs.getInt("seat_col"));
				
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		return inning;
	}
}