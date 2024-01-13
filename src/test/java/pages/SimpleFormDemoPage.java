package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class SimpleFormDemoPage extends Base {

	@FindBy(id = "user-message")
	private WebElement singleInputField;

	@FindBy(id = "showInput")
	private WebElement getCheckedValueButton;

	@FindBy(id = "message")
	private WebElement messageValue;

	@FindBy(id = "sum1")
	private WebElement firstSumInputField;

	@FindBy(id = "sum2")
	private WebElement secondSumInputField;

	@FindBy(xpath = "//button[.='Get Sum']")
	private WebElement getSumButton;

	@FindBy(id = "addmessage")
	private WebElement sumResultOrMessage;

	public String getMessageValue() {
		return messageValue.getText();
	}

	public void enterMessage(String message) {
		singleInputField.sendKeys(message);
	}

	public void clearSingleInputField() {
		singleInputField.clear();
	}

	public void enterSumValue(String value, WebElement sumInputField) {
		sumInputField.clear();
		sumInputField.sendKeys(value);
	}

	public void clickOnGetCheckedValueButton() {
		getCheckedValueButton.click();
	}

	public void clickOnGetSumButton() {
		getSumButton.click();
	}

	public WebElement getFirstSumInputField() {
		return firstSumInputField;
	}

	public WebElement getSecondSumInputField() {
		return secondSumInputField;
	}

	public String getSumOrMessageValue() {
		return sumResultOrMessage.getText();
	}

	public SimpleFormDemoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
