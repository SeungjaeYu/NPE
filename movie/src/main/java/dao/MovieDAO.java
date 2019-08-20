package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
	public List<MovieVO> selectMovie() {
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
			while(rs.next()) {
				MovieVO movie = new MovieVO();
				
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
	 * 선택한 영화 조회
	 * @param movieNo
	 * @return
	 */
	public MovieVO selectOneMovie(String originalTitle) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionPool.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select * ");
			sql.append("  from tb_movie");
			sql.append(" where movie_title = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, originalTitle);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				MovieVO movie = new MovieVO();
				movie.setMovieNo(rs.getInt("movie_no"));
				movie.setMovieTitle(rs.getString("movie_title"));
				movie.setMovieDirector(rs.getString("movie_director"));
				movie.setMovieActor(rs.getString("movie_actor"));
				movie.setViewingGrade(rs.getString("viewing_grade"));
				return movie;
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
	 * 메서드 명 : insertMovie()
	 * 기능 정의 : 영화 등록
	 * @return
	 */
	public static int insertMovie(String movieTitle, String movieDirector, String movieActor, String viewingGrade,
							int movieInning, String movieTime, String theaterName) {
		
		/*
		 * String movieTitle, String movieDirector, String movieActor, String viewingGrade,
							int movieInning, String movieTime, int theaterNo
		 */
		String[] movieDel = new String[3];
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int chkCnt = 0;
		
		String seq = "select tb_movie_seq.nextval as movie_seq from dual";
		
		movieDel[0] = " insert into tb_movie (movie_no, movie_title, movie_director, movie_actor, viewing_grade)"
				+ " values (?,?,?,?,?)";
		movieDel[1] = " select theater_no from tb_theater where theater_name = ? ";
		movieDel[2] = " insert into tb_inning (inning_no, movie_no, movie_inning, movie_time, theater_no)"
				+ " values (tb_inning_seq.nextval,?,?,?,?)";
		ResultSet rs =  null;
		try {
			con = ConnectionPool.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(seq);
			rs = pstmt.executeQuery();
			
			int movieNo = 0;
			if (rs.next()) {
				movieNo = rs.getInt("movie_seq");
			}
			rs.close();
			ConnectionFactory.close(pstmt);
			
			if (movieNo ==0) return 0;
			
			pstmt = con.prepareStatement(movieDel[0]);
			pstmt.setObject(1, movieNo);
			pstmt.setObject(2, movieTitle);
			pstmt.setObject(3, movieDirector);
			pstmt.setObject(4, movieActor);
			pstmt.setObject(5, viewingGrade);
			chkCnt += pstmt.executeUpdate();
			ConnectionFactory.close(pstmt);
			
			
			pstmt = con.prepareStatement(movieDel[1]);
			pstmt.setObject(1, theaterName);
			
			rs =  pstmt.executeQuery();
			int theaterNo = 0;
			if (rs.next()) {
				theaterNo = rs.getInt("theater_no");
			}
			
			if (theaterNo ==0) return 0;
			rs.close();
			ConnectionFactory.close(pstmt);


			pstmt = con.prepareStatement(movieDel[2]);
			pstmt.setObject(1, movieNo);
			pstmt.setObject(2, movieInning);
			pstmt.setObject(3, movieTime);
			pstmt.setObject(4, theaterNo);
			chkCnt += pstmt.executeUpdate();
			ConnectionFactory.close(pstmt);
			
			
			con.commit();
			return chkCnt;
		}catch(Exception e) {
			try {
				con.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(pstmt);
			ConnectionPool.relaseConnection(con);
		}
		
		
		return 0;
	}
	
	/*
	public int insertMovie(MovieVO movie) {
		try {
			return SqlExecutor.update(
					"insert into tb_movie(movie_no, movie_title, movie_director, movie_actor, viewing_grade) "
					+ "values (tb_movie_seq.nextval, ?, ?, ?, ?)",
					movie.getMovieTitle(), movie.getMovieDirector(), 
					movie.getMovieActor(), movie.getViewingGrade()
				);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	*/
	/**
	 * 영화 수정
	 */
	public int modifyMovie(MovieVO vo, String originalTitle) {
		try {
			return SqlExecutor.update(
					"update tb_movie set movie_title = ?, movie_director = ?, movie_actor = ?, viewing_grade = ? where movie_title = ? ",
					vo.getMovieTitle(), vo.getMovieDirector(), vo.getMovieActor(), vo.getViewingGrade(),originalTitle
					);
		}catch (Exception e) {
			
		}
		return 0;
	}
	
	/**
	 * 메서드 명 : deleteMovie()
	 * 기능 정의 : 영화 삭제
	 * @return
	 */
	public int deleteMovie(String movieTitle) {
		String tableName = "TB_MOVIE";
		try {
			return SqlExecutor.update(
					"delete from " + tableName + " where movie_title = ? ", movieTitle
				);
		} catch(Exception e) {
			if(e.getMessage().contains("child record found"))
				return 2;
			//e.printStackTrace();
		}
		return 0;
	}
}
