package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class HomePage extends Base {

	WebDriver driver;

	@FindBy(linkText = "Ajax Form Submit")
	@CacheLookup
	private WebElement ajaxFormLink;

	@FindBy(linkText = "Auto Healing")
	@CacheLookup
	private WebElement autoHealingLink;

	@FindBy(linkText = "Broken Image")
	@CacheLookup
	private WebElement brokenImageLink;

	@FindBy(linkText = "Checkbox Demo")
	@CacheLookup
	private WebElement checkBoxDemoLink;

	@FindBy(linkText = "Download File Demo")
	@CacheLookup
	private WebElement downloadFileDemoLink;

	@FindBy(linkText = "File Download")
	@CacheLookup
	private WebElement fileDownloadLink;

	@FindBy(linkText = "Upload File Demo")
	@CacheLookup
	private WebElement uploadFileLink;

	@FindBy(linkText = "Simple Form Demo")
	@CacheLookup
	private WebElement simpleFormDemoLink;

	@FindBy(linkText = "Redirection")
	@CacheLookup
	private WebElement redirectionLink;

	@FindBy(linkText = "Javascript Alerts")
	@CacheLookup
	private WebElement javascriptAlertsLink;

	@FindBy(linkText = "Key Press")
	@CacheLookup
	private WebElement keyPressLink;

	@FindBy(linkText = "Radio Buttons Demo")
	@CacheLookup
	private WebElement radioButtonsDemoLink;

	@FindBy(linkText = "Table Filter")
	@CacheLookup
	private WebElement tableFilterLink;

	@FindBy(linkText = "Input Form Submit")
	@CacheLookup
	private WebElement inputFormSubmitLink;

	@FindBy(linkText = "Select Dropdown List")
	@CacheLookup
	private WebElement selectDropdownLink;

	@FindBy(linkText = "Context Menu")
	@CacheLookup
	private WebElement contextMenuLink;

	public void navigateToAjaxFormPage() {
		ajaxFormLink.click();
	}

	public void navigateToAutoHealingPage() {
		autoHealingLink.click();
	}

	public void navigateToBrokenImageLinkPage() {
		brokenImageLink.click();
	}

	public void navigateToCheckBoxDemoPage() {
		checkBoxDemoLink.click();
	}

	public void navigateToDownloadFileDemoPage() {
		downloadFileDemoLink.click();
	}

	public void navigateToFileDownloadPage() {
		fileDownloadLink.click();
	}

	public void navigateToUploadFilePage() {
		uploadFileLink.click();
	}

	public void navigateToSimpleFormDemoPage() {
		simpleFormDemoLink.click();
	}

	public void navigateToRedirectionPage() {
		redirectionLink.click();
	}

	public void navigateToJavscriptAlertsPage() {
		javascriptAlertsLink.click();
	}

	public void navigateToKeyPressPage() {
		keyPressLink.click();
	}

	public void navigateToRadioButtonsDemoPage() {
		radioButtonsDemoLink.click();
	}

	public void navigateToTableFilterPage() {
		tableFilterLink.click();
	}

	public void navigateToInputFormSubmitPage() {
		inputFormSubmitLink.click();
	}

	public void navigateToSelectDropdownDemoPage() {
		selectDropdownLink.click();
	}

	public void navigateToContextMenuPage() {
		contextMenuLink.click();
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
