package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.pages.JavascriptAlertsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavascriptAlertsTests extends Base {

    WebDriver driver;
    JavascriptAlertsPage javascriptAlertsPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        javascriptAlertsPage = new JavascriptAlertsPage(driver);
        homePage.navigateToJavscriptAlertsPage();
    }

    @Test(priority = 1)
    public void verifyRegularAlertIsDisplayed() {

        // Open the regular alert box
        javascriptAlertsPage.clickOnRegularAlertButton();

        // Assert alert box message is displayed
        Assert.assertEquals(javascriptAlertsPage.checkRegularAlertBoxIsDisplayed(), true);

    }

    @Test(priority = 2)
    public void verifyConfirmBoxAlertMessageWhenAccepted() {

        String expectedMessage = "You pressed OK!";
        final String ACCEPT_ALERT = "accept";

        // Open the confirm alert box
        javascriptAlertsPage.clickOnConfirmBoxAlertButton();

        String confirmAlertResult = javascriptAlertsPage.getConfirmAlertReturnMessage(ACCEPT_ALERT);

        // Assert correct message is displayed for confirm box
        Assert.assertEquals(confirmAlertResult, expectedMessage);

    }

    @Test(priority = 3)
    public void verifyConfirmBoxAlertMessageWhenDismissed() {

        String expectedMessage = "You pressed Cancel!";
        final String DIMISS_ALERT = "dismiss";

        // Open the confirm alert box
        javascriptAlertsPage.clickOnConfirmBoxAlertButton();

        String dismissAlertResult = javascriptAlertsPage.getConfirmAlertReturnMessage(DIMISS_ALERT);

        // Assert correct message is displayed for confirm box
        Assert.assertEquals(dismissAlertResult, expectedMessage);

    }

    @Test(priority = 4)
    public void verifyPromptAlertDisplaysCorrectText() {

        String text = "Test";
        String expectedResult = "You have entered '" + text + "' !";

        // Open the prompt alert box
        javascriptAlertsPage.clickOnPromptBoxAlertButton();

        // Enter the text and get back the value
        String promptResult = javascriptAlertsPage.enterPromptTextAndReturnMessage(text);

        // Assert correct message is displayed
        Assert.assertEquals(promptResult, expectedResult);

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
