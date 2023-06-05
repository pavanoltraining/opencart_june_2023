package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.AccountRegistrationPage;

public class TC_001_AccountRegistrationTest extends BaseClass{

	@Test(groups= {"Regression","Master"})   // step 8
	public void test_account_Registration()
	{
		logger.info("*********** starting TC_001_AccountRegistrationTest    *********");
		logger.debug("*** Generating debug logs...");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		
		hp.clickRegister(); // opens registration page
		logger.info("Clicked on Registerlink");
		
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details....");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		
		String pwd=randomeAlphaNumeric();
		regpage.setPassword(pwd);
		regpage.setConfirmPassword(pwd);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Clicked on Continue button..");
		
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("test passed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail();
		}
		
		logger.info("*********** Finished TC_001_AccountRegistrationTest    *********");
	}
	
}
