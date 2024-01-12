package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class HomePage extends Base {

	WebDriver driver;

	@FindBy(linkText = "Ajax Form Submit")
	@CacheLookup
	private WebElement ajaxFormLink;

	public void navigateToAjaxFormPage() {
		ajaxFormLink.click();
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
