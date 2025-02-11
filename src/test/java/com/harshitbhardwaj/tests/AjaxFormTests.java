package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.AjaxFormPage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AjaxFormTests extends Base {

    WebDriver driver;
    AjaxFormPage ajaxFormPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        ajaxFormPage = new AjaxFormPage(driver);
        homePage.navigateToAjaxFormPage();
    }

    @Test(priority = 1)
    public void verifyRedBorderIsDisplayed_whenSubmitIsClickedWithEmptyName() {

        String expectedStyle = "border: 1px solid rgb(255, 0, 0);";
        String actualStyle = ajaxFormPage.getStyleOfEmptyNameField();

        ajaxFormPage.enterName("");
        ajaxFormPage.clickOnSubmitButton();

        // Red border is added
        Assert.assertEquals(actualStyle, expectedStyle);

    }

    @Test(priority = 2)
    public void verifyAjaxProcessingMessageIsDisplayed_whenSubmitIsClickedWithData() {

        ajaxFormPage.enterName("test");
        ajaxFormPage.enterMessage("test message");
        ajaxFormPage.clickOnSubmitButton();

        // Processing Message is Displayed
        Assert.assertTrue(ajaxFormPage.checkSubmitControlDivContent());

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
