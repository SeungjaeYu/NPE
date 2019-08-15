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
	
	public static List<InningVO> selectInning(int movieNo) {
		List<InningVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			
			StringBuffer sql = new StringBuffer();
			sql.append("select i.movie_time, th.theater_name ");
			sql.append("  from tb_inning i, tb_theater th ");
			sql.append(" where i.theater_no = th.theater_no and movie_no = ? ");
			sql.append(" order by inning_no desc");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, movieNo);
			
			ResultSet rs = pstmt.executeQuery();

			System.out.println();
			while (rs.next()) {
				
				
				InningVO inning = new InningVO();
				inning.setTheaterName(rs.getString("theater_name"));
				inning.setMovieTime(rs.getString("movie_time"));
				list.add(inning);
			}
			for (InningVO inning : list) {
				System.out.print(inning.getMovieTime() + "\t\t");
				System.out.print(inning.getTheaterName() + "\n");
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		return list;
	}
}