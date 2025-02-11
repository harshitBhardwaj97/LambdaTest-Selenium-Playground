package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdownDemoPage {

    private final WebDriver driver;

    @FindBy(id = "select-demo")
    @CacheLookup
    private WebElement firstSelect;

    @FindBy(xpath = "//p[contains(@class,'selected-value')]")
    @CacheLookup
    private WebElement firstSelectedDayValue;

    @FindBy(id = "multi-select")
    @CacheLookup
    private WebElement multiSelect;

    @FindBy(id = "printMe")
    @CacheLookup
    private WebElement firstSelectedMultiSelectButton;

    @FindBy(id = "printAll")
    @CacheLookup
    private WebElement lastSelectedMultiSelectButton;

    @FindBy(xpath = "//div[@class='w-4/12 smtablet:w-full rigth-input']/p[1]")
    @CacheLookup
    private WebElement firstMultiSelectValue;

    @FindBy(xpath = "//div[@class='w-4/12 smtablet:w-full rigth-input']/p[2]")
    @CacheLookup
    private WebElement lastMultiSelectValue;

    public SelectDropdownDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectDayOption(String dayName) {
        Select daySelectOption = new Select(firstSelect);
        daySelectOption.selectByVisibleText(dayName);
    }

    public void selectStateOption(String stateName) {
        Select stateSelectOption = new Select(multiSelect);
        stateSelectOption.selectByVisibleText(stateName);
    }

    public String getFirstSelectMessage() {
        return firstSelectedDayValue.getText();
    }

    public String getFirstSelectedStateMessage() {
        return firstMultiSelectValue.getText();
    }

    public String getLastSelectedStateMessage() {
        return lastMultiSelectValue.getText();
    }

    public boolean checkFirstSelectMessageIsDisplayed() {
        return firstSelectedDayValue.isDisplayed();
    }

    public void clickOnFirstSelectedButton() {
        firstSelectedMultiSelectButton.click();
    }

    public void clickOnLastSelectedButton() {
        lastSelectedMultiSelectButton.click();
    }

}
