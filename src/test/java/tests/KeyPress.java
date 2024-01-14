package tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.KeyPressPage;

public class KeyPress extends Base {

	WebDriver driver;
	KeyPressPage keyPressPage;
	HomePage homePage;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		keyPressPage = new KeyPressPage(driver);
		homePage.navigateToKeyPressPage();
	}

	@Test(priority = 1)
	public void verifyMessageForAlphaNumericKeys() {

		var alphanumericKeysList = new ArrayList<String>();

		// Initialize the list first with alphanumeric keys
		for (char c = 'A'; c <= 'Z'; c++) {
			alphanumericKeysList.add(String.valueOf(c));
		}

		for (int i = 0; i <= 9; i++) {
			alphanumericKeysList.add(String.valueOf(i));
		}

		// Now check for all the alphanumeric keys
		for (String key : alphanumericKeysList) {
			Assert.assertTrue(keyPressPage.enterAlphaNumKeyInInputFieldAndVerifyMessage(key));
		}

	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
