package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.BootstrapListBoxPage;
import pages.HomePage;

public class BootstrapListBox extends Base {

	WebDriver driver;
	BootstrapListBoxPage bootstrapListBoxPage;
	HomePage homePage;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		bootstrapListBoxPage = new BootstrapListBoxPage(driver);
		homePage.navigateToBootstrapListBoxPage();
	}

	@Test(priority = 1)
	public void verifyLeftToRightTransferOfItems_andSearchBoxFunctionality() {

		final String ITEM_ONE_TO_TRANSFER = "Kedungjenar";
		final String ITEM_TWO_TO_TRANSFER = "Danville";

		int leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		int rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		var leftSearchBox = bootstrapListBoxPage.getLeftSearchInput();
		var transferToRightBoxButton = bootstrapListBoxPage.getRightTransferButton();

		// Originally three items should be displayed in both boxes
		Assert.assertEquals(leftBoxItemsLength, 3);
		Assert.assertEquals(rightBoxItemsLength, 3);

		/*
		 * Now transfer some items to right box, and verify those items are present
		 * there, and size of left box has decreased by the number of items transferred.
		 */
		bootstrapListBoxPage.clickOnLeftBoxItemForTransfer(ITEM_ONE_TO_TRANSFER);
		bootstrapListBoxPage.clickOnTransferButton(transferToRightBoxButton);
		leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		Assert.assertEquals(leftBoxItemsLength, 2); // Is decreased by one
		Assert.assertEquals(rightBoxItemsLength, 4); // Is increased by one

		bootstrapListBoxPage.clickOnLeftBoxItemForTransfer(ITEM_TWO_TO_TRANSFER);
		bootstrapListBoxPage.clickOnTransferButton(transferToRightBoxButton);
		leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		Assert.assertEquals(leftBoxItemsLength, 1); // Is decreased by one
		Assert.assertEquals(rightBoxItemsLength, 5); // Is increased by one

		/*
		 * Verify correct strings are displayed, when searched in both the lists
		 */
		bootstrapListBoxPage.enterTextInSearchBox(ITEM_ONE_TO_TRANSFER, leftSearchBox);
		leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		// Zero because it has been transferred to right box
		Assert.assertEquals(leftBoxItemsLength, 0);
		// Item text is not found in left list
		Assert.assertFalse(bootstrapListBoxPage.checkTheItemIsPresentInLeftList(ITEM_ONE_TO_TRANSFER));

		bootstrapListBoxPage.enterTextInSearchBox(ITEM_TWO_TO_TRANSFER, leftSearchBox);
		leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		Assert.assertEquals(leftBoxItemsLength, 0); // Zero because it has been transferred to right box
		Assert.assertFalse(bootstrapListBoxPage.checkTheItemIsPresentInLeftList(ITEM_TWO_TO_TRANSFER));

		/*
		 * Verify nothing is displayed when a string is searched, which is not present
		 * in list (Hence the size of displayed items in left box should be zero)
		 */
		bootstrapListBoxPage.enterTextInSearchBox("Random Text", leftSearchBox);
		leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		Assert.assertEquals(leftBoxItemsLength, 0);

	}

	@Test(priority = 2)
	public void verifyRightToLeftTransferOfItems_andSearchBoxFunctionality() {

		final String ITEM_ONE_TO_TRANSFER = "Ngodo";
		final String ITEM_TWO_TO_TRANSFER = "Grange";

		int leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		int rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		var rightSearchBox = bootstrapListBoxPage.getRightSearchInput();
		var transferToLeftBoxButton = bootstrapListBoxPage.getLeftTransferButton();

		// Originally three items should be displayed in both boxes
		Assert.assertEquals(leftBoxItemsLength, 3);
		Assert.assertEquals(rightBoxItemsLength, 3);

		/*
		 * Now transfer some items to right box, and verify those items are present
		 * there, and size of left box has decreased by the number of items transferred.
		 */
		bootstrapListBoxPage.clickOnRightBoxItemForTransfer(ITEM_ONE_TO_TRANSFER);
		bootstrapListBoxPage.clickOnTransferButton(transferToLeftBoxButton);
		leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		Assert.assertEquals(leftBoxItemsLength, 4); // Is increased by one
		Assert.assertEquals(rightBoxItemsLength, 2); // Is decreased by one

		bootstrapListBoxPage.clickOnRightBoxItemForTransfer(ITEM_TWO_TO_TRANSFER);
		bootstrapListBoxPage.clickOnTransferButton(transferToLeftBoxButton);
		leftBoxItemsLength = bootstrapListBoxPage.getLengthOfLeftListDisplayedItems();
		rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		Assert.assertEquals(leftBoxItemsLength, 5); // Is increased by one
		Assert.assertEquals(rightBoxItemsLength, 1); // Is decreased by one

		/*
		 * Verify correct strings are displayed, when searched in both the lists
		 */
		bootstrapListBoxPage.enterTextInSearchBox(ITEM_ONE_TO_TRANSFER, rightSearchBox);
		rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		// Zero because it has been transferred to left box
		Assert.assertEquals(rightBoxItemsLength, 0);
		// Item text is not found in right list
		Assert.assertFalse(bootstrapListBoxPage.checkTheItemIsPresentInRightList(ITEM_ONE_TO_TRANSFER));

		bootstrapListBoxPage.enterTextInSearchBox(ITEM_TWO_TO_TRANSFER, rightSearchBox);
		rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		// Zero because it has been transferred to right box
		Assert.assertEquals(rightBoxItemsLength, 0);
		// Item text is not found in right list
		Assert.assertFalse(bootstrapListBoxPage.checkTheItemIsPresentInRightList(ITEM_TWO_TO_TRANSFER));

		/*
		 * Verify nothing is displayed when a string is searched, which is not present
		 * in list (Hence the size of displayed items in left box should be zero)
		 */
		bootstrapListBoxPage.enterTextInSearchBox("Random Text", rightSearchBox);
		rightBoxItemsLength = bootstrapListBoxPage.getLengthOfRightListDisplayedItems();
		Assert.assertEquals(rightBoxItemsLength, 0);

	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
