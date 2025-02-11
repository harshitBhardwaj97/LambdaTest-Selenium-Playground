package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.pages.RadioButtonsDemoPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RadioButtonsDemoTests extends Base {

    WebDriver driver;
    RadioButtonsDemoPage radioButtonsDemoPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        radioButtonsDemoPage = new RadioButtonsDemoPage(driver);
        homePage.navigateToRadioButtonsDemoPage();
    }

    @Test(priority = 1)
    public void verifyCorrectMessageIsDisplayed_afterSelectionOfFirstMaleRadioButton() {

        var firstMaleRadioButton = radioButtonsDemoPage.getFirstMaleRadioButton();
        String expectedMessage = "Radio button 'Male' is checked";

        radioButtonsDemoPage.selectThePassedRadioButton(firstMaleRadioButton);

        Assert.assertTrue(radioButtonsDemoPage.checkPassedRadioButtonIsSelected(firstMaleRadioButton));

        radioButtonsDemoPage.clickOnFirstGetValueButton();

        String actualMessage = radioButtonsDemoPage.getFirstGenderValueAfterSelection();

        Assert.assertEquals(actualMessage, expectedMessage);

    }

    @Test(priority = 2)
    public void verifyCorrectMessageIsDisplayed_afterSelectionOfFirstFemaleRadioButton() {

        var firstFemaleRadioButton = radioButtonsDemoPage.getFirstFemaleRadioButton();
        String expectedMessage = "Radio button 'Female' is checked";

        radioButtonsDemoPage.selectThePassedRadioButton(firstFemaleRadioButton);

        Assert.assertTrue(radioButtonsDemoPage.checkPassedRadioButtonIsSelected(firstFemaleRadioButton));

        radioButtonsDemoPage.clickOnFirstGetValueButton();

        String actualMessage = radioButtonsDemoPage.getFirstGenderValueAfterSelection();

        Assert.assertEquals(actualMessage, expectedMessage);

    }

    @Test(priority = 3)
    public void verifyDisabledRadioButtonIsNotSelected() {

        var disabledRadioButton = radioButtonsDemoPage.getDisabledRadioButton();

        // Initially without clicking the disabled radio button is not selected
        Assert.assertFalse(radioButtonsDemoPage.checkPassedRadioButtonIsSelected(disabledRadioButton));

        radioButtonsDemoPage.selectThePassedRadioButton(disabledRadioButton);

        // Now also the radio button should not be selected
        Assert.assertFalse(radioButtonsDemoPage.checkPassedRadioButtonIsSelected(disabledRadioButton));

    }

    @Test(priority = 4)
    public void verifyCorrectValuesAreDisplayed_forAllCombinationsOfGenderAndAge() {

        var genderButtons = radioButtonsDemoPage.getAllPossibleGenderButtons();
        var ageGroupButtons = radioButtonsDemoPage.getAllPossibleAgeGroupButtons();

        /*
         * Now loop through all the combinations, click on radio buttons, and verify the
         * message for each combination
         */

        for (var genderButton : genderButtons) {
            for (var ageGroupButton : ageGroupButtons) {

                genderButton.click();
                ageGroupButton.click();
                radioButtonsDemoPage.clickOnGetValuesButton();
                System.out.println("Gender -> " + genderButton.getText());
                System.out.println("Age -> " + ageGroupButton.getText());

                // Assert that gender values match after clicking on get Values
                Assert.assertEquals(genderButton.getText(), radioButtonsDemoPage.getCombinedGenderValueText());

                /*
                 * Assert that age values match after clicking on get Values, but first replace
                 * ' to ' with ' - '
                 */
                Assert.assertEquals(ageGroupButton.getText(),
                        radioButtonsDemoPage.getCombinedAgeValueText().replaceAll(" - ", " to "));
            }
        }

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
