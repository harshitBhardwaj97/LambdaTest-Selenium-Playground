package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.base.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavascriptAlertsPage extends Base {

    private final WebDriver driver;
    private final WebDriverWait wait;

    Alert regularAlert;
    Alert confirmBoxAlert;
    Alert promptBoxAlert;

    @FindBy(xpath = "//div[@class='w-full']/div[1]/p/button")
    private WebElement regularAlertButton;

    @FindBy(xpath = "//div[@class='w-full']/div[2]/div/p/button")
    private WebElement confirmBoxAlertButton;

    @FindBy(xpath = "//div[@class='w-full']/div[3]/p/button")
    private WebElement promptBoxAlertButton;

    @FindBy(id = "confirm-demo")
    private WebElement confirmBoxMessage;

    @FindBy(id = "prompt-demo")
    private WebElement promptBoxMessage;

    public JavascriptAlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickOnRegularAlertButton() {
        regularAlertButton.click();
    }

    public void clickOnConfirmBoxAlertButton() {
        confirmBoxAlertButton.click();
    }

    public void clickOnPromptBoxAlertButton() {
        promptBoxAlertButton.click();
    }

    public boolean checkRegularAlertBoxIsDisplayed() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());

            regularAlert = driver.switchTo().alert();
            regularAlert.accept();

            return true; // Alert is displayed
        } catch (NoAlertPresentException e) {
            // No alert is displayed
            return false;
        }

    }

    public String getConfirmAlertReturnMessage(String acceptOrDismiss) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());

            confirmBoxAlert = driver.switchTo().alert();

            if (acceptOrDismiss.toLowerCase().contains("accept")) {
                confirmBoxAlert.accept(); // Click on OK
            } else {
                confirmBoxAlert.dismiss();
            }

            return confirmBoxMessage.getText();
        } catch (TimeoutException e) {
            // Handle the scenario where the alert is not displayed
            return "Confirm Alert Box is not displayed.";
        }
    }

    public String enterPromptTextAndReturnMessage(String text) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());

            promptBoxAlert = driver.switchTo().alert();

            // Set a value in the prompt
            promptBoxAlert.sendKeys(text);

            // Accept the prompt (click OK)
            promptBoxAlert.accept();

            return "You have entered '" + text + "' !";
        } catch (TimeoutException e) {
            // Handle the scenario where the alert is not displayed
            return "Alert is not displayed. Cannot enter text.";
        }
    }

}
