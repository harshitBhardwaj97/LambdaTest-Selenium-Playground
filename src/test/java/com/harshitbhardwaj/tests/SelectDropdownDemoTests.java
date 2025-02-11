package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.pages.SelectDropdownDemoPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SelectDropdownDemoTests extends Base {

    WebDriver driver;
    SelectDropdownDemoPage selectDropdownDemoPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        selectDropdownDemoPage = new SelectDropdownDemoPage(driver);
        homePage.navigateToSelectDropdownDemoPage();
    }

    @Test(priority = 1)
    public void verifySingleSelectFunctionality() {

        /*
         * In this will add each day at last and then check the message, for example :
         * Day selected :- Monday
         *
         */
        String expectedMessage;

        String actualMessage;

        var dayOptionNames = new ArrayList<String>(
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

        for (String day : dayOptionNames) {
            expectedMessage = "Day selected :- " + day;
            selectDropdownDemoPage.selectDayOption(day);

            // Now the message should be displayed
            Assert.assertTrue(selectDropdownDemoPage.checkFirstSelectMessageIsDisplayed());

            actualMessage = selectDropdownDemoPage.getFirstSelectMessage();

            // And correct message should be displayed according to selected day
            Assert.assertEquals(actualMessage, expectedMessage);
        }

    }

    @Test(priority = 2)
    public void verifyMultiSelectFunctionality() {

        String expectedStateOneMessage, expectedStateTwoMessage, actualStateOneMessage, actualStateTwoMessage;

        var statesOptionsList = new ArrayList<String>(Arrays.asList("California", "Florida", "New Jersey", "New York",
                "Ohio", "Texas", "Pennsylvania", "Washington"));

        var twoRandomStates = getTwoRandomStates(statesOptionsList);

        /*
         * First select state one, then click on first Selected, then check the first
         * message
         */
        selectDropdownDemoPage.selectStateOption(twoRandomStates[0]);
        selectDropdownDemoPage.clickOnFirstSelectedButton();

        actualStateOneMessage = selectDropdownDemoPage.getFirstSelectedStateMessage();
        expectedStateOneMessage = "First selected option is : " + twoRandomStates[0];

        Assert.assertEquals(actualStateOneMessage, expectedStateOneMessage);

        // Now do the same thing for State two
        selectDropdownDemoPage.selectStateOption(twoRandomStates[1]);
        selectDropdownDemoPage.clickOnLastSelectedButton();

        actualStateTwoMessage = selectDropdownDemoPage.getLastSelectedStateMessage();
        expectedStateTwoMessage = "Last selected option is :\n" + twoRandomStates[1];

        Assert.assertEquals(actualStateTwoMessage, expectedStateTwoMessage);

    }

    String[] getTwoRandomStates(ArrayList<String> states) {

        final int NUMBER_OF_STATES_TO_SELECT = 2;
        String[] randomStatesList = new String[NUMBER_OF_STATES_TO_SELECT];

        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_STATES_TO_SELECT; i++) {
            int randomIndex = random.nextInt(states.size());
            randomStatesList[i] = states.get(randomIndex);
        }

        return randomStatesList;
    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
