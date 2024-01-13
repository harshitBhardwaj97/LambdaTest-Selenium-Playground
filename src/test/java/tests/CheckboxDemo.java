package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.CheckboxDemoPage;
import pages.HomePage;

public class CheckboxDemo extends Base {

	WebDriver driver;
	CheckboxDemoPage checkboxDemoPage;
	HomePage homePage;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		checkboxDemoPage = new CheckboxDemoPage(driver);
		homePage.navigateToCheckBoxDemoPage();
	}

	@Test(priority = 1)
	public void verifyTextIsDisplayed_afterSelectingFirstCheckBox() {

		var firstCheckBox = checkboxDemoPage.getFirstCheckBox();
		boolean isTextDisplayedBefore = checkboxDemoPage.checkFirstCheckBoxTextIsDisplayed();
		assertEquals(isTextDisplayedBefore, false);

		// Originally check-box should not be selected
		assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(firstCheckBox), false);

		// Select the first check-box
		checkboxDemoPage.selectCheckBox(firstCheckBox);

		// Update the isTextDisplayed variable after selecting the check-box
		boolean isTextDisplayedAfter = checkboxDemoPage.checkFirstCheckBoxTextIsDisplayed();

		// Now it should be displayed
		assertEquals(isTextDisplayedAfter, true);

		// Now check-box should also be selected
		assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(firstCheckBox), true);

	}

	@Test(priority = 2)
	public void verifyDisabledCheckBoxesCannotBeSelected() {

		var firstDisabledCheckBox = checkboxDemoPage.getFirstDisabledCheckBox();
		var secondDisabledCheckBox = checkboxDemoPage.getSecondDisabledCheckBox();

		boolean isFirstCheckBoxEnabled = checkboxDemoPage.checkWhetherCheckBoxIsDisabled(firstDisabledCheckBox);
		boolean isSecondCheckBoxEnabled = checkboxDemoPage.checkWhetherCheckBoxIsDisabled(secondDisabledCheckBox);

		// Assert both check-boxes are disabled or not enabled
		assertEquals(isFirstCheckBoxEnabled, false);
		assertEquals(isSecondCheckBoxEnabled, false);

		/*
		 * Assert both check-boxes cannot be selected, by first clicking on them and
		 * then checking whether they are selected or not
		 */

		checkboxDemoPage.selectCheckBox(firstDisabledCheckBox);
		checkboxDemoPage.selectCheckBox(secondDisabledCheckBox);

		assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(firstDisabledCheckBox), false);
		assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(secondDisabledCheckBox), false);

	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
