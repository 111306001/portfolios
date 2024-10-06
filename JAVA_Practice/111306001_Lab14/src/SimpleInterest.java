
public class SimpleInterest extends DepositInsurance{
	
	private double interestRate;
	
	public SimpleInterest(BankAccount account, double annuity, double interestRate, int expiration) {
		// TODO Auto-generated constructor stub
		super(account, annuity, expiration);
		this.interestRate = interestRate;
	}

	public void interestPayment() {
		// TODO Auto-generated method stub
		super.updateTotalValue(super.getAnnuity() * interestRate);
	}
	public void yearEnd() {
		if(super.getAnnuity() > super.getCurrentYear()) {
			interestPayment();
		}
		else {
			System.out.println("Deduction is not successful");
		}
	}
}
