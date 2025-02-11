package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.ContextMenuPage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenuTests extends Base {

    WebDriver driver;
    ContextMenuPage contextMenuPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        contextMenuPage = new ContextMenuPage(driver);
        homePage.navigateToContextMenuPage();
    }

    @Test(priority = 1)
    public void verifyAlertIsDisplayed_whenContextClickIsPerformed() {
        contextMenuPage.performContextClickInTargetDiv();
        Assert.assertTrue(contextMenuPage.checkAfterContextClickAlertIsDisplayed());
    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
