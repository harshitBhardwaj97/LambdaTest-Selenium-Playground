package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.FileDownloadPage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileDownloadTest extends Base {

    WebDriver driver;
    FileDownloadPage fileDownloadPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        fileDownloadPage = new FileDownloadPage(driver);
        homePage.navigateToFileDownloadPage();
    }

    @Test(priority = 1)
    public void verifyFileIsDownloadedAndItsContentMatches_afterClickingOnDownloadFileButton() {
        String message = "Test";

        fileDownloadPage.writeInTextBoxAndSubmit(message);
        fileDownloadPage.clickOnDownloadButton();

        String expectedFileName = "Lambdainfo.txt";
        boolean result = fileDownloadPage.verifyFileIsDownloadedAndItsContentMatches(Base.downloadsDirectory,
                expectedFileName, message);

        Assert.assertTrue(result);

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
