package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class TableFilterPage extends Base {

	@FindBy(xpath = "//button[normalize-space()='HyperExecute']")
	private WebElement hyperExecuteFilter;

	@FindBy(xpath = "//button[normalize-space()='Selenium Testing']")
	private WebElement seleniumTestingFilter;

	@FindBy(xpath = "//button[normalize-space()='Cypress Testing']")
	private WebElement cypressTestingFilter;

	@FindBy(xpath = "//button[normalize-space()='All']")
	private WebElement allFilter;

	@FindBy(xpath = "//tbody")
	private WebElement tableElement;

	@FindBy(xpath = "//tr")
	private List<WebElement> rows;

	@FindBy(xpath = "//tr/td/div/div/h4")
	private List<WebElement> rowHeadings;

//	public String getCombinedGenderValueText() {
//		return genderText.getText();
//	}

	public void clickOnHyperExecuteFilter() {
		hyperExecuteFilter.click();
	}

	public void clickOnSeleniumTestingFilter() {
		seleniumTestingFilter.click();
	}

	public void clickOnCypressTestingFilter() {
		cypressTestingFilter.click();
	}

	public void clickOnAllFilter() {
		allFilter.click();
	}

	public int getExpectedLengthOfRowsAfterApplyingFilter(String filter) {
		switch (filter) {
		case "ALL":
			return 5;

		case "HyperExecute":
			return 2;

		case "Selenium Testing":
			return 2;

		case "Cypress Testing":
			return 1;

		default:
			return 5; // Current Total number of rows
		}

	}

	// Find the number of hidden rows
	public int getRowsHavingStyleAsDisplayNone() {
		return (int) rows.stream().map(row -> row.getAttribute("style"))
				.filter(style -> style != null && style.contains("display: none;")).count();
	}

	public List<String> getCurrentDisplayedRowsHeadings() {
		return rows.stream().filter(row -> {
			String style = row.getAttribute("style");
			return style == null || !style.contains("display: none;");
		}).map(row -> row.findElement(By.xpath(".//td/div/div/h4")).getText()).collect(Collectors.toList());
	}

	public List<WebElement> getCurrentRows() {
		return rows;
	}

	public TableFilterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
