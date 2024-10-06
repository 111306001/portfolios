
public class Employee {
	
	private String name;
	private int wage;
	private int workDay;
	private int overWork;
	private String title;
	
	public Employee(String title, String name, int wage) {
		this.title = title;
		this.name = name;
		this.wage = wage;
	}
	
	public void setOverWork(int hour) {
		this.overWork = hour - 8;
	}
	public void setWorkDay() {
		workDay++;
	}
	public String getName() {
		return name;
	}
	public int getWage() {
		return wage;
	}
	public int getWorkDay() {
		return workDay;
	}
	public int getOverWork() {
		return overWork;
	}
	public String getTitle() {
		return title;
	}
	public int payment() {
		// TODO Auto-generated method stub
		int payment = 0;
		payment = wage * getWorkDay();
		if(overWork > 0) {
			payment = (int) (payment + (wage / 8) * overWork * 1.5) ;
			return payment;
		}
		else {
			return payment;
		}
	}

}
