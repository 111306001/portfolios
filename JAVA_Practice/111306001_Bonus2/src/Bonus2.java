import java.util.ArrayList;

public class Bonus2 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SalaryEmployee John = new SalaryEmployee ("John", 2016, 45000);
		SalaryEmployee Peter = new SalaryEmployee  ("Peter", 2020, 55000);
		HourlyEmployee Jason = new HourlyEmployee  ("Jason", 2017, 400, 80);
		
		ArrayList <Employee> employees = new ArrayList <Employee>();
		employees.add(John);
		employees.add(Peter);
		employees.add(Jason);
		
		for(Employee workers : employees) {
			presentResult(workers, 2021);
		}
		System.out.println();
		for(Employee workers : employees) {
			presentResult(workers, 2022);
		}
	}
	public static void presentResult(Employee employees, int year){
		employees.setSeniority(year - employees.getOnboardYear());
		if(employees.getClass() == SalaryEmployee.class) {
			((SalaryEmployee)employees).setSalary();
		}
		else if(employees.getClass() == HourlyEmployee.class){
			((HourlyEmployee)employees).setSalary();
		}
		String info = employees.getName() + "'s salary for " + year + ": ";
		System.out.print(info);
		System.out.printf("%.0f", employees.getSalary());
		System.out.println();
	}
}
