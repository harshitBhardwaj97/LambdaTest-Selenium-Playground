package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AutoHealingPage;
import pages.HomePage;

public class AutoHealing extends Base {

	WebDriver driver;
	AutoHealingPage autoHealingPage;
	HomePage homePage;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		autoHealingPage = new AutoHealingPage(driver);
		homePage.navigateToAutoHealingPage();
	}

	@Test(priority = 1)
	public void verifyLoginSuccessfulIsDisplayed_whenSubmitIsClickedWithData() {

		autoHealingPage.enterUserName("User");
		autoHealingPage.enterPassword("Password");
		autoHealingPage.clickOnSubmitButton();

		// Login successful message is displayed
		Assert.assertEquals(autoHealingPage.checkLoginSuccessfulMessageIsDisplayed(), true);

	}

	@Test(priority = 2)
	public void verifyDOMIdIsChanged_whenChangeDOMIsClicked() {

		autoHealingPage.enterUserName("User");
		autoHealingPage.enterPassword("Password");

		String initialDOMId = autoHealingPage.checkUserNameDomId();
		System.out.println(initialDOMId);

		// Check initalDOMId
		Assert.assertEquals(initialDOMId, "username");

		autoHealingPage.clickOnChangeDOMIDButton();

		String finalDOMId = autoHealingPage.checkUserNameDomId();
		System.out.println(finalDOMId);

		// Check finalDOMId
		Assert.assertEquals(initialDOMId, "username");

	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
