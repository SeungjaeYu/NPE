package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import util.SqlExecutor;
import vo.UserVO;

public class UserDAO {
	
	
	/**
	 *  관리자 기능 유저 전체 조회
	 * 
	 * @return
	 */
	public List<UserVO> selectAdminList() {
		List<UserVO> userList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
		
			sql.append("select * ");
			sql.append("  from tb_user");
			sql.append(" order by user_no desc ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setUserEmail(rs.getString("user_email"));
				user.setRegDate(rs.getDate("reg_date"));
				user.setReservCnt(rs.getInt("reserv_cnt"));
				userList.add(user);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		
		
		
		return userList;
		
	}
	
	/**
	 * 
	 *  사용자 로그인 조회
	 * @return
	 */
	
	public UserVO selectOneUser(String userId, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			


			
			sql.append("select * ");
			sql.append("  from tb_user t");
			sql.append(" inner join tb_grade g");
			sql.append(" on t.reserv_cnt between g.grade_min_reserv and g.grade_max_reserv");
			sql.append(" where t.user_id = ? ");
			sql.append("   and t.password = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				UserVO user = new UserVO();
				
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setPassword(rs.getString("password"));
				user.setUserEmail(rs.getString("user_email"));
				user.setRegDate(rs.getDate("reg_date"));
				user.setReservCnt(rs.getInt("reserv_cnt"));
				user.setGradeName(rs.getString("grade_name"));
				user.setDiscountRate(rs.getDouble("discount_rate"));
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		
		return null;
	}
	
	
	public int updateUser(UserVO userVO) {
		int result = 0;
		try {
			result = SqlExecutor.update(
	                   "update tb_user set user_email = ? where user_no = ?",
	                   userVO.getUserEmail(), userVO.getUserNo());
			
		} catch (Exception e) {
			//e.getMessage();
			e.printStackTrace();
		} 
		return result;
		
	}
	
	public int insertUser(UserVO vo) {
		int result = 0;
		try {
			result = SqlExecutor.update(
	                   "insert into tb_user(user_no, user_id, password, user_email) values (tb_user_seq.nextval, ?, ?, ?)",
	                   vo.getUserId(), vo.getPassword(), vo.getUserEmail());
		} catch (Exception e) {
			//e.getMessage();
			//e.printStackTrace();
		} 
		return result;
		
	}
	
	public int deleteUser(UserVO vo) {
		int result = 0;
		try {
			result = SqlExecutor.update(
	                   "delete tb_user where user_no = ?",
	                   vo.getUserNo());
		} catch (Exception e) {
			//e.getMessage();
			//e.printStackTrace();
		} 
		return result;
		
	}


}
