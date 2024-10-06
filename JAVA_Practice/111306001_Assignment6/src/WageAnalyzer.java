import java.util.ArrayList;

public class WageAnalyzer implements Analyzer{
	
	private ArrayList <Employee> employees;

	public void addE(ArrayList<Employee> employees) {
		// TODO Auto-generated method stub
		this.employees = employees;
	}

	public int count() {
		// TODO Auto-generated method stub
		int number = employees.size();
		return number;
	}

	public int sum() {
		// TODO Auto-generated method stub
		int sum = 0;
		for(Employee employee : employees) {
			sum += employee.payment();
		}
		return sum;
	}

	public double avg() {
		// TODO Auto-generated method stub
		return (double)sum() / (double)count();
	}

	public int max() {
		// TODO Auto-generated method stub
		int maxValue = 0;
		for(Employee employee : employees) {
			if(maxValue < employee.payment()) {
				maxValue = employee.payment();
			}
		}
		return maxValue;

	}

	public void getInfo() {
		// TODO Auto-generated method stub
		System.out.println(String.format("%20s", "Employees:") + String.format("%10s", count()));
		System.out.println(String.format("%20s", "Total payment:") + String.format("%10s", sum()));
		System.out.println(String.format("%20s", "Average payment:") + String.format("%10.2f", avg()));
		System.out.println(String.format("%20s", "Max payment:") + String.format("%10s", max()));
		
	}

}
