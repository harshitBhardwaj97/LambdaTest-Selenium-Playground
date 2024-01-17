package tests;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.TableDataDownloadPage;

public class TableDataDownload extends Base {

	WebDriver driver;
	TableDataDownloadPage tableDataDownloadPage;
	HomePage homePage;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		tableDataDownloadPage = new TableDataDownloadPage(driver);
		homePage.navigateToTableDataDownloadPage();
		tableDataDownloadPage.cleanDownloadDirectory(Base.downloadsDirectory);
	}

	@Test(priority = 1)
	public void verifyCopyButtonFunctionality() {

		/*
		 * This method attempts to copy some data, and then verify it with already
		 * present data, which should be actually copied on the clip-board after
		 * pressing on the copy button.
		 */

		var copyButton = tableDataDownloadPage.getCopyButton();

		tableDataDownloadPage.searchForText("Abraham");

		tableDataDownloadPage.clickButton(copyButton);

		String clipboardContent = getClipboardContent(); // This contains the actual copied content

		Assert.assertTrue(tableDataDownloadPage.checkCopyContentFunctionality(clipboardContent));

	}

	@Test(priority = 2)
	public void verifyCSVDownloadFunctionality() {

		/*
		 * This method attempts to download the CSV File, and then compare its data with
		 * the present data, which is already defined according to the expected CSV
		 * file, that is located in the resources directory.
		 */

		var csvDownloadButton = tableDataDownloadPage.getCSVDownloadButton();

		tableDataDownloadPage.searchForText("Abraham");

		tableDataDownloadPage.clickButton(csvDownloadButton);

		Assert.assertTrue(tableDataDownloadPage.checkCSVDownloadFunctionality());

	}

	@Test(priority = 3)
	public void verifyXLSXDownloadFunctionality() {

		/*
		 * This method attempts to download the XLSX File, and then compare its data
		 * with the present data, which is already defined according to the expected
		 * XLSX file, that is located in the resources directory.
		 */

		var excelDownloadButton = tableDataDownloadPage.getExcelDownloadButton();

		tableDataDownloadPage.searchForText("Abraham");

		tableDataDownloadPage.clickButton(excelDownloadButton);

		Assert.assertTrue(tableDataDownloadPage.checkXLSXDownloadFunctionality());

	}

	// Function to get content from the clip-board
	private String getClipboardContent() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);

		try {
			if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				return (String) transferable.getTransferData(DataFlavor.stringFlavor);
			}
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
