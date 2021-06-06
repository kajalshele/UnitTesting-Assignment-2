package QualityKioskTraining.AccountSystem;
import QualityKioskTraining.AccountSystemExceptions.InsufficientBalanceException;
public abstract class Account
{
	private int AccountNo;
	protected double Balance;
	static final double InterestRate=10.00;	
	public Account(int ActNo)
	{
		AccountNo=ActNo;
		Balance=0.0;	
	}

	public Account(int AccountNo,double Bal)
	{
		this.AccountNo=AccountNo;
		Balance=Bal;
	}
	
	public double DepositeAmount(double Amount)
	{
		Balance=Balance+Amount;
		return Balance;
	}
	public double GetBalance()
	{
		return Balance;
	}

	public void setBalance(double balance) {
	
		this.Balance=balance;
	}
	public double WithdrawAmount(double Amount)throws InsufficientBalanceException
	{
	
		/*
		if(Balance>=Amount)
			Balance=Balance-Amount;
		else
		{
			System.out.println("Sorry..You do not have sufficient balance");
		}
		*/
		if(Balance<Amount)
			throw new InsufficientBalanceException();
		Balance=Balance-Amount;
		
		return Balance;
	}
	
	public int Get_AccountNo()
	{
		return AccountNo;
	}

	public double EarnInterest()
	{
		double InterestEarned=(Balance*InterestRate)/100;
		Balance=Balance+InterestEarned;
		
		return Balance;
		}

	public final void CloseAccount()
	{
		System.out.println("Submit the application.");
		System.out.println("Withdraw the whole balance.");
		System.out.println("Closing of an account may take 2 working days");
	}

	public abstract void OpenAccount();

}