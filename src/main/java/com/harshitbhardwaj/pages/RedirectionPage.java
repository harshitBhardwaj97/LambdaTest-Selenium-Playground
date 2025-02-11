package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RedirectionPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[.='here']")
    private WebElement redirectionLink;

    public RedirectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnRedirectionLink() {
        redirectionLink.click();
    }

    public String getCurrentUrlOfPage() {
        return driver.getCurrentUrl();
    }

    public void waitForPageUrl(String pageUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(pageUrl));
    }

}
