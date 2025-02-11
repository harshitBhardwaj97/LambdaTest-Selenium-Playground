package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KeyPressPage {

    private final WebDriver driver;

    @FindBy(id = "my_field")
    private WebElement keyInputField;

    @FindBy(id = "result")
    private WebElement keyPressedResult;

    public KeyPressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean enterAlphaNumKeyInInputFieldAndVerifyMessage(String keyToBeEntered) {
        keyInputField.sendKeys(keyToBeEntered);
        return keyPressedResult.getText().equals("You entered: " + keyToBeEntered);
    }

}
