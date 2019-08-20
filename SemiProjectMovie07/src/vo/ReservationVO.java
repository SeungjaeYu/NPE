package vo;

public class ReservationVO extends ReservSeatVO {
	
	private int reservNo;
	private int inningNo;
	private int userNo;
	private String movieTitle;
	private String movieTime;
	private String theaterName;
	private int reservRow;
	private int reservCol;
	public ReservationVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservationVO(int reservNo, int reservRow, int reservCol) {
		super(reservNo, reservRow, reservCol);
		// TODO Auto-generated constructor stub
	}
	public ReservationVO(int reservNo, int reservRow, int reservCol, int reservNo2, int inningNo, int userNo,
			String movieTitle, String movieTime, String theaterName, int reservRow2, int reservCol2) {
		super(reservNo, reservRow, reservCol);
		reservNo = reservNo2;
		this.inningNo = inningNo;
		this.userNo = userNo;
		this.movieTitle = movieTitle;
		this.movieTime = movieTime;
		this.theaterName = theaterName;
		reservRow = reservRow2;
		reservCol = reservCol2;
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
	public int getReservRow() {
		return reservRow;
	}
	public void setReservRow(int reservRow) {
		this.reservRow = reservRow;
	}
	public int getReservCol() {
		return reservCol;
	}
	public void setReservCol(int reservCol) {
		this.reservCol = reservCol;
	}
	@Override
	public String toString() {
		return "ReservationVO [reservNo=" + reservNo + ", inningNo=" + inningNo + ", userNo=" + userNo + ", movieTitle="
				+ movieTitle + ", movieTime=" + movieTime + ", theaterName=" + theaterName + ", reservRow=" + reservRow
				+ ", reservCol=" + reservCol + "]";
	}
	
	
	
	
	
	
}
