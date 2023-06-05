package testCases;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;


// This is Login test .....

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void test_Login()
	{
		logger.info("Starting TC_002_LoginTest.....");
		
		try
		{
		HomePage hp=new HomePage(driver);  // Home Page
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);  // Login Page
		lp.setEmail(rb.getString("email"));
		lp.setPassword(rb.getString("password"));
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		
		Assert.assertEquals(targetpage, true);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
				
		
		logger.info("Finished TC_002_LoginTest.....");
		
	}
	
	
}
