package vo;

public class TheaterVO {
	private int theaterNo;
	private String theaterName;
	private int seatRow;
	private int seatCol;
	public TheaterVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TheaterVO(int theaterNo, String theaterName, int seatRow, int seatCol) {
		super();
		this.theaterNo = theaterNo;
		this.theaterName = theaterName;
		this.seatRow = seatRow;
		this.seatCol = seatCol;
	}
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public int getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	public int getSeatCol() {
		return seatCol;
	}
	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}
	@Override
	public String toString() {
		return "Theater [theaterNo=" + theaterNo + ", theaterName=" + theaterName + ", seatRow=" + seatRow
				+ ", seatCol=" + seatCol + "]";
	}
	
	
}
