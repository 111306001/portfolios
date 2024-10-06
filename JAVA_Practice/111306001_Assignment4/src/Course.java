import java.util.ArrayList;

public class Course {
	
	int courseID;
	String courseName;
	int credits;
	int capacity;
	int enrolled;
	
	ArrayList<Course> enrolledCourses = new ArrayList<Course>();
	
	public Course(int courseID, String name, int credits, int capacity) {
		this.courseID = courseID;
		this.courseName = name;
		this.credits = credits;
		this.capacity = capacity;
		this.enrolled = 0;
	}
	// 判斷已選人數是否超過上限
	public boolean isFull() {
		if(capacity <= enrolled) {
			return true;
		}
		else {
			return false;
		}
	}
	// 如果有人選，課程已選人數累加
	public void enroll() {
		for(Course course : enrolledCourses) {
			if(course.getCourseID() == courseID) {
			enrolled++;
			}
		}
	}
	// 如果有人退，課程已選人數累減
	public void drop() {
		for(Course course : enrolledCourses) {
			if(course.getCourseID() == courseID) {
				enrolled--;
			}
		}
		
	}
	// 回傳結果
	public String getInfo() {
		String info = courseID + " " + courseName + " " + credits + " " + enrolled + "/" + capacity;
		return info;
	}

	public int getCourseID() {
		// TODO Auto-generated method stub
		return courseID;
	}
}
