package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;

    @FindBy(linkText = "Ajax Form Submit")
    @CacheLookup
    private WebElement ajaxFormLink;

    @FindBy(linkText = "Auto Healing")
    @CacheLookup
    private WebElement autoHealingLink;

    @FindBy(linkText = "Bootstrap List Box")
    @CacheLookup
    private WebElement bootstrapListBoxLink;

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

    @FindBy(linkText = "Drag and Drop")
    @CacheLookup
    private WebElement dragAndDropLink;

    @FindBy(linkText = "Drag & Drop Sliders")
    @CacheLookup
    private WebElement dragAndDropSlidersLink;

    @FindBy(linkText = "Data List Filter")
    @CacheLookup
    private WebElement dataListFilterLink;

    @FindBy(linkText = "iFrame Demo")
    @CacheLookup
    private WebElement iFrameLink;

    @FindBy(linkText = "Table Data Download")
    @CacheLookup
    private WebElement tableDataDownloadLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToAjaxFormPage() {
        ajaxFormLink.click();
    }

    public void navigateToAutoHealingPage() {
        autoHealingLink.click();
    }

    public void navigateToBootstrapListBoxPage() {
        bootstrapListBoxLink.click();
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

    public void navigateToDragAndDropDemoPage() {
        dragAndDropLink.click();
    }

    public void navigateToDragAndDropSlidersPage() {
        dragAndDropSlidersLink.click();
    }

    public void navigateToDataListFilterPage() {
        dataListFilterLink.click();
    }

    public void navigateToiFrameDemoPage() {
        iFrameLink.click();
    }

    public void navigateToTableDataDownloadPage() {
        tableDataDownloadLink.click();
    }

}
