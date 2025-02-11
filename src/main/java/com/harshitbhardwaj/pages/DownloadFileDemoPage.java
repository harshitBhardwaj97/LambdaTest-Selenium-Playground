package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class DownloadFileDemoPage {

    private final WebDriver driver;

    @FindBy(xpath = "//button[.='Download File']")
    private WebElement downloadFileButton;

    public DownloadFileDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnDownloadFileButton() {
        downloadFileButton.click();
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
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

}
