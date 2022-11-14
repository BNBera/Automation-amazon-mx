package quickstart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constants.TimeOuts;

public class AmazonSearchPage  {

	WebDriver driver;

    @FindBy(xpath="//*[@id='twotabsearchtextbox']")
    WebElement searchTextbox;
    
    @FindBy(xpath="//*[@id='nav-search-submit-button']")
    WebElement searchButton;
    
    @FindBy(xpath="(//*[contains(text(),'Samsung')])[1]")
    WebElement firstResultSamsung;
    
    @FindBy(xpath="(//*[contains(text(),'Mabe')])[1]")
    WebElement firstResultMabe;
    
    
	/**
	 * Constructor of the Page class to set the driver.
	 * It is also used to initialize all the elements.
	 * @param driver
	 */
	public AmazonSearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//methods
	public void enterKeyWord(String keyword) {
		
		searchTextbox.sendKeys(keyword);
		searchTextbox.sendKeys(Keys.ENTER);
	}
	
	public  void searchKeyword(String keyword) {
		
		this.enterKeyWord(keyword);
		this.waitForResultLink(keyword);			
	}
	public void waitForResultLink(String keyword) {
		String searchResultLink="//*[contains(text(),'"+keyword+"')]";
		WebDriverWait wait= new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResultLink)));		
	}

	
	public  boolean isLinkDisplayed(String elementName) {

		String searchResultLink="//*[contains(text(),'"+elementName+"')]";
		WebDriverWait wait= new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		WebElement ele= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResultLink)));
		return ele.isDisplayed();
	}
	
	public void ClickOnFirstResult_Samsung() throws InterruptedException {
		//Scrolling in to view to the result element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstResultSamsung);
		Thread.sleep(1000);
		firstResultSamsung.click();
	}
	
	public void ClickOnFirstResult_Mabe() throws InterruptedException {
		//Scrolling in to view to the result element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstResultMabe);
		Thread.sleep(1000);
		firstResultMabe.click();
	}
	
}
