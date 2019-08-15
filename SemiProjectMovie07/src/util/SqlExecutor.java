package util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SqlExecutor {
	
	
	public static int update(String sql, Object ...args) throws Exception {
		return update(sql, true, args);
	}
	
	public static int update(String sql, boolean autoCommit, Object ...args) throws Exception {
		String[] arr = new String[1];
		arr[0] = sql;
		return update(arr, true, args);
	}
	
	public static int update(String[] sql, boolean autoCommit, Object ...args) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int chkCnt = 0;
		
		try {
			con = ConnectionPool.getConnection();
			con.setAutoCommit(autoCommit);
			for (String val : sql) {
				pstmt = con.prepareStatement(val);
				int index = 1;
				for (Object arg : args) {
					pstmt.setObject(index++, arg);
				}
				chkCnt += pstmt.executeUpdate();
			}
			if (!autoCommit) con.commit();
			return chkCnt;
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
	}
	
	
}
