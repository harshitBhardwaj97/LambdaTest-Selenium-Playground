package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.DataListFilterPage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

public class DataListFilterTests extends Base {

    WebDriver driver;
    DataListFilterPage dataListFilterPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        dataListFilterPage = new DataListFilterPage(driver);
        homePage.navigateToDataListFilterPage();
    }

    @Test(priority = 1)
    public void verifyOriginallySixItemsAreDisplayed() {

        int expectedCount = 6;
        int actualCount = dataListFilterPage.getDisplayedSearchContainerItemsCount();

        Assert.assertEquals(actualCount, expectedCount);

    }

    @Test(priority = 2)
    public void verifyFilterFunctionality_withMissingData() {

        int expectedCount = 0;
        String textToSearch = "Some Random Text";

        // Enter the random text in search field
        dataListFilterPage.enterTextInSearchInput(textToSearch);

        // Now verify that no rows are displayed in search container
        int actualCount = dataListFilterPage.getDisplayedSearchContainerItemsCount(); // should be 0
        Assert.assertEquals(actualCount, expectedCount);

        // Repeat it for some different random text also
        textToSearch = "This data won't be found!";
        dataListFilterPage.enterTextInSearchInput(textToSearch);

        actualCount = dataListFilterPage.getDisplayedSearchContainerItemsCount(); // should be 0
        Assert.assertEquals(actualCount, expectedCount);

    }

    @Test(priority = 2)
    public void verifyFilterFunctionality_withPresentData() {

        /*
         * This map contains the string that would be searched, and expected row count
         * that would be displayed upon searching this string
         */
        var presentDataMap = new LinkedHashMap<String, Integer>();

        // Searching by title
        presentDataMap.put("Manager", 3);
        presentDataMap.put("Developer", 1);
        presentDataMap.put("Tester", 1);

        // Searching by name
        presentDataMap.put("Dennis", 1);
        presentDataMap.put("Catherine", 1);

        // Searching by company
        presentDataMap.put("Ltd", 6);
        presentDataMap.put("Oakes", 1);

        /*
         * Now loop over the present data map, search for the string, verify the
         * displayed count, and also that text is displayed
         *
         */
        presentDataMap.forEach((searchText, expectedDisplayedRowsCount) -> {

            // Enter the search text in search field
            dataListFilterPage.enterTextInSearchInput(searchText);

            // Now verify that actualDisplayedRowsCount and expectedDisplayedRowsCount match
            Assert.assertEquals(dataListFilterPage.getDisplayedSearchContainerItemsCount(), expectedDisplayedRowsCount);

            // Also verify that displayedRows contain the searchText
            Assert.assertTrue(dataListFilterPage.checkDisplayedSearchContainerItemsText(searchText));
        });

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
