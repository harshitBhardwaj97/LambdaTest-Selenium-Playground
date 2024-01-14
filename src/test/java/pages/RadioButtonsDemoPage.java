package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class RadioButtonsDemoPage extends Base {

	@FindBy(xpath = "//input[@value='Male' and @name='optradio']")
	private WebElement firstMaleRadioButton;

	@FindBy(xpath = "//input[@value='Female' and @name='optradio']")
	private WebElement firstFemaleRadioButton;

	@FindBy(xpath = "// input[@value='RadioButton3']")
	private WebElement disabledRadioButton;

	@FindBy(id = "buttoncheck")
	private WebElement getValueButton;

	@FindBy(xpath = "//button[@id='buttoncheck']//following-sibling::p")
	private WebElement firstGetValueMessage;

	@FindBy(xpath = "//h4[text()='Gender : ']/following-sibling::label[text()='Male']")
	private WebElement secondMaleGenderRadioButton;

	@FindBy(xpath = "//h4[text()='Gender : ']/following-sibling::label[text()='Female']")
	private WebElement secondFemaleGenderRadioButton;

	@FindBy(xpath = "//h4[text()='Gender : ']/following-sibling::label[text()='Other']")
	private WebElement otherGenderRadioButton;

	@FindBy(xpath = "//h4[text()='Age : ']/following-sibling::label[text()='0 to 5']")
	private WebElement ageZeroToFiveRadioButton;

	@FindBy(xpath = "//h4[text()='Age : ']/following-sibling::label[text()='5 to 15']")
	private WebElement ageFiveToFifteenRadioButton;

	@FindBy(xpath = "//h4[text()='Age : ']/following-sibling::label[text()='15 to 50']")
	private WebElement ageFifteenToFiftyRadioButton;

	@FindBy(xpath = "//button[.='Get values']")
	private WebElement getValuesButton;

	@FindBy(className = "genderbutton")
	private WebElement genderText;

	@FindBy(className = "groupradiobutton")
	private WebElement ageText;

	public WebElement getFirstMaleRadioButton() {
		return firstMaleRadioButton;
	}

	public WebElement getFirstFemaleRadioButton() {
		return firstFemaleRadioButton;
	}

	public WebElement getDisabledRadioButton() {
		return disabledRadioButton;
	}

	public void selectThePassedRadioButton(WebElement radioButton) {
		radioButton.click();
	}

	public void clickOnFirstGetValueButton() {
		getValueButton.click();
	}

	public void clickOnGetValuesButton() {
		getValuesButton.click();
	}

	public String getFirstGenderValueAfterSelection() {
		return firstGetValueMessage.getText();
	}

	public boolean checkPassedRadioButtonIsSelected(WebElement radioButton) {
		return radioButton.isSelected();
	}

	public List<WebElement> getAllPossibleGenderButtons() {
		return new ArrayList<WebElement>(
				Arrays.asList(secondMaleGenderRadioButton, secondFemaleGenderRadioButton, otherGenderRadioButton));
	}

	public List<WebElement> getAllPossibleAgeGroupButtons() {
		return new ArrayList<WebElement>(
				Arrays.asList(ageZeroToFiveRadioButton, ageFiveToFifteenRadioButton, ageFifteenToFiftyRadioButton));
	}

	public String getCombinedGenderValueText() {
		return genderText.getText();
	}

	public String getCombinedAgeValueText() {
		return ageText.getText();
	}

	public RadioButtonsDemoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
