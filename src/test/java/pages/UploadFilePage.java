package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class UploadFilePage extends Base {

	@FindBy(id = "file")
	private WebElement chooseFileButton;

	@FindBy(id = "error")
	private WebElement uploadMessage;

	public boolean checkUploadMessageIsDisplayed() {
		return uploadMessage.isDisplayed();
	}

	public String getUploadedMessage() {
		return uploadMessage.getText();
	}

	public String getUploadedMessageStyle() {
		return uploadMessage.getAttribute("style");
	}

	public void uploadFile(String filePath) {
		System.out.println(filePath);
		chooseFileButton.sendKeys(filePath);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public UploadFilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
