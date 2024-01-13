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

	@FindBy(linkText = "Auto Healing")
	@CacheLookup
	private WebElement autoHealingLink;

	@FindBy(linkText = "Broken Image")
	@CacheLookup
	private WebElement brokenImageLink;

	public void navigateToAjaxFormPage() {
		ajaxFormLink.click();
	}

	public void navigateToAutoHealingPage() {
		autoHealingLink.click();
	}

	public void navigateToBrokenImageLinkPage() {
		brokenImageLink.click();
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
