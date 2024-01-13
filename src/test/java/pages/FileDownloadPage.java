package pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class FileDownloadPage extends Base {

	@FindBy(id = "textbox")
	private WebElement dataTextBox;

	@FindBy(id = "create")
	private WebElement generateButton;

	@FindBy(id = "link-to-download")
	private WebElement downloadLink;

	public void writeInTextBoxAndSubmit(String text) {
		dataTextBox.sendKeys(text);
		generateButton.click();
	}

	public void clickOnDownloadButton() {
		downloadLink.click();
	}

	public boolean checkDownloadLinkIsDisplayed() {
		return downloadLink.isDisplayed();
	}

//	public boolean isFileDownloadedAndHasCorrectContent(String downloadPath, String fileName, String fileContent) {
//		File downloadsDirectory = new File(downloadPath);
//
//		/*
//		 * Wait for a reasonable amount of time before checking for files
//		 */
//		long endTime = System.currentTimeMillis() + 15_000;
//
//		while (System.currentTimeMillis() < endTime) {
//
//			File[] dirContents = downloadsDirectory.listFiles();
//
//			if (dirContents != null) {
//				for (File file : dirContents) {
//					System.out.println(file.getName());
//					if (file.getName().equals(fileName)) {
//
//						// Before deleting the file, check for the file contents
//						if (matchFileContent(fileContent, file)) {
//							System.out.println(
//									"File has been found successfully, and content also matches now deleting it.");
//							// File has been found, and content also matches it can now be deleted:
//							file.delete();
//							return true;
//						}
//
//					}
//					file.delete(); // Delete the file whether the message matches or not
//				}
//
//			}
//
//			// Sleep for a short duration before checking again
//			try {
//				Thread.sleep(1000); // 1 second
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// If the loop completes without finding the file, return false
//		return false;
//	}
//
//	public boolean matchFileContent(String fileContent, File fileToRead) {
//
//		List<String> lines;
//		try {
//			lines = FileUtils.readLines(fileToRead);
//			String[] lineArray = lines.stream().toArray(String[]::new);
//
//			for (String s : lineArray) {
//				System.out.println(s);
//				if (s.equals(fileContent))
//					return true;
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return false;
//	}
	public boolean verifyFileIsDownloadedAndItsContentMatches(String downloadPath, String fileName,
			String expectedContent) {
		File downloadsDirectory = new File(downloadPath);

		cleanDownloadDirectory(downloadPath);

		/*
		 * Wait for a reasonable amount of time before checking for files
		 */
		long endTime = System.currentTimeMillis() + 15_000;

		while (System.currentTimeMillis() < endTime) {

			File[] dirContents = downloadsDirectory.listFiles();

			if (dirContents != null) {
				for (File file : dirContents) {
					if (file.getName().equals(fileName)) {
						// File has been found, now read its content
						System.out.println("File -> " + fileName);
						String actualContent = readContentFromFile(file);

						if (actualContent.equals(expectedContent)) {
							// Content matches, delete the file and return true
							System.out.println("File downloaded and content also matched.");
							file.delete();
							return true;
						} else {
							// Content doesn't match, return false without deleting the file
							System.out.println("File downloaded but content didn't match.");
							return false;
						}
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

		/*
		 * If the loop completes without finding the file, or content doesn't match,
		 * return false
		 */
		return false;
	}

	private String readContentFromFile(File file) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line);
				System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	// Delete all files in beginning
	private void cleanDownloadDirectory(String downloadPath) {
		File downloadsDirectory = new File(downloadPath);

		if (downloadsDirectory.exists() && downloadsDirectory.isDirectory()) {
			File[] files = downloadsDirectory.listFiles();

			if (files != null) {
				for (File file : files) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
		}
	}

	public FileDownloadPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
