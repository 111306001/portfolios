import java.util.ArrayList;

public class CompoundInterest extends DepositInsurance{
	
	private ArrayList <Double> interestRate;
	
	public CompoundInterest(BankAccount account, double annuity, int expiration) {
		// TODO Auto-generated constructor stub 
		super(account, annuity, expiration);
		this.interestRate = new ArrayList <Double>();
	}

	public void addInterestRate (double rate) {
		// TODO Auto-generated method stub
		interestRate.add(rate);
	}
	public void interestPayment () {
		super.updateTotalValue(super.getAnnuity() * interestRate.get(getCurrentYear()));
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
