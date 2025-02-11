package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DataListFilterPage {

    private final WebDriver driver;

    @FindBy(id = "input-search")
    @CacheLookup
    private WebElement searchInput;

    @FindBy(xpath = "//div[contains(@class,'searchable-container')]")
    @CacheLookup
    private WebElement searchResultContainer;

    @FindBy(xpath = "//div[contains(@class,'searchable-container')]/div")
    private List<WebElement> searchItems;

    public DataListFilterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getDisplayedSearchContainerItemsCount() {
        return (int) searchItems.stream().filter(item -> item.isDisplayed()).count();

    }

    public void enterTextInSearchInput(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public boolean checkDisplayedSearchContainerItemsText(String searchText) {
        return searchItems.stream().filter(item -> item.isDisplayed()).map(item -> item.getText())
                .anyMatch(text -> text.contains(searchText));

    }

}
