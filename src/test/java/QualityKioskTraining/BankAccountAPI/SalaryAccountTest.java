package QualityKioskTraining.BankAccountAPI;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import QualityKioskTraining.AccountSystemExceptions.InsufficientBalanceException;
import QualityKioskTraining.TypesOfAccounts.SalaryAccount;

public class SalaryAccountTest {

	SalaryAccount salaryAccount;
	
	double result;
	
	@BeforeClass
	public void init() {
		salaryAccount =new SalaryAccount(7);
	}
	@BeforeGroups("smokeTest")
	public void beforeGroup() {
		salaryAccount =new SalaryAccount(6);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void reInitializeBalance() {
		salaryAccount.setBalance(5000);
		result=0;
	}
	
	@Test(groups="smokeTest")
	public void salaryAccountWithdrawTest() {
		try {
			result=salaryAccount.WithdrawAmount(7000);
			Assert.assertEquals(result,0,"Maintain balance is not sufficient");
		} catch (InsufficientBalanceException e) {}
	}
	
		
	@AfterMethod(alwaysRun=true)
	public void reInitializeVar() {
		result=0;
	}
	
	@AfterClass
	public void tearDown() {
		salaryAccount=null;
	}


}
