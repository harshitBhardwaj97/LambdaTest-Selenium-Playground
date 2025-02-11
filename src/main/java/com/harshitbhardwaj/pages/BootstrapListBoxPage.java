package com.harshitbhardwaj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BootstrapListBoxPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@type='text' and ancestor::div[contains(@class,'list-left')]]")
    private WebElement leftSearchInput;

    @FindBy(xpath = "//input[@type='text' and ancestor::div[contains(@class,'list-right')]]")
    private WebElement rightSearchInput;

    @FindBy(xpath = "//ul[contains(@class,'list-group') and ancestor::div[contains(@class,'list-left')]]/li")
    private List<WebElement> leftBoxItems;

    @FindBy(xpath = "//ul[contains(@class,'list-group') and ancestor::div[contains(@class,'list-right')]]/li")
    private List<WebElement> rightBoxItems;

    @FindBy(xpath = "//div[contains(@class,'list-arrows')]/button[1]")
    private WebElement moveItemToLeftBoxButton;

    @FindBy(xpath = "//div[contains(@class,'list-arrows')]/button[2]")
    private WebElement moveItemToRightBoxButton;

    public BootstrapListBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebElement getLeftSearchInput() {
        return leftSearchInput;
    }

    public WebElement getRightSearchInput() {
        return rightSearchInput;
    }

    public void enterTextInSearchBox(String text, WebElement searchBox) {
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    public boolean checkTheItemIsPresentInLeftList(String itemName) {
        return leftBoxItems.stream().filter(item -> item.isDisplayed()).map(item -> item.getText())
                .anyMatch(text -> text.contains(itemName));
    }

    public boolean checkTheItemIsPresentInRightList(String itemName) {
        return rightBoxItems.stream().filter(item -> item.isDisplayed()).map(item -> item.getText())
                .anyMatch(text -> text.contains(itemName));
    }

    public void clickOnTransferButton(WebElement transferButton) {
        transferButton.click();
    }

    public WebElement getLeftTransferButton() {
        return moveItemToLeftBoxButton;
    }

    public WebElement getRightTransferButton() {
        return moveItemToRightBoxButton;
    }

    public void clickOnLeftBoxItemForTransfer(String itemName) {
        driver.findElement(
                        By.xpath("//ul[contains(@class,'list-group') and ancestor::div[contains(@class,'list-left')]]/li[.='"
                                + itemName + "']"))
                .click();
    }

    public void clickOnRightBoxItemForTransfer(String itemName) {
        driver.findElement(
                        By.xpath("//ul[contains(@class,'list-group') and ancestor::div[contains(@class,'list-right')]]/li[.='"
                                + itemName + "']"))
                .click();
    }

    public int getLengthOfLeftListDisplayedItems() {
        return (int) leftBoxItems.stream().filter(item -> item.isDisplayed()).count();
    }

    public int getLengthOfRightListDisplayedItems() {
        return (int) rightBoxItems.stream().filter(item -> item.isDisplayed()).count();
    }

}
