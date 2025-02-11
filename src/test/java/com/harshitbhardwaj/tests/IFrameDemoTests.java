package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.pages.IFrameDemoPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFrameDemoTests extends Base {

    WebDriver driver;
    IFrameDemoPage iFrameDemoPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        iFrameDemoPage = new IFrameDemoPage(driver);
        homePage.navigateToiFrameDemoPage();
    }

    @Test(priority = 1)
    public void verifyFirstiFrameFunctionality() {

        var iFrameOne = iFrameDemoPage.getFirstIFrame();

        /*
         * Switch to first iFrame, enter the text, and then confirm that entered text
         * matches with the text present in that text box. Also before that check that
         * "Your content." is already present in that text box.
         */

        iFrameDemoPage.switchToIframe(iFrameOne);

        Assert.assertTrue(iFrameDemoPage.checkTheTextOfFirstIFrameTextBoxMatches("Your content."));

        final String ENTERED_TEXT = "The quick brown fox jumps over the lazy dog.";

        iFrameDemoPage.enterDataInFirstIFrameTextBox(ENTERED_TEXT);

        Assert.assertTrue(iFrameDemoPage.checkTheTextOfFirstIFrameTextBoxMatches(ENTERED_TEXT));

    }

    @Test(priority = 2)
    public void verifySecondiFrameSearchFunctionality_withIncorrectData() {

        var iFrameTwo = iFrameDemoPage.getSecondIFrame();

        /*
         * Switch to second iFrame, search for non-existent data and verify no results
         * found is displayed.
         */

        iFrameDemoPage.switchToIframe(iFrameTwo);

        iFrameDemoPage.clickOnSecondIframeSearchBox();

        iFrameDemoPage.searchSecondIframeSearchBox("asdf");

        Assert.assertTrue(iFrameDemoPage.checkNoResultsParagraphIsDisplayed());

    }

    @Test(priority = 3)
    public void verifySecondiFrameSearchFunctionality_withCorrectData() {

        var iFrameTwo = iFrameDemoPage.getSecondIFrame();

        /*
         * Switch to second iFrame, search for existent data and verify result is
         * displayed, and also that result contains the searched item.
         */

        iFrameDemoPage.switchToIframe(iFrameTwo);

        iFrameDemoPage.clickOnSecondIframeSearchBox();

        iFrameDemoPage.searchSecondIframeSearchBox("Selenium");

        Assert.assertTrue(iFrameDemoPage.checkCorrectSearchResultIsDisplayedForPresentData("Selenium"));

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
