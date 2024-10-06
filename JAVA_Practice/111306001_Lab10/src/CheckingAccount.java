
public class CheckingAccount extends BankAccount {
	
	private int transCount;
	
	public CheckingAccount(int amount, int ID) {
		super.deposit(amount);
		
		super.setID(ID);
	}
	public void deposit(double amount) {
		super.deposit(amount);
		super.setBalance(getBalance() + amount);
	}
	public void withdraw(double amount) {
		super.withdraw(amount);
		super.setBalance(getBalance() - amount);
	}
	public void monthEnd() {
		double commissionFee = 5;
		if(transCount > 3) {
			double newBalance = super.getBalance() - commissionFee;
			super.setBalance(newBalance);
			transCount = 0;
		}
		
	}
}
