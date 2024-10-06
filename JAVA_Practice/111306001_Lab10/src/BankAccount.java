
public class BankAccount {
	
	private int ID;
	private double balance;
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void deposit(double amount) {
		balance += amount;
	}
	public void withdraw(double amount) {
		if(balance >= amount) {
			balance -= amount;
		}
		else {
			System.out.println("Your account does not have enough money.");
		}
	}
	public void monthEnd() {
		
	}
	public double getBalance() {
		return balance;
	}
}
