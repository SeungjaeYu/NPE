package vo;

public class ReservationVO {
	private int reservNo;
	private int inningNo;
	private int userNo;
	private String movieTitle;
	private String movieTime;
	private String theaterName;
	private int reserv_row;
	private int reserv_col;
	
	public ReservationVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservationVO(int reservNo, int inningNo, int userNo, String movieTitle, String movieTime,
			String theaterName, int reserv_row, int reserv_col) {
		super();
		this.reservNo = reservNo;
		this.inningNo = inningNo;
		this.userNo = userNo;
		this.movieTitle = movieTitle;
		this.movieTime = movieTime;
		this.theaterName = theaterName;
		this.reserv_row = reserv_row;
		this.reserv_col = reserv_col;
	}
	public int getReservNo() {
		return reservNo;
	}
	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
	}
	public int getInningNo() {
		return inningNo;
	}
	public void setInningNo(int inningNo) {
		this.inningNo = inningNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieTime() {
		return movieTime;
	}
	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public int getReserv_row() {
		return reserv_row;
	}
	public void setReserv_row(int reserv_row) {
		this.reserv_row = reserv_row;
	}
	public int getReserv_col() {
		return reserv_col;
	}
	public void setReserv_col(int reserv_col) {
		this.reserv_col = reserv_col;
	}
	
	@Override
	public String toString() {
		return "ReservationVO [reservNo=" + reservNo + ", inningNo=" + inningNo + ", userNo=" + userNo + ", movieTitle="
				+ movieTitle + ", movieTime=" + movieTime + ", theaterName=" + theaterName + ", reserv_row="
				+ reserv_row + ", reserv_col=" + reserv_col + "]";
	}
	
}
