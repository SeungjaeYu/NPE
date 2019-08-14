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
			sql.append("  from tb_board");
			sql.append(" order by no desc ");
			
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
			sql.append("  from tb_user");
			sql.append(" where user_id = ? ");
			sql.append("   and password = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				UserVO user = new UserVO();
				
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setUserEmail(rs.getString("user_email"));
				user.setRegDate(rs.getDate("reg_date"));
				user.setReservCnt(rs.getInt("reserv_cnt"));
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
		
		
		/*
		try {
			Connection con = ConnectionPool.getConnection();
			
			
		}
		List<UserVO> list = selectUser();
		for (UserVO user : list) {
			if (user.getId().equals(vo.getId())) {
				return 0;
			}
		}
		
		String user = ++pos + "@@" + vo.getId() + "@@" + vo.getPasswd() + "@@" + vo.getPasshint() + "@@"
				      + vo.getName() + "@@" + vo.getGender() + "@@" + vo.getPhone();
		fileWrite(path, user, true);
		return 1;
		*/
		
		
	}
	/*
	public List<UserVO> selectUser() {
		List<UserVO> list = new ArrayList<>();
		List<String> list2 = fileRead(path);
	
		if (list2 != null) {
			for (String str : list2) {
				String[] arr = str.split("@@");
//				list.add(new UserVO(Integer.parseInt(arr[0]),
//						arr[1], arr[2], arr[3], arr[4], arr[5], Integer.parseInt(arr[6])));
			}
		}
		
		return list;
	}
	*/

}
