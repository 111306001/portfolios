
public class Student extends Person{
	
	private String major;
	private String tutorName;
	private int enrolledYear;
	private double grade;
	
	public Student(int ID, String name, String major, int enrolledYear, String tutorName, double grade){
		super(ID,name);
		this.major = major;
		this.enrolledYear = enrolledYear;
		this.tutorName = tutorName;
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public String getTutorName() {
		return tutorName;
	}
	public int getEnrolledYear() {
		return enrolledYear;
	}
	public double getGrade() {
		return grade;
	}
	public String getInfo() {
		String info = "Student[ID=" + super.getID() + ", name=" + super.getName() + ", major=" + major + ", enrolledYear=" + enrolledYear + ", grade=" + String.format("%.2f",grade) + "]";
		return info;
	}
}
