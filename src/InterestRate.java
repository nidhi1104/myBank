
public class InterestRate {

	private AccountType accountType;
	
	private double rateOfInterest;
	
	public InterestRate(AccountType accountType) {
		if(AccountType.Current==accountType)
			this.rateOfInterest=0.03;
		else if(AccountType.Saving==accountType)
			this.rateOfInterest=0.06;
		else
			this.rateOfInterest=0.10;
	}
	
	public double getRateOfInterest() {
		return this.rateOfInterest;
	}
	
}
