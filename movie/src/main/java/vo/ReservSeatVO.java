package vo;

public class ReservSeatVO {
	private int reservNo;
	private int reservRow;
	private int reservCol;
	public ReservSeatVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservSeatVO(int reservNo, int reservRow, int reservCol) {
		super();
		this.reservNo = reservNo;
		this.reservRow = reservRow;
		this.reservCol = reservCol;
	}
	public int getReservNo() {
		return reservNo;
	}
	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
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
		return "ReservSeat [reservNo=" + reservNo + ", reservRow=" + reservRow + ", reservCol=" + reservCol + "]";
	}
	
	
}
