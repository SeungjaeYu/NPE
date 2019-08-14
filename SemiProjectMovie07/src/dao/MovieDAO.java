package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import util.SqlExecutor;
import vo.MovieVO;

public class MovieDAO {
	/**
	 * 메서드 명 : selectMovie()
	 * 기능 정의 : 등록된 영화 목록 조회
	 * @return
	 */
	public static List<MovieVO> selectMovie() {
		List<MovieVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			String tableName = "TB_MOVIE";
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * ");
			sql.append("  from " + tableName);
			sql.append(" order by movie_no desc ");
			pstmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
//			System.out.println("------------------------------");
//			for (int i = 1; i <= count; i++) {
//				String columnName = rsmd.getColumnName(i);
//				
//				System.out.print(columnName + "\t");
//			}
//			System.out.println("\n------------------------------");
			while(rs.next()) {
				MovieVO movie = new MovieVO();
				
				movie.setMovieNo(rs.getInt("movie_no"));
				movie.setMovieTitle(rs.getString("movie_title"));
				movie.setMovieDirector(rs.getString("movie_director"));
				movie.setMovieActor(rs.getString("movie_actor"));
				movie.setViewingGrade(rs.getString("viewing_grade"));
				list.add(movie);
				
			}
			
//			for (MovieVO movie: list) {
//				System.out.print(movie.getMovieNo() + "\t\t");
//				System.out.print(movie.getMovieTitle() + "\t\t");
//				System.out.print(movie.getMovieDirector() + "\t\t");
//				System.out.print(movie.getMovieActor() + "\t\t");
//				System.out.println(movie.getViewingGrade());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
//			↙ 메소드명 변경요망 relaseConnection -> releaseConnection
			ConnectionPool.relaseConnection(con);
		}
		
		return list;
	}
	
	/**
	 * 메서드 명 : insertMovie()
	 * 기능 정의 : 영화 등록
	 * @return
	 */
	public void insertMovie(MovieVO movie) {
		try {
			int cnt = SqlExecutor.update(
//					↙ null을 허용하는 컬럼 처리...
					"insert into tb_movie(movie_no, movie_title, movie_director, movie_actor, viewing_grade) values (tb_movie_seq.nextval, ?, ?, ?, ?)",
					movie.getMovieTitle(), movie.getMovieDirector(), movie.getMovieActor(), movie.getViewingGrade()
				);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 메서드 명 : deleteMovie()
	 * 기능 정의 : 영화 삭제
	 * @return
	 */
	public int deleteMovie(int no) {
		String tableName = "TB_MOVIE";
		try {
			return SqlExecutor.update(
					"delete from " + tableName + " where movie_no = ? ", no
				);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println("무비 메인 ok");
		// selectMovie()
//		new MovieDAO().selectMovie();
		// ---------- END selectMovie()
		
		// insertMovie()
		MovieVO movie = new MovieVO();
//		movie.setMovieNo(11);
		movie.setMovieTitle(null);
		movie.setMovieActor("김배우22");
		movie.setMovieDirector("박감독22");
		movie.setViewingGrade("청불");
		// ---------- END insertMovie()
//		new MovieDAO().insertMovie(movie);
		
		// deleteMovie()
		int no = 14;
		new MovieDAO().deleteMovie(no);
		// ---------- END deleteMovie()
	}
}
 