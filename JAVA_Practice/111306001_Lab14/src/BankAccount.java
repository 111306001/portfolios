
public class BankAccount {
	
	private int accountID;
	private double balance;
	private double interestRate;
	
	public BankAccount(int accountID) {
		// TODO Auto-generated constructor stub
		this.accountID = accountID;
		this.balance = 0;
		this.interestRate = 0.001;
	}

	public int getAccountID() {
		// TODO Auto-generated method stub
		return accountID;
	}
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	public String deposit(double amount) {
		balance += amount;
		return "";
	}
	public boolean withdraw(double amount) {
		if(balance >= amount) {
			return true;
		}
		else {
			return false;
		}
	}
	public void updateInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public void interestPayment() {
		balance = balance * (1 + interestRate);
	}
	public void yearEnd() {
		
	}
	public String getInfo() {
		String info = "Account ID: " + accountID
				+ "\n" + "Balance: " + String.format("%.2f",balance) + "\n" ;
		return info;
	}

}
