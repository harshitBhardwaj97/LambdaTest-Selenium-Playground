package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.pages.RedirectionPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RedirectionTest extends Base {

    WebDriver driver;
    RedirectionPage redirectionPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        redirectionPage = new RedirectionPage(driver);
        homePage.navigateToRedirectionPage();
    }

    @Test(priority = 1)
    public void verifyClickingOnRedirectLink_redirectsToCorrectPage() {

        String redirectionPageUrl = "https://www.lambdatest.com/selenium-playground/redirection";
        String homepageUrl = "https://www.lambdatest.com/selenium-playground/";

        /*
         * First of all verify the current URL, which should be the URL of redirection
         * page itself
         */

        Assert.assertEquals(redirectionPage.getCurrentUrlOfPage(), redirectionPageUrl);

        /*
         * Now click on redirection link and then verify the current URL matches the
         * home-page URL
         */

        redirectionPage.clickOnRedirectionLink();
        redirectionPage.waitForPageUrl(homepageUrl);

        Assert.assertEquals(redirectionPage.getCurrentUrlOfPage(), homepageUrl);

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
