
public class Student {
	
	private String name;
	int gradesIndex = 0;
	private int[] grades = new int [5];
	
	public Student(String stuName) {
		this.name = stuName;
	}
	public int getGrade(int idx) {
		// TODO Auto-generated method stub
		return grades[idx];
		
	}
	public void addGrade(int grade) {
		
		if(gradesIndex >= 0 && gradesIndex < 5) {
			grades[gradesIndex] = grade;
			gradesIndex++;
		}
		else {
			System.out.println("Array index out of bounds.");	
		}
	}
	public String info(){
		String scores = "";
		for(int i = 0; i < 5; i++) {
			scores += grades[i] + " ";
		}
		String info = "Name: " + name + "\n" + "Grade: " + scores + "\n";
		return info;
	}
	public int[] getGrades() {
		return grades;
	}
	public void setGrades(int[] grades) {
		this.grades = grades;
	}

}
