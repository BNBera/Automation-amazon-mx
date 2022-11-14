package quickstart.Tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import functionLibrary.ReportLog;
import functionLibrary.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import quickstart.Pages.AmazonCartPage;
import quickstart.Pages.AmazonProductPage;
import quickstart.Pages.AmazonSearchPage;


public class DemoTest {
	WebDriver driver;
	AmazonSearchPage amazonSearch ;
	AmazonProductPage amazonProduct ;
	AmazonCartPage amazonCart;
	ReportLog logger;

	TestData testdata;

	@BeforeSuite
	public void startTestSuite() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		logger= new ReportLog(driver);
		String currDir= System.getProperty("user.dir");
		try {
			testdata= new TestData(currDir+"\\TestData\\searchData.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Issue in Test data file opening; you can do some operation here, may be exit test");
		}
	}
	@BeforeMethod
	public void startTest(Method m) {
		logger.startTest(m.getName());
		driver.get("https://www.amazon.com.mx/");
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void endTest() {
		logger.endTest();

	}

	@AfterSuite
	public void endTestSuite() {
		logger.endTestSuite();
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public  void AmazonSearch_automationScript() throws IOException, InterruptedException {

		amazonSearch= new AmazonSearchPage(driver);
		amazonProduct= new AmazonProductPage(driver);
		amazonCart= new AmazonCartPage(driver);
		
		logger.logInfo("Sample Test data fetched from test data file for search text= "+testdata.getTestData("1", "Keyword"));
		logger.logInfo("Sample Test data fetched from test data file for result 1= "+testdata.getTestData("2", "Keyword"));
		logger.logInfo("Sample Test data fetched from test data file for result 2= "+testdata.getTestData("3", "Keyword"));
		String Searchkey = testdata.getTestData("1", "Keyword");
		String item1 = testdata.getTestData("2", "Keyword");
		//Search refrigerator keyword
		amazonSearch.searchKeyword(Searchkey);	
		
		boolean isDisplayed= amazonSearch.isLinkDisplayed(item1);		
		if(isDisplayed==true) {
			logger.logPass(item1+" is displayed on Amazon Search results page 1");
		}else {
			logger.logFail(item1+" is not displayed on Amazon Search results page 1");
		}
		
		//copy search result page URL
		String searchResultPageURL= driver.getCurrentUrl();
		
		//Clicking on first Samsung product
		amazonSearch.ClickOnFirstResult_Samsung();
		
		//Clicking on add to cart button
		amazonProduct.ClickOn_addToCartButton();
		
		//Clicking on no gracias button if exists
//		amazonProduct.ClickOn_NoGraciasButton();
		
		//Navigate back to Search result page
		driver.get(searchResultPageURL);
		
		//Clicking on first Mabe product
		amazonSearch.ClickOnFirstResult_Mabe();
		
		//Clicking on add to cart button
		amazonProduct.ClickOn_addToCartButton();
		
		//Clicking on no gracias button if exists
		amazonProduct.ClickOn_NoGraciasButton();
		
		//Validation of total price
		String totalAmt = amazonCart.subtotalValue();
		System.out.println("Total amount is :"+totalAmt);
		double subTotal = Double.parseDouble(totalAmt.replaceAll(",", ""));
		
		System.out.println("Total amount in double is :"+subTotal);
		if(subTotal>10000.00) {
			logger.logPass("Total amount is greater than 10000");
		}else {
			System.out.println("Total amount is less than 10000");
			logger.logFail("Total amount is less than 10000");
		}
		Assert.assertTrue("", subTotal>10000.00);
	}
	

}
