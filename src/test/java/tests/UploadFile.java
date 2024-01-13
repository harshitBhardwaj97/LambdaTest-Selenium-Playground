package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.UploadFilePage;

public class UploadFile extends Base {

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
		String jpgFilePath = Base.uploadDirectory + "\\lamdatest-logo.jpg";

		// As of now no message should be displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), false);

		// Now try uploading the file
		uploadFilePage.uploadFile(jpgFilePath);

		// Assert upload status is displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), true);

		System.out.println(uploadFilePage.getUploadedMessageStyle());

		// Assert correct style is applied to message
		Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

		// Assert correct success message is displayed
		Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

	}

	@Test(priority = 2)
	public void verifyJPEGFileIsUploadedSuccessfully() {
		String jpegFilePath = Base.uploadDirectory + "\\lamdatest-logo.jpeg";

		// As of now no message should be displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), false);

		// Now try uploading the file
		uploadFilePage.uploadFile(jpegFilePath);

		// Assert upload status is displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), true);

		System.out.println(uploadFilePage.getUploadedMessageStyle());

		// Assert correct style is applied to message
		Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

		// Assert correct success message is displayed
		Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

	}

	@Test(priority = 3)
	public void verifyPNGFileIsUploadedSuccessfully() {
		String pngFilePath = Base.uploadDirectory + "\\lamdatest-logo.png";

		// As of now no message should be displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), false);

		// Now try uploading the file
		uploadFilePage.uploadFile(pngFilePath);

		// Assert upload status is displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), true);

		System.out.println(uploadFilePage.getUploadedMessageStyle());

		// Assert correct style is applied to message
		Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

		// Assert correct success message is displayed
		Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

	}

	@Test(priority = 4)
	public void verifyPDFFileIsUploadedSuccessfully() {
		String pdfFilePath = Base.uploadDirectory + "\\LambdaTest.pdf";

		// As of now no message should be displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), false);

		// Now try uploading the file
		uploadFilePage.uploadFile(pdfFilePath);

		// Assert upload status is displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), true);

		System.out.println(uploadFilePage.getUploadedMessageStyle());

		// Assert correct style is applied to message
		Assert.assertTrue(uploadFilePage.getUploadedMessageStyle().contains("color: green;"));

		// Assert correct success message is displayed
		Assert.assertEquals(uploadFilePage.getUploadedMessage(), expectedSuccessMessage);

	}

	@Test(priority = 5)
	public void verifyTXTFileIsNotUploaded() {
		String txtFilePath = Base.uploadDirectory + "\\Lambdainfo.txt";

		// As of now no message should be displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), false);

		// Now try uploading the file
		uploadFilePage.uploadFile(txtFilePath);

		// Assert upload status is displayed
		Assert.assertEquals(uploadFilePage.checkUploadMessageIsDisplayed(), true);

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
