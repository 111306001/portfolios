
public class DepositInsurance {
	
	private BankAccount account;
	private double totalValue;
	private double annuity;
	private int expiration;
	private int currentYear;
	
	public DepositInsurance(BankAccount account, double annuity, int expiration) {
		// TODO Auto-generated constructor stub
		this.account = account;
		this.annuity = annuity;
		this.expiration = expiration;
		this.currentYear = 0;
		this.totalValue = 0;
	}

	public int getExpiration() {
		// TODO Auto-generated method stub
		return expiration;
	}
	public int getCurrentYear() {
		return currentYear;
	}
	public BankAccount getAccount() {
		return account;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public double getAnnuity() {
		return annuity;
	}
	public int updateCurrentYear() {
		currentYear++;
		return currentYear;
	}
	public void updateAnnuity(double amount) {
		annuity += amount;
	}
	public void updateTotalValue(double amount) {
		totalValue += amount;
	}
	public void redemption() {
		account.deposit(totalValue);
	}
	public void redemption(double redemptionRate) {
		account.deposit(totalValue * (1 - redemptionRate));
	}
	public void yearEnd() {
		
	}
	public String getInfo() {
		String info = "Designated Bank Account: " + account.getAccountID() 
		+ "\n" + "Current Total Value: " + String.format("%.2f", totalValue) 
		+ "\n" + "Elapsed Years: " + currentYear;
		return info;
	}
}
