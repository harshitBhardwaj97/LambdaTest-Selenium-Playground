package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.DownloadFileDemoPage;
import pages.HomePage;

public class DownloadFileDemo extends Base {

	WebDriver driver;
	DownloadFileDemoPage downloadFileDemoPage;
	HomePage homePage;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		downloadFileDemoPage = new DownloadFileDemoPage(driver);
		homePage.navigateToDownloadFileDemoPage();
	}

	@Test(priority = 1)
	public void verifyFileIsDownloaded_afterClickingOnDownloadFileButton() {

		String expectedFileName = "LambdaTest.pdf";
		downloadFileDemoPage.clickOnDownloadFileButton();

		boolean result = downloadFileDemoPage.isFileDownloaded(Base.downloadsDirectory, expectedFileName);

		Assert.assertEquals(result, true);

	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
