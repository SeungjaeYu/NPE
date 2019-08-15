package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import util.SqlExecutor;
import vo.TheaterVO;

public class TheaterDAO {


	/**
	 * 상영관 전체 리스트
	 * @return
	 */
	public List<TheaterVO> selectTheaterList() {
		List<TheaterVO> theaterList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("select * ");
			sql.append("  from tb_theater");
			sql.append(" order by theater_name ");

			pstmt = con.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				TheaterVO theater = new TheaterVO();
				theater.setTheaterNo(rs.getInt("theater_no"));
				theater.setTheaterName(rs.getString("theater_name"));
				theater.setSeatRow(rs.getInt("seat_row"));
				theater.setSeatCol(rs.getInt("seat_col"));

				theaterList.add(theater);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		return theaterList;
	}

	
	/**
	 * 상영관 정보 조회
	 * @param theaterNo
	 * @return
	 */
	public TheaterVO selectOneTheater(String theaterName) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionPool.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select * ");
			sql.append("  from tb_theater");
			sql.append(" where theater_name = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, theaterName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				TheaterVO theater = new TheaterVO();
				theater.setTheaterNo(rs.getInt("theater_no"));
				theater.setTheaterName(rs.getString("theater_name"));
				theater.setSeatRow(rs.getInt("seat_row"));
				theater.setSeatCol(rs.getInt("seat_col"));
				return theater;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}

		return null;
	}

	
	
	/**
	 * 상영관 정보 입력
	 * @param vo
	 */
	public int insertTheater(TheaterVO vo) {

		try {
			return SqlExecutor.update(
					"insert into tb_theater(theater_no, theater_name, seat_row, seat_col) values (tb_theater_seq.nextval, ?, ?, ?)",
					vo.getTheaterName(), vo.getSeatRow(), vo.getSeatCol());
			
		} catch (Exception e) {
			
		}
		return 0;
	}
	
	/**
	 * 상영관정보 수정
	 * @param vo
	 */
	public int updateTheater(TheaterVO vo, String originalName) {
		try {
			return  SqlExecutor.update(
					"update tb_theater set theater_name = ? ,seat_row =?, seat_col = ? where theater_name = ?",
					vo.getTheaterName(), vo.getSeatRow(), vo.getSeatCol(), originalName
					);
		} catch(Exception e) {
			
		}
		return 0;
	}
	
	/**
	 * 상영관 삭제
	 * @param no
	 * @return
	 */
	public int deleteTheater(String theaterName) {
		int result = 0;
		try {
			result = SqlExecutor.update("delete from tb_theater where theater_name = ? ", theaterName
					);

		} catch (Exception e) {
			if(e.getMessage().contains("child record found")) {
				result =  2;
			}
			//e.printStackTrace();
		}
		return result;
	}
	
	

}
