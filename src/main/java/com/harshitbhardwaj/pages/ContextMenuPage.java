package com.harshitbhardwaj.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContextMenuPage {

    private final WebDriver driver;

    Alert regularAlert;
    Actions actions;
    WebDriverWait wait;

    @FindBy(id = "hot-spot")
    @CacheLookup
    private WebElement targetDiv;

    public ContextMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void performContextClickInTargetDiv() {
        actions = new Actions(driver);
        actions.contextClick(targetDiv).perform();
    }

    public boolean checkAfterContextClickAlertIsDisplayed() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());

            regularAlert = driver.switchTo().alert();

            // You selected a context menu
            System.out.println(regularAlert.getText());

            regularAlert.accept();
            return true; // Alert is displayed
        } catch (NoAlertPresentException e) {
            // No alert is displayed
            return false;
        }
    }

}
