package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.ConnectionPool;
import util.SqlExecutor;
import vo.MovieVO;

public class MovieDAO_yoo {

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
			int idx = 1;
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieVO movie = new MovieVO();
				movie.setTempNo(idx++);
				movie.setMovieNo(rs.getInt("movie_no"));
				movie.setMovieTitle(rs.getString("movie_title"));
				movie.setMovieDirector(rs.getString("movie_director"));
				movie.setMovieActor(rs.getString("movie_actor"));
				movie.setViewingGrade(rs.getString("viewing_grade"));
				list.add(movie);
				
			}
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
}