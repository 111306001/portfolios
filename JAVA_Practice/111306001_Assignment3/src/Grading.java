
public class Grading {

	private int passMark;
	
	public Grading(int passMark){
		this.passMark = passMark;
		return;
	}
	
	public String toLetterGrade(int score) {
		// TODO Auto-generated method stub
		
		String letterGrade = null;
		if(80 <= score && score <= 100) {
			 letterGrade = "A";
		}
		else if(70 <= score && score <= 79) {
			 letterGrade = "B";
		}
		else if(60 <= score && score <= 69) {
			 letterGrade = "C";
		}
		else if(50 <= score && score <= 59) {
			 letterGrade = "D";
		}
		else if(1 <= score && score <= 49) {
			 letterGrade = "E";
		}
		else if(score == 0) {
			 letterGrade = "X";
		}
		return letterGrade;
	}
	public double calculateAvg(int[] grades) {
		double average = 0;
		
		for(int i = 0; i < 5; i++) {
			if(grades[i] != 0) {
				average += grades[i];
				
			}
		}
		average /= 5;
		return average;
	}
	public String summarizeGrade(int[] grades) {
		int pass = 0;
		int failed = 0;
		  	for (int i = 0; i < 5; i++) {
		  		if (grades[i] >= passMark) {
		  			pass++;
		  		} else {
		  			failed++;
		  		}
		  	}
		String letter = "";
		for(int i = 0; i < 5; i++) {
			letter += toLetterGrade(grades[i]) + " ";
		}
		String result = "Avg. Score: " + String.format("%.2f",this.calculateAvg(grades)) + "\n" + "Pass: " + pass + ", Failed: "  + failed + "\n" + "All Grades Letter: " + letter + "\n";
		return result;
	}
	
}
