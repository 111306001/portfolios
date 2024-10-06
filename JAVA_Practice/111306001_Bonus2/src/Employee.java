
public class Employee {
	
	private String name;
	private int onboardYear;
	private int seniority;
	protected double salary;
	
	Employee(String name, int onboardYear){
		this.name = name;
		this.onboardYear = onboardYear;
	}
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	public int getOnboardYear() {
		return onboardYear;
	}
	public int getSeniority() {
		return seniority;
	}
	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}
	
	
	
}
