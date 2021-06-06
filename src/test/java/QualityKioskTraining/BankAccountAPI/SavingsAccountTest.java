package QualityKioskTraining.BankAccountAPI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import QualityKioskTraining.AccountSystemExceptions.InsufficientBalanceException;
import QualityKioskTraining.TypesOfAccounts.*;

public class SavingsAccountTest {
	
	SavingsAccount savingAccount;

	double result;
	
	@BeforeClass
	public void init() {
		savingAccount =new SavingsAccount(3,1000);
	}
	@BeforeGroups("smokeTest")
	public void beforeGroup() {
		savingAccount =new SavingsAccount(6,1000);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void reInitializeBalance() {
		savingAccount.setBalance(1000);
		
	}
	
	@Test(dataProvider="bankSavingsData")
	public void depositeTestDataDriven(double amount) {
		double bal=savingAccount.GetBalance();
		result=savingAccount.DepositeAmount(amount);
		Assert.assertEquals(result,bal+amount,"Amount Not able to deposite");
	}

	@Test(groups="smokeTest")
	public void depositeTest() {
		result=savingAccount.DepositeAmount(2000);
		Assert.assertEquals(result,3000,"Amount Not able to deposite");
	}

	@Test(groups="smokeTest")
	public void savingAccountWithdrawTest() {
		try {
			result=savingAccount.WithdrawAmount(1000);
			Assert.assertEquals(result,1000,"Maintain balance is not sufficient");
		} catch (InsufficientBalanceException e) {}
	}
	
	@Test(groups="smokeTest")
	public void earnInterestTest() {
		result=savingAccount.EarnInterest();
		Assert.assertEquals(result,1100,"EarnInterest not added");

	}
	
	@DataProvider
	public Object[][] bankSavingsData() {
		return new Object[][] {
		{3000},
		{500},
		{50000}};
	}
	
	@AfterMethod(alwaysRun=true)
	public void reInitializeVar() {
		result=0;
	}
	
	@AfterClass
	public void tearDown() {
		savingAccount=null;
	}
	@BeforeSuite
	@Parameters({"RequestID"})
	public void createResultFolder(String RequestID) {
		try {
			Files.createDirectories(Paths.get("./"+RequestID));
		} catch (IOException e) {
		System.out.println("There is problem  to create folder");
		}
	}
	
	@AfterSuite
	@Parameters({"RequestID"})
	public void copyReportFile(String RequestID) {
		try {
			Files.copy(Paths.get(".\\target\\surefire-reports\\emailable-report.html"), Paths.get("./"+RequestID+"/Report.html"),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Not able to perform the task");}
	}

}