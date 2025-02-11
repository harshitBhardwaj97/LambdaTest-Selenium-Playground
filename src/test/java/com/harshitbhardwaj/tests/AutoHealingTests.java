package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.AutoHealingPage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutoHealingTests extends Base {

    WebDriver driver;
    AutoHealingPage autoHealingPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        autoHealingPage = new AutoHealingPage(driver);
        homePage.navigateToAutoHealingPage();
    }

    @Test(priority = 1)
    public void verifyLoginSuccessfulIsDisplayed_whenSubmitIsClickedWithData() {

        autoHealingPage.enterUserName("User");
        autoHealingPage.enterPassword("Password");
        autoHealingPage.clickOnSubmitButton();

        // Login successful message is displayed
        Assert.assertEquals(autoHealingPage.checkLoginSuccessfulMessageIsDisplayed(), true);

    }

    @Test(priority = 2)
    public void verifyDOMIdIsChanged_whenChangeDOMIsClicked() {

        autoHealingPage.enterUserName("User");
        autoHealingPage.enterPassword("Password");

        String initialDOMId = autoHealingPage.checkUserNameDomId();
        System.out.println("initialDOMId -> " + initialDOMId);

        // Check initalDOMId
        Assert.assertEquals(initialDOMId, "username");

        autoHealingPage.clickOnChangeDOMIDButton();

        String finalDOMId = autoHealingPage.checkUserNameDomId();
        System.out.println("finalDOMId -> " + finalDOMId);

        // Check finalDOMId
        Assert.assertEquals(finalDOMId, "lambdatest");

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
