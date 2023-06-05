package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	// Elements
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement lnkMyaccount;

	@FindBy(linkText = "Register")
	WebElement lnkRegister;

	@FindBy(linkText="Login")  // added in step 6 - Login link 
	WebElement linkLogin;
	
	
		// Action Methods
		public void clickMyAccount() {
			lnkMyaccount.click();
		}

		public void clickRegister() {
			lnkRegister.click();
		}
		
		public void clickLogin()   //added in step6
		{  
			linkLogin.click();
		}
	
}
