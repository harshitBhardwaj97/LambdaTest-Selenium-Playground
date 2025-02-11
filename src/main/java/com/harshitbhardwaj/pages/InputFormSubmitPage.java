package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class InputFormSubmitPage {

    private final WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "inputEmail4")
    private WebElement emailInput;

    @FindBy(id = "inputPassword4")
    private WebElement passwordInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "websitename")
    private WebElement websiteInput;

    @FindBy(xpath = "//select[@name='country']")
    private WebElement countrySelect;

    @FindBy(xpath = "//option[@value='IN']")
    private WebElement indiaCountrySelectionOption;

    @FindBy(id = "inputCity")
    private WebElement cityInput;

    @FindBy(id = "inputState")
    private WebElement stateInput;

    @FindBy(id = "inputAddress1")
    private WebElement addressOneInput;

    @FindBy(id = "inputAddress2")
    private WebElement addressTwoInput;

    @FindBy(id = "inputZip")
    private WebElement zipInput;

    @FindBy(xpath = "//button[@type='submit' and .='Submit']")
    private WebElement formSubmitButton;

    @FindBy(xpath = "//p[contains(@class,'success-msg')]")
    private WebElement formSubmitSuccessMessage;

    public InputFormSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnFormSubmitButton() {
        formSubmitButton.click();
    }

    public boolean checkRequiredAttributeIsPresentForInputField(WebElement inputField) {
        // Required attribute is present (Value is not null)
        return (!Objects.isNull(inputField.getAttribute("required")));
    }

    public Map<String, WebElement> getAllInputFields() {

        var inputFields = new LinkedHashMap<String, WebElement>();

        inputFields.put("name", nameInput);
        inputFields.put("email", emailInput);
        inputFields.put("password", passwordInput);
        inputFields.put("company", companyInput);
        inputFields.put("website", websiteInput);
        inputFields.put("city", cityInput);
        inputFields.put("addressOne", addressOneInput);
        inputFields.put("addressTwo", addressTwoInput);
        inputFields.put("state", stateInput);
        inputFields.put("zipCode", zipInput);

        return inputFields;
    }

    public boolean checkFormSubmitSuccessMessageIsDisplayed() {
        return formSubmitSuccessMessage.isDisplayed() && formSubmitSuccessMessage.getText()
                .equals("Thanks for contacting us, we will get back to you shortly.");
    }

    public void selectCountryOption() {
        Select coutnrySelectOption = new Select(countrySelect);
        coutnrySelectOption.selectByVisibleText("India");
    }

}
