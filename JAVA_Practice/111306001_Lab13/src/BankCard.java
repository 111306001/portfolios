
public class BankCard extends Card{
	
	private int expYear;
	
	public BankCard(String name, int year) {
		super(name);
		this.expYear = year;
	}
	public void replace(int year) {
		if(expYear < year) {
			this.expYear = year + 1;	
		}
		else {
			System.out.println("Your card has not expired.");
		}
	}
	public void getInfo() {
		System.out.println("\n" + "<BANK CARD INFO>");
		System.out.println("Name: " + super.getName());
		System.out.println("Expire year: " + expYear + "\n");
		
	}
}
