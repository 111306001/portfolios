import java.util.ArrayList;

public class Supervisor extends Employee{
	
	private ArrayList <Employee> subordinates;
	
	public Supervisor(int ID, String name, BankAccount account, String department, int baseSalary, int
			sales, ArrayList<Employee> subordinates) {
		super(ID, name, account, department, baseSalary, sales);
		this.subordinates = subordinates;
		for(Employee employee : subordinates ) {
			sales += employee.getSales();
			super.setSales(sales);
		}
	}
	

}
