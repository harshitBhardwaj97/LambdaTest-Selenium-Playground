package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.BrokenImagePage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenImageTests extends Base {

    WebDriver driver;
    BrokenImagePage brokenImagePage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        brokenImagePage = new BrokenImagePage(driver);
        homePage.navigateToBrokenImageLinkPage();
    }

    @Test(priority = 1)
    public void verifyImagesAreBroken_byMakingUseOfNaturalWidth() {

        boolean imageOneResult = brokenImagePage.checkImageIsBrokenWithNaturalWidth(driver,
                brokenImagePage.getImageOne());

        boolean imageTwoResult = brokenImagePage.checkImageIsBrokenWithNaturalWidth(driver,
                brokenImagePage.getImageTwo());

        // Asserting First Image is broken
        Assert.assertEquals(imageOneResult, true);

        // Asserting Second Image is broken
        Assert.assertEquals(imageTwoResult, true);

    }

    @Test(priority = 2)
    public void verifyImagesAreBroken_byMakingUseOfHttpResponse() {

        boolean imageOneHttpResult = brokenImagePage.checkImageIsBrokenWithHttpResponse(brokenImagePage.getImageOne());

        boolean imageTwoHttpResult = brokenImagePage.checkImageIsBrokenWithHttpResponse(brokenImagePage.getImageOne());

        // Asserting First Image is broken
        Assert.assertEquals(imageOneHttpResult, true);

        // Asserting Second Image is broken
        Assert.assertEquals(imageTwoHttpResult, true);

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
