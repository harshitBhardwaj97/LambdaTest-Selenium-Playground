package tests;

import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.DragAndDropSlidersPage;
import pages.HomePage;

public class DragAndDropSliders extends Base {

	WebDriver driver;
	DragAndDropSlidersPage dragAndDropSlidersPage;
	HomePage homePage;

	@BeforeMethod
	void setup() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		dragAndDropSlidersPage = new DragAndDropSlidersPage(driver);
		homePage.navigateToDragAndDropSlidersPage();
	}

	@Test(priority = 1)
	public void verifySliderFunctionalityForRandomSlider() {

		var inputSliderAndValueMap = dragAndDropSlidersPage.getInputSliderAndOutputValuesMap();
		var singleSlider = getRandomMapEntry(inputSliderAndValueMap);

		Assert.assertTrue(dragAndDropSlidersPage.checkSlider(singleSlider));

	}

	// To get a pseudo-random map entry of all the input sliders
	private Map.Entry<WebElement, WebElement> getRandomMapEntry(Map<WebElement, WebElement> map) {
		Random random = new Random();
		int randomIndex = random.nextInt(map.size());

		return map.entrySet().stream().skip(randomIndex).findFirst().orElseThrow();
	}

	@AfterMethod
	void closeWindow() {
		driver.quit();
	}

}
