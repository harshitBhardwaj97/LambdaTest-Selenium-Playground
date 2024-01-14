package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class KeyPressPage extends Base {

	@FindBy(id = "my_field")
	private WebElement keyInputField;

	@FindBy(id = "result")
	private WebElement keyPressedResult;

	public boolean enterAlphaNumKeyInInputFieldAndVerifyMessage(String keyToBeEntered) {
		keyInputField.sendKeys(keyToBeEntered);
		return keyPressedResult.getText().equals("You entered: " + keyToBeEntered);
	}

	public KeyPressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
