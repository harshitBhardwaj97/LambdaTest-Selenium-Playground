package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.pages.UploadFilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class UploadFileTests extends Base {

    WebDriver driver;
    UploadFilePage uploadFilePage;
    HomePage homePage;
    String expectedSuccessMessage = "File Successfully Uploaded";
    String expectedFailureMessage = "File type should be pdf, png, jpeg or jpg";

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        uploadFilePage = new UploadFilePage(driver);
        homePage.navigateToUploadFilePage();
    }

    @Test(priority = 1)
    public void verifyJPGFileIsUploadedSuccessfully() {
        String jpgFilePath = Base.uploadDirectory + File.separator + "lamdatest-logo.jpg";

        // As of now no message should be displayed
        Assert.assertFalse(uploadFilePage.checkUploadMessageIsDisplayed());

        // Now try uploading the file
        uploadFilePage.uploadFile(jpgFilePath);

        // Assert upload status is displayed
        Assert.assertTrue(uploadFilePage.checkUploadMessageIsDisplayed());

        System.out.println(uploadFilePage.getUploadedMessageStyle());

        // Assert correct style is applied to message
        Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

        // Assert correct success message is displayed
        Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

    }

    @Test(priority = 2)
    public void verifyJPEGFileIsUploadedSuccessfully() {
        String jpegFilePath = Base.uploadDirectory + File.separator + "lamdatest-logo.jpeg";

        // As of now no message should be displayed
        Assert.assertFalse(uploadFilePage.checkUploadMessageIsDisplayed());

        // Now try uploading the file
        uploadFilePage.uploadFile(jpegFilePath);

        // Assert upload status is displayed
        Assert.assertTrue(uploadFilePage.checkUploadMessageIsDisplayed());

        System.out.println(uploadFilePage.getUploadedMessageStyle());

        // Assert correct style is applied to message
        Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

        // Assert correct success message is displayed
        Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

    }

    @Test(priority = 3)
    public void verifyPNGFileIsUploadedSuccessfully() {
        String pngFilePath = Base.uploadDirectory + File.separator + "lamdatest-logo.png";

        // As of now no message should be displayed
        Assert.assertFalse(uploadFilePage.checkUploadMessageIsDisplayed());

        // Now try uploading the file
        uploadFilePage.uploadFile(pngFilePath);

        // Assert upload status is displayed
        Assert.assertTrue(uploadFilePage.checkUploadMessageIsDisplayed());

        System.out.println(uploadFilePage.getUploadedMessageStyle());

        // Assert correct style is applied to message
        Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

        // Assert correct success message is displayed
        Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

    }

    @Test(priority = 4)
    public void verifyPDFFileIsUploadedSuccessfully() {
        String pdfFilePath = Base.uploadDirectory + File.separator + "LambdaTest.pdf";

        // As of now no message should be displayed
        Assert.assertFalse(uploadFilePage.checkUploadMessageIsDisplayed());

        // Now try uploading the file
        uploadFilePage.uploadFile(pdfFilePath);

        // Assert upload status is displayed
        Assert.assertTrue(uploadFilePage.checkUploadMessageIsDisplayed());

        System.out.println(uploadFilePage.getUploadedMessageStyle());

        // Assert correct style is applied to message
        Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

        // Assert correct success message is displayed
        Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

    }

    @Test(priority = 5)
    public void verifyTXTFileIsNotUploaded() {
        String txtFilePath = Base.uploadDirectory + File.separator + "Lambdainfo.txt";

        // As of now no message should be displayed
        Assert.assertFalse(uploadFilePage.checkUploadMessageIsDisplayed());

        // Now try uploading the file
        uploadFilePage.uploadFile(txtFilePath);

        // Assert upload status is displayed
        Assert.assertTrue(uploadFilePage.checkUploadMessageIsDisplayed());

        System.out.println(uploadFilePage.getUploadedMessageStyle());

        // Assert correct style is applied to message
        Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: red;"));

        // Assert correct success message is displayed
        Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedFailureMessage);

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
