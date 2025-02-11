package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckboxDemoPage {

    private final WebDriver driver;

    @FindBy(id = "isAgeSelected")
    private WebElement firstCheckBox;

    @FindBy(id = "txtAge")
    private WebElement firstCheckBoxDisplayText;

    @FindBy(xpath = "//div[@class='pb-10']//div[3]//input[1]")
    private WebElement firstDisabledCheckBox;

    @FindBy(xpath = "//div[@class='pb-10']//div[4]//input[1]")
    private WebElement secondDisabledCheckBox;

    public CheckboxDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCheckBox(WebElement checkbox) {
        checkbox.click();
    }

    public boolean checkFirstCheckBoxTextIsDisplayed() {
        return firstCheckBoxDisplayText.isDisplayed();
    }

    public boolean checkWhetherCheckBoxIsDisabled(WebElement checkbox) {
        return checkbox.isEnabled();
    }

    public boolean checkWhetherCheckBoxIsSelected(WebElement checkbox) {
        return checkbox.isSelected();
    }

    public WebElement getFirstCheckBox() {
        return firstCheckBox;
    }

    public WebElement getFirstDisabledCheckBox() {
        return firstDisabledCheckBox;
    }

    public WebElement getSecondDisabledCheckBox() {
        return secondDisabledCheckBox;
    }

}
