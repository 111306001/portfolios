
public class SavingsAccount extends BankAccount {

	private double interestRate;
	
	public SavingsAccount (int amount, int ID, double interestRate) {
		super.setID(ID);
		super.deposit(amount);
		
		this.interestRate = interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public void monthEnd() {
		double newBalance = getBalance() * (1 + (interestRate / 100));
		setBalance(newBalance);
	}

}
