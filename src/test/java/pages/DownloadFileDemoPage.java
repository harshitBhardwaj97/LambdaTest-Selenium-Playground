package pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class DownloadFileDemoPage extends Base {

	@FindBy(xpath = "//button[.='Download File']")
	private WebElement downloadFileButton;

	public void clickOnDownloadFileButton() {
		downloadFileButton.click();
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File downloadsDirectory = new File(downloadPath);

		/*
		 * Wait for a reasonable amount of time before checking for files
		 */
		long endTime = System.currentTimeMillis() + 15_000;

		while (System.currentTimeMillis() < endTime) {

			File[] dirContents = downloadsDirectory.listFiles();

			if (dirContents != null) {
				for (File file : dirContents) {
					System.out.println(file.getName());
					if (file.getName().equals(fileName)) {
						System.out.println("File has been found successfully, now deleting it.");
						// File has been found, it can now be deleted:
						file.delete();
						return true;
					}
				}
			}

			// Sleep for a short duration before checking again
			try {
				Thread.sleep(1000); // 1 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// If the loop completes without finding the file, return false
		return false;
	}

	public DownloadFileDemoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
