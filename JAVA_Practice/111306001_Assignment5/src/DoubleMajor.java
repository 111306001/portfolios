
public class DoubleMajor extends Student{
	
	private String major2;
	
	public DoubleMajor(int ID, String name, String major, int enrolledYear, String tutorName, double
			grade, String major2Name) {
		super(ID, name, major, enrolledYear, tutorName, grade);
		this.major2 = major2Name;
	}
	public String getMajor2() {
		return major2;
	}
	
	public String getInfo() {
		String info = "DoubleMajor[ID=" + super.getID() + ", name=" + super.getName() + ", major=" + super.getMajor() + ", major2=" + major2
				+ ", enrolledYear=" + super.getEnrolledYear() + ", grade=" + String.format("%.2f",super.getGrade()) + "]";
		return info;
	}

}
