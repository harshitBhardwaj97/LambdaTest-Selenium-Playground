package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class AutoHealingPage extends Base {

	WebDriver driver;

	@FindBy(xpath = "//input[@placeholder='username']")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[.='Submit']")
	private WebElement submitBtn;

	@FindBy(xpath = "//p[.='Change DOM ID']")
	private WebElement changeDOMId;

	@FindBy(xpath = "//p[.='Login Successful']")
	private WebElement successfulLoginMessage;

	public void enterUserName(String username) {
		usernameInput.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public void clickOnSubmitButton() {
		submitBtn.click();
	}

	public void clickOnChangeDOMIDButton() {
		changeDOMId.click();
	}

	public String checkUserNameDomId() {
		return usernameInput.getAttribute("id");
	}

	public boolean checkLoginSuccessfulMessageIsDisplayed() {
		return successfulLoginMessage.isDisplayed();
	}

	public AutoHealingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
