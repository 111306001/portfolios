import java.util.ArrayList;
import java.util.Collection;

public class Company {
	
	private String name;
	private ArrayList <Employee> employees;
	
	public Company(String name){
		this.name = name;
		this.employees = new ArrayList <Employee>();
	}

	public void addE(Employee employee) {
		// TODO Auto-generated method stub
		employees.add(employee);
	}
	public void addWork(String name, int hour) {
		boolean done = false;
		for(Employee employee : employees) {
			if(employee.getName().equals(name)) {
				employee.setWorkDay();
				if(hour > 8) {
					employee.setOverWork(hour);
				}
				done = true;
			}
		}
		if(done == false) {
			System.out.println("The employee is not found.");
		}
	}
	public void callA() {
		
		Analyzer overtimeAnalyzer = new OvertimeAnalyzer();
		Analyzer wageAnalyzer = new WageAnalyzer();
		
		overtimeAnalyzer.addE(employees);
		wageAnalyzer.addE(employees);
		
		System.out.println("<Wage info>");
		wageAnalyzer.getInfo();
		System.out.println();
		
		System.out.println("<Over Work info>");
		overtimeAnalyzer.getInfo();
		
	}
	public void getInfo() {
		System.out.println("<Company: " + name + ">");
		System.out.println(String.format("%10s", "Name") + String.format("%10s", "WorkDay") + String.format("%10s", "OverTime") + String.format("%10s", "Wage") + String.format("%10s", "Title") );
		for(Employee employee : employees) {
			System.out.println(String.format("%10s", employee.getName()) + String.format("%10s", employee.getWorkDay()) + String.format("%10s", employee.getOverWork()) + String.format("%10s", employee.payment()) + String.format("%10s", employee.getTitle()) );
		}
		System.out.println();
	}
	
}
