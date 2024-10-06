
public class BankAccount {
	
	private int ID;
	private double balance;
	
	public BankAccount(int ID, double balance) {
		this.ID = ID;
		this.balance = balance;
	}
	public int getID () {
		return ID;
	}
	public double getBalance() {
		return balance;
	}
	public void setID(int ID) {
		this.ID = ID;
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
	
}
