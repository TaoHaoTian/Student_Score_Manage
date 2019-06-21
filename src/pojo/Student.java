package pojo;

public class Student {
	private int sid;
	private String sname;
	private double score1;
	private double score2;
	private double score3;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Student() {
		
	}
	public Student(int sid, String sname, double score1, double score2, double score3) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", score1=" + score1 + ", score2=" + score2 + ", score3="
				+ score3 + "]";
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public double getScore1() {
		return score1;
	}
	public void setScore1(double score1) {
		this.score1 = score1;
	}
	public double getScore2() {
		return score2;
	}
	public void setScore2(double score2) {
		this.score2 = score2;
	}
	public double getScore3() {
		return score3;
	}
	public void setScore3(double score3) {
		this.score3 = score3;
	}
}
