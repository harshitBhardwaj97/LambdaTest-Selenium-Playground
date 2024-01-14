package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.TableFilterPage;

public class TableFilter extends Base {

	WebDriver driver;
	TableFilterPage tableFilterPage;
	HomePage homePage;
	final int TOTAL_ROWS = 5;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		tableFilterPage = new TableFilterPage(driver);
		homePage.navigateToTableFilterPage();
	}

	@Test(priority = 1)
	public void verifyOriginallyFiveElementsAreDisplayed() {

		int expectedRowCount = tableFilterPage.getExpectedLengthOfRowsAfterApplyingFilter("ALL");

		System.out.println(tableFilterPage.getCurrentRows().size());

		Assert.assertEquals(tableFilterPage.getCurrentRows().size(), expectedRowCount);
	}

	@Test(priority = 2)
	public void verifyHyperExecuteFilterFunctionality() {

		final String expectedHyperExecuteRowHeading = "HyperExecute";

		/*
		 * HyperExecute Filter would be working correctly, if after clicking on it, we
		 * get 2 rows (which is the current size of this filter's data) and each row
		 * contains the same heading as filter (that's how the data has been added in
		 * table). Also getting 2 rows would mean that basically out of 5 rows only 2
		 * rows are shown, and 3 are hidden. Hence the hidden row count in this case
		 * should be 5 - 2 = 3.
		 */

		tableFilterPage.clickOnHyperExecuteFilter();

		int expectedRowCount = tableFilterPage.getExpectedLengthOfRowsAfterApplyingFilter("HyperExecute");

		Assert.assertEquals(tableFilterPage.getRowsHavingStyleAsDisplayNone(), TOTAL_ROWS - expectedRowCount);

		var currentDisplayedRowsHeadings = tableFilterPage.getCurrentDisplayedRowsHeadings();

		// Now assert heading of the displayed rows match
		for (var currentHeading : currentDisplayedRowsHeadings) {
			System.out.println(currentHeading);
			Assert.assertEquals(currentHeading, expectedHyperExecuteRowHeading);
		}

	}

	@Test(priority = 3)
	public void verifySeleniumTestingFilterFunctionality() {

		final String expectedSeleniumRowHeading = "Selenium Testing";

		/*
		 * Selenium Filter would be working correctly, if after clicking on it, we get 2
		 * rows (which is the current size of this filter's data) and each row contains
		 * the same heading as filter (that's how the data has been added in table).
		 * Also getting 2 rows would mean that basically out of 5 rows 2 are shown, and
		 * 3 are hidden. Hence the hidden row count in this case should be 5 - 2 = 3.
		 */

		tableFilterPage.clickOnSeleniumTestingFilter();

		int expectedRowCount = tableFilterPage.getExpectedLengthOfRowsAfterApplyingFilter("Selenium Testing");

		Assert.assertEquals(tableFilterPage.getRowsHavingStyleAsDisplayNone(), TOTAL_ROWS - expectedRowCount);

		var currentDisplayedRowsHeadings = tableFilterPage.getCurrentDisplayedRowsHeadings();

		// Now assert heading of the displayed rows match
		for (var currentHeading : currentDisplayedRowsHeadings) {
			System.out.println(currentHeading);
			Assert.assertEquals(currentHeading, expectedSeleniumRowHeading);
		}

	}

	@Test(priority = 4)
	public void verifyCypressTestingFilterFunctionality() {

		final String expectedCypressRowHeading = "Cypress Testing";

		/*
		 * Cypress Filter would be working correctly, if after clicking on it, we get 1
		 * row (which is the current size of this filter's data) and each row contains
		 * the same heading as filter (that's how the data has been added in table).
		 * Also getting 1 row would mean that basically out of 5 rows only 1 row is
		 * shown, and 4 are hidden. Hence the hidden row count in this case should be 5
		 * - 1 = 4.
		 */

		tableFilterPage.clickOnCypressTestingFilter();

		int expectedRowCount = tableFilterPage.getExpectedLengthOfRowsAfterApplyingFilter("Cypress Testing");

		Assert.assertEquals(tableFilterPage.getRowsHavingStyleAsDisplayNone(), TOTAL_ROWS - expectedRowCount);

		var currentDisplayedRowsHeadings = tableFilterPage.getCurrentDisplayedRowsHeadings();

		// Now assert heading of the displayed rows match
		for (var currentHeading : currentDisplayedRowsHeadings) {
			System.out.println(currentHeading);
			Assert.assertEquals(currentHeading, expectedCypressRowHeading);
		}

	}

	@Test(priority = 5)
	public void verifyAllFilterFunctionality() {

		tableFilterPage.clickOnAllFilter();

		int expectedRowCount = tableFilterPage.getExpectedLengthOfRowsAfterApplyingFilter("ALL");

		var currentDisplayedRowsHeadings = tableFilterPage.getCurrentDisplayedRowsHeadings();

		/*
		 * All 5 rows should be displayed, so size of the currentDisplayedRowsHeadings
		 * must be 5
		 */
		Assert.assertEquals(currentDisplayedRowsHeadings.size(), expectedRowCount);
	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
