package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IFrameDemoPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "iFrame1")
    @CacheLookup
    private WebElement iFrameOne;

    @FindBy(xpath = "//div[@class='rsw-ce']")
    private WebElement firstIframeTextBox;

    @FindBy(id = "iFrame2")
    @CacheLookup
    private WebElement iFrameTwo;

    @FindBy(xpath = "//button[@aria-label='Search']")
    private WebElement secondIframeSearchBox;

    @FindBy(xpath = "//input[@id='docsearch-input']")
    private WebElement secondIframeSearchBoxInput;

    @FindBy(xpath = "//p[@class='DocSearch-Title']")
    private WebElement secondIframeSearchBoxResult;

    @FindBy(xpath = "//div[@class='DocSearch-NoResults']/p")
    private WebElement secondIframeNoResultFoundParagraph;

    @FindBy(xpath = "//ul[@id='docsearch-list']")
    private WebElement secondIframeSearchResult;

    @FindBy(xpath = "//ul[@id='docsearch-list']/li")
    private List<WebElement> secondIframeSearchResultList;

    public IFrameDemoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void switchToIframe(WebElement iFrameElement) {
        driver.switchTo().frame(iFrameElement);
    }

    public void enterDataInFirstIFrameTextBox(String data) {
        firstIframeTextBox.clear();
        firstIframeTextBox.sendKeys(data);
    }

    public void clickOnSecondIframeSearchBox() {
        secondIframeSearchBox.click();
    }

    public void searchSecondIframeSearchBox(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(secondIframeSearchBoxInput));

        secondIframeSearchBoxInput.clear();
        secondIframeSearchBoxInput.sendKeys(searchText);
    }

    public WebElement getFirstIFrame() {
        return iFrameOne;
    }

    public WebElement getSecondIFrame() {
        return iFrameTwo;
    }

    public boolean checkTheTextOfFirstIFrameTextBoxMatches(String text) {
        return firstIframeTextBox.getText().equals(text);
    }

    public boolean checkNoResultsParagraphIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(secondIframeNoResultFoundParagraph));
        return secondIframeNoResultFoundParagraph.isDisplayed();
    }

    public boolean checkCorrectSearchResultIsDisplayedForPresentData(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(secondIframeSearchResult));

        boolean isResultDisplayed = secondIframeSearchResult.isDisplayed();

        boolean isSearchTextPresentInResult = secondIframeSearchResultList.stream().map(item -> item.getText())
                .anyMatch(text -> text.contains(searchText));

        return isResultDisplayed && isSearchTextPresentInResult;
    }

}
