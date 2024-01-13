package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.FileDownloadPage;
import pages.HomePage;

public class FileDownload extends Base {

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
	public void verifyfileIsDownloadedAndItsContentMatches_afterClickingOnDownloadFileButton() {
		String message = "Test";

		fileDownloadPage.writeInTextBoxAndSubmit(message);
		fileDownloadPage.clickOnDownloadButton();

		String expectedFileName = "Lambdainfo.txt";
		boolean result = fileDownloadPage.verifyFileIsDownloadedAndItsContentMatches(Base.downloadsDirectory,
				expectedFileName, message);

		Assert.assertEquals(result, true);

	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
