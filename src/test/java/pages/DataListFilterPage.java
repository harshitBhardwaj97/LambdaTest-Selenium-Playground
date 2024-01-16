package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class DataListFilterPage extends Base {

	@FindBy(id = "input-search")
	@CacheLookup
	private WebElement searchInput;

	@FindBy(xpath = "//div[contains(@class,'searchable-container')]")
	@CacheLookup
	private WebElement searchResultContainer;

	@FindBy(xpath = "//div[contains(@class,'searchable-container')]/div")
	private List<WebElement> searchItems;

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

	public DataListFilterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
