import java.util.ArrayList;

public class Instructor extends Person{
	
	private String department;
	private ArrayList<String> lectureList;
	private ArrayList<Student> studentList;
	
	public Instructor (int ID, String name, String department) {
		super(ID,name);
		this.department = department;
		this.lectureList = new ArrayList<String>();
		this.studentList = new ArrayList<Student>();
	}
	public String getDepartment() {
		return department;
	}
	public String getLectureList() {
		String lectures = null;
		for(int i = 0; i < lectureList.size(); i++) {
			lectures += lectureList.get(i);
			for (int j = 0; j < lectureList.size() - 1; j ++) {
				lectures += ",";
			}
		}
		return lectures;
	}
	public String getStudentList() {
		String students = "";
		for(int i =0; i < studentList.size(); i++) {
			students += studentList.get(i);
			for (int j = 0; j < studentList.size() - 1; j++) {
				students += ", ";
			}
		}
		return students;
	}
	public void addLecture(String lectureName) {
		lectureList.add(lectureName);
	}
	public boolean addStudent(Student student) {
		if(student.getTutorName() == super.getName()) {
			studentList.add(student);
			return true;
		}
		else {
			return false;
		}
	}
	public String getStudentName() {
		String studentName = "";
		for(int i = 0; i < studentList.size(); i++) {
			studentName += studentList.get(i).getName();
			if(i < studentList.size() - 1) {
				studentName += ", ";
			}
		}
		return studentName;
	}
	public double getStudentAverage() {
		double average = 0;
		for(Student student : studentList) {
			average += student.getGrade();
		}
		return average / studentList.size();
	}
	public String getInfo() {
		String info = "Instructor[ID=" + super.getID() + ", name=" + super.getName() + ", department=" + department + ", lectureList=";
		for (int i = 0; i < lectureList.size(); i++) {
			info += lectureList.get(i) + ", ";
		}
		info += "studentList=" + getStudentName() + "]";
		return info;
	}
	
}
