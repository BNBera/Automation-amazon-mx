package quickstart.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constants.TimeOuts;

public class AmazonCartPage  {

	WebDriver driver;

    @FindBy(xpath="//*[@id='sw-subtotal']/span[2]/span/span[2]/span[2]")
    WebElement subtotal;

	public AmazonCartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public  String subtotalValue() {

		WebDriverWait wait= new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(subtotal));
		
		return subtotal.getText().toString();
	}

}
