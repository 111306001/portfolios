import java.util.ArrayList;

public class Student {
	
	int studentID;
	String studentName;
	ArrayList<Course> enrolledCourses;
	int currentCredits;
	int maxCredits;
	
	public Student(int studentID, String name) {
		this.studentID = studentID;
		this.studentName = name;
		this.currentCredits = 0;
		this.maxCredits = 25;
		this.enrolledCourses = new ArrayList<Course>() ;
	}
	public Student(int studentID, String name, int maxCredits) {
		this.studentID = studentID;
		this.studentName = name;
		this.maxCredits = maxCredits;
		this.currentCredits = 0;
		this.enrolledCourses = new ArrayList<Course>() ;
	}
	// 用id找course
	public Course getCourse(int id) {
		for(Course course : enrolledCourses) {
			if(course.getCourseID() == id) {
				return course;
			}
		}
		return null;
	}
	// 將課程加入選單
	public void enroll(Course course) {
		
		if(course.capacity < course.enrolled) {
			System.out.println("Course " + course.courseID + " is full");
			course.enrolled--;
		}
		else {
			if(currentCredits + course.credits >= maxCredits) {
				System.out.println("Student " + studentID + " cannot enroll in this course");
			}
			else {
				enrolledCourses.add(course);
				course.enrolled++;
				currentCredits += course.credits;
			}
		}
	}
	// 將課程從選單移除
	public void drop(int courseID) {
		if (getCourse(courseID) != null){
			Course courseget = getCourse(courseID);
			currentCredits -= courseget.credits;
			enrolledCourses.remove(courseget);
			courseget.drop();
		}
		
		else {
			System.out.println("Course " + courseID + " not found in student " + studentID);
		}
	}
	public void drop(Course course) {
		drop(course.courseID);
		course.enrolled--;
	}
	// 回傳結果
	public String getInfo() {
		String courseList = null;
		ArrayList <String> list = new ArrayList <String>();
		for(Course course : enrolledCourses) {
			courseList = course.courseID + " " + course.courseName + " " + course.credits + "\n";
			list.add(courseList);
		}
		
		String studentInfo = "Student ID: " + studentID + "\n"
				+ "Name: " + studentName + "\n" 
				+ "Credits: " + currentCredits + "/" + maxCredits + "\n" 
				+ "Course list: " + "\n";
		for(int i =0 ; i < list.size(); i++) {
			studentInfo += list.get(i);
		}
		
		return studentInfo;
	}
	
	
}
