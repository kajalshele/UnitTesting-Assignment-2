package QualityKioskTraining.TypesOfAccounts;
import QualityKioskTraining.AccountSystem.Account;
public class SalaryAccount extends Account
{
	public SalaryAccount(int AccountNo)
	{
		super(AccountNo);
	}		
	
	public void OpenAccount()
	{
		System.out.println("Account opening will be done within 1 day");
	}
	
}