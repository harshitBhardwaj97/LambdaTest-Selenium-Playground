package tests;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.SimpleFormDemoPage;

public class SimpleFormDemo extends Base {

	WebDriver driver;
	SimpleFormDemoPage simpleFormDemoPage;
	HomePage homePage;
	String expectedFailureMessage = "Entered value is not a number";

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		simpleFormDemoPage = new SimpleFormDemoPage(driver);
		homePage.navigateToSimpleFormDemoPage();
	}

	@Test(priority = 1)
	public void verifySingleInputFieldShowsCorrectMessage() {

		/*
		 * As of now since we haven't entered message, hence the messageValue of the
		 * input field should be empty string
		 */

		Assert.assertEquals(simpleFormDemoPage.getMessageValue(), "");

		// Now enter the some text and then check the value
		String messageOne = "Test Message 1";
		String messageTwo = "Test Message 2";
		simpleFormDemoPage.enterMessage(messageOne);
		simpleFormDemoPage.clickOnGetCheckedValueButton();

		Assert.assertEquals(simpleFormDemoPage.getMessageValue(), messageOne);

		// Repeat the same test now with different value
		simpleFormDemoPage.clearSingleInputField();
		simpleFormDemoPage.enterMessage(messageTwo);
		simpleFormDemoPage.clickOnGetCheckedValueButton();

		Assert.assertEquals(simpleFormDemoPage.getMessageValue(), messageTwo);

	}

	@Test(priority = 2)
	public void verifySumInputFieldsFeatureWithNumericValues() {

		var sumInputFieldList = new ArrayList<WebElement>(
				Arrays.asList(simpleFormDemoPage.getFirstSumInputField(), simpleFormDemoPage.getSecondSumInputField()));

		ArrayList<String> firstInputList = new ArrayList<String>(Arrays.asList("5", "10"));
		ArrayList<String> secondInputList = new ArrayList<String>(Arrays.asList("15", "20"));

		int sumOne = 0;
		int sumTwo = 0;

		// Now enter the numbers, click on getSum and verify the sum matches
		for (int i = 0; i < firstInputList.size(); i++) {
			simpleFormDemoPage.enterSumValue(firstInputList.get(i), sumInputFieldList.get(i));
			sumOne += Integer.parseInt(firstInputList.get(i));
		}

		simpleFormDemoPage.clickOnGetSumButton();

		Assert.assertEquals(simpleFormDemoPage.getSumOrMessageValue(), Integer.toString(sumOne));

		// Now repeat the same thing for different set of numbers

		for (int i = 0; i < secondInputList.size(); i++) {
			simpleFormDemoPage.enterSumValue(secondInputList.get(i), sumInputFieldList.get(i));
			sumTwo += Integer.parseInt(secondInputList.get(i));
		}

		simpleFormDemoPage.clickOnGetSumButton();

		Assert.assertEquals(simpleFormDemoPage.getSumOrMessageValue(), Integer.toString(sumTwo));

	}

	@Test(priority = 2)
	public void verifySumInputFieldsFeatureWithNonNumericValues() {

		ArrayList<String> nonNumericInputList = new ArrayList<String>(Arrays.asList("a", "b"));

		var sumInputFieldList = new ArrayList<WebElement>(
				Arrays.asList(simpleFormDemoPage.getFirstSumInputField(), simpleFormDemoPage.getSecondSumInputField()));

		/*
		 * Now enter the non-numberic values, click on getSum and verify the error
		 * message is displayed
		 */
		for (int i = 0; i < nonNumericInputList.size(); i++) {
			simpleFormDemoPage.enterSumValue(nonNumericInputList.get(i), sumInputFieldList.get(i));
		}

		simpleFormDemoPage.clickOnGetSumButton();

		Assert.assertEquals(simpleFormDemoPage.getSumOrMessageValue(), expectedFailureMessage);

	}

	@Test(priority = 3)
	public void verifySumInputFieldsFeatureWithEmptyValues() {

		var sumInputFieldList = new ArrayList<WebElement>(
				Arrays.asList(simpleFormDemoPage.getFirstSumInputField(), simpleFormDemoPage.getSecondSumInputField()));

		/*
		 * Pass the empty-strings in sum input fields, click on getSum and verify the
		 * error message is displayed
		 */
		for (int i = 0; i < sumInputFieldList.size(); i++) {
			simpleFormDemoPage.enterSumValue("", sumInputFieldList.get(i));
		}

		simpleFormDemoPage.clickOnGetSumButton();

		Assert.assertEquals(simpleFormDemoPage.getSumOrMessageValue(), expectedFailureMessage);

	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
