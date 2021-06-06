package QualityKioskTraining.TypesOfAccounts;
import QualityKioskTraining.AccountSystem.Account;
import QualityKioskTraining.AccountSystemExceptions.InsufficientBalanceException;
public class SavingsAccount extends Account
{
	
	public SavingsAccount(int ActNo,double Balance)
	{
		//AccountNo=ActNo;
		//Balance=Balance;	
		super(ActNo,Balance);
	}

	public double WithdrawAmount(double Amount)throws InsufficientBalanceException
	{
		//Re-implementing the process due to applicability of new rules
		//Overriding this method
		
		double AmountLeft=Balance-Amount;
		if(AmountLeft<1000)
			throw new InsufficientBalanceException();
		if(Amount<Balance)
			Balance=Balance-Amount;	
		else
			throw new InsufficientBalanceException();	
		
		
		return AmountLeft;
	}


	public void setBalance(double balance) {
		this.Balance=balance;
		
	}
	public void OpenAccount()
	{
		System.out.println("Submit KYC docs.");
		System.out.println("Account opening will take 8 working days.");
	}
}