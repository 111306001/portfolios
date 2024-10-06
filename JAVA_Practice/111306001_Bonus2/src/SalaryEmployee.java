
public class SalaryEmployee extends Employee{
	
	private double avgSalary;
	private double raiseRate;
	
	SalaryEmployee(String name, int onboardYear, double avgSalary){
		super(name, onboardYear);
		this.avgSalary = avgSalary;
		setSalary();
	}
	public void setSalary() {
		setRaiseRate();
		salary = raiseRate * avgSalary;
	}
	public void setRaiseRate() {
		this.raiseRate = 1 + ((double)super.getSeniority() / 100);
	}
}
