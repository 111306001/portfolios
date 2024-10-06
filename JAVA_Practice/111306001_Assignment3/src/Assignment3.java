
public class Assignment3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student stu1 = new Student("Jack");
		Grading grading1 = new Grading(60);
		Grading grading2 = new Grading(80);
	
		stu1.addGrade(100);
		stu1.addGrade(78);
		stu1.addGrade(55);
		stu1.addGrade(67);
		stu1.addGrade(98);
		stu1.addGrade(90);
		
		System.out.println("information:");
		System.out.println(stu1.info());
		System.out.println("grading1 summarizeGrade(...)");
		System.out.println(grading1.summarizeGrade(stu1.getGrades()));
		System.out.println("grading2 summarizeGrade(...)");
		System.out.println(grading2.summarizeGrade(stu1.getGrades()));
		
	}

}
