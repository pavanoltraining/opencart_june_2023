package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;   // Log4j

import java.util.Date;
import java.util.ResourceBundle;   // loading properties file

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle rb; // properties file
	
	@BeforeClass(groups= {"Master","Sanity","Regression"}) //step8
	@Parameters("browser")
	public void setup(String br)
	{
		
		rb=ResourceBundle.getBundle("config"); // reading data from properties file
		
		logger=LogManager.getLogger(this.getClass()); //log4j
		
		if(br.equalsIgnoreCase("chrome"))
		{
			logger.info("Launching Chrome Browser..");
			
			ChromeOptions options = new ChromeOptions(); 
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
					
			driver=new ChromeDriver(options);
		}
		else if(br.equalsIgnoreCase("edge"))
		{
			logger.info("Launching Edge Browser..");
			EdgeOptions options = new EdgeOptions(); 
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
			driver=new EdgeDriver();
		}
		else if(br.equalsIgnoreCase("firefox"))
		{
			logger.info("Launching Firefox Browser..");
			driver=new FirefoxDriver();
		}
		else
		{
			logger.info("Launching Safari Browser..");
			driver=new SafariDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(rb.getString("appURL"));   // local app URL
		//driver.get("https://tutorialsninja.com/demo");  
		//driver.get("https://demo.opencart.com/index.php");   // remote App URL
		
		
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown()
	{
		logger.info("Closing application...");
		driver.quit();
	}
	
	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomeAlphaNumeric()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(5);
		return(generatedString+generatedNumber);
		
	}
	
	public String captureScreen(String tname) throws IOException {

		/*Date dt=new Date();
		SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddhhmmss");
		String timestamp=sp.format(dt);
		*/
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
}




