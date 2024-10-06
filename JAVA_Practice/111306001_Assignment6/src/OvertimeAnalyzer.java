import java.util.ArrayList;

public class OvertimeAnalyzer implements Analyzer{
	
	private ArrayList <Employee> employees;

	@Override
	public void addE(ArrayList<Employee> employees) {
		// TODO Auto-generated method stub
		this.employees = employees;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		int number = 0;
		for(Employee employee : employees) {
			if(employee.getOverWork() > 0) {
				number++;
			}
		}
		return number;
	}

	@Override
	public int sum() {
		// TODO Auto-generated method stub
		int sum = 0;
		for(Employee employee : employees) {
			sum += employee.getOverWork();
		}
		return sum;
	}

	@Override
	public double avg() {
		// TODO Auto-generated method stub
		return (double)sum() / (double)count();
	}

	@Override
	public int max() {
		// TODO Auto-generated method stub
		int maxValue = 0;
		for(Employee employee : employees) {
			if(maxValue < employee.getOverWork()) {
				maxValue = employee.getOverWork();
			}
		}
		return maxValue;

	}

	@Override
	public void getInfo() {
		// TODO Auto-generated method stub
		System.out.println(String.format("%20s", "Employees:") + String.format("%10s", count()));
		System.out.println(String.format("%20s", "Total hours:") + String.format("%10s", sum()));
		System.out.println(String.format("%20s", "Average hours:") + String.format("%10.2f",avg()));
		System.out.println(String.format("%20s", "Max hours:") + String.format("%10s", max()));
	}
	
	
}
