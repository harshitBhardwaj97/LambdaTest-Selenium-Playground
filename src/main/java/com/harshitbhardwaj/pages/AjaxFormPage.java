package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AjaxFormPage {

    private final WebDriver driver;

    @FindBy(id = "title")
    @CacheLookup
    private WebElement nameInput;

    @FindBy(id = "description")
    @CacheLookup
    private WebElement messageInput;

    @FindBy(id = "btn-submit")
    @CacheLookup
    private WebElement submitBtn;

    @FindBy(id = "submit-control")
    @CacheLookup
    private WebElement submitDiv;

    public AjaxFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name) {
        nameInput.sendKeys(name);
    }

    public void enterMessage(String message) {
        messageInput.sendKeys(message);
    }

    public String getStyleOfEmptyNameField() {
        clickOnSubmitButton();
        return nameInput.getAttribute("style");
    }

    public void clickOnSubmitButton() {
        submitBtn.click();
    }

    public boolean checkSubmitControlDivContent() {
        String divInnerHtml = submitDiv.getAttribute("innerHTML");
        boolean isImagePresent = divInnerHtml.contains("bx_loader.gif");
        boolean isTextPresent = divInnerHtml.contains("Ajax Request is Processing!");
        return isImagePresent && isTextPresent;
    }

}
