package vo;

public class GradeVO {
	private int gradeNo;
	private String gradeName;
	private int gradeMinReserv;
	private int gradeMaxReserv;
	private double discountRate;
	public GradeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GradeVO(int gradeNo, String gradeName, int gradeMinReserv, int gradeMaxReserv, double discountRate) {
		super();
		this.gradeNo = gradeNo;
		this.gradeName = gradeName;
		this.gradeMinReserv = gradeMinReserv;
		this.gradeMaxReserv = gradeMaxReserv;
		this.discountRate = discountRate;
	}
	public int getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public int getGradeMinReserv() {
		return gradeMinReserv;
	}
	public void setGradeMinReserv(int gradeMinReserv) {
		this.gradeMinReserv = gradeMinReserv;
	}
	public int getGradeMaxReserv() {
		return gradeMaxReserv;
	}
	public void setGradeMaxReserv(int gradeMaxReserv) {
		this.gradeMaxReserv = gradeMaxReserv;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	
	@Override
	public String toString() {
		return "GradeVO [gradeNo=" + gradeNo + ", gradeName=" + gradeName + ", gradeMinReserv=" + gradeMinReserv
				+ ", gradeMaxReserv=" + gradeMaxReserv + ", discountRate=" + discountRate + "]";
	}
	
	
}
