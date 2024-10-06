
public class Manager extends Employee{
	
	private double bonusRate;
	
	public Manager(String title, String name, int wage, double bonusRate) {
		super(title, name, wage);
		this.bonusRate = bonusRate;
	}
	public int payment() {
		// TODO Auto-generated method stub
		int managerWage = super.payment();
		managerWage *= bonusRate;
		return managerWage;
	}

}
