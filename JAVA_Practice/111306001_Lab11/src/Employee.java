
public class Employee {
	
	private int ID;
	private double baseSalary;
	private double totalSalary;
	private int sales;
	private String name;
	private String department;
	private BankAccount account;
	
	public Employee(int ID, String name, BankAccount account, String department, int baseSalary, int sales) {
		this.ID = ID;
		this.name = name;
		this.account = account;
		this.department = department;
		this.baseSalary = baseSalary;
		this.sales = sales;
		this.totalSalary = 0;
	}
	public String getDepartment() {
		return department;
	}
	public int getSales() {
		return sales;
	}
	public double getTotalSalary() {
		return totalSalary;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public void monthEnd() {
		double taxRate = 0.03;
		int salesBonus = 500;
		totalSalary = (baseSalary + (salesBonus * sales)) * (1 - taxRate);
		account.deposit(totalSalary);
	}
	public String getInfo() {
		String info = "ID: " + ID + "\n" + "Name: " + name + "\n" + "Department: " + department + "\n" + "Total sales: " + sales + "\n" + "Total salary: " + totalSalary + "\n" + "Account amount: " + account.getBalance();
		return info;
	}
}
