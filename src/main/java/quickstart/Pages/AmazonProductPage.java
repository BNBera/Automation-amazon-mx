package quickstart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonProductPage  {

	WebDriver driver;



    @FindBy(xpath="//*[@id='add-to-cart-button']")
    WebElement addToCartButton;
    
    @FindBy(xpath="//*[@id='attachSiNoCoverage']/span/input")
    WebElement noGraciasButton;
 
	public AmazonProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	public void ClickOn_addToCartButton() throws InterruptedException {
		
		Thread.sleep(1000);
		addToCartButton.click();
	}
	
	
	public void ClickOn_NoGraciasButton() throws InterruptedException {
		
		Thread.sleep(1000);
		try {
		noGraciasButton.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
