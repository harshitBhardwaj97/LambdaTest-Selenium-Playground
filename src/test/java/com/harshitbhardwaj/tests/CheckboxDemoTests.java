package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.CheckboxDemoPage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxDemoTests extends Base {

    WebDriver driver;
    CheckboxDemoPage checkboxDemoPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        checkboxDemoPage = new CheckboxDemoPage(driver);
        homePage.navigateToCheckBoxDemoPage();
    }

    @Test(priority = 1)
    public void verifyTextIsDisplayed_afterSelectingFirstCheckBox() {

        var firstCheckBox = checkboxDemoPage.getFirstCheckBox();
        boolean isTextDisplayedBefore = checkboxDemoPage.checkFirstCheckBoxTextIsDisplayed();
        Assert.assertEquals(isTextDisplayedBefore, false);

        // Originally check-box should not be selected
        Assert.assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(firstCheckBox), false);

        // Select the first check-box
        checkboxDemoPage.selectCheckBox(firstCheckBox);

        // Update the isTextDisplayed variable after selecting the check-box
        boolean isTextDisplayedAfter = checkboxDemoPage.checkFirstCheckBoxTextIsDisplayed();

        // Now it should be displayed
        Assert.assertEquals(isTextDisplayedAfter, true);

        // Now check-box should also be selected
        Assert.assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(firstCheckBox), true);

    }

    @Test(priority = 2)
    public void verifyDisabledCheckBoxesCannotBeSelected() {

        var firstDisabledCheckBox = checkboxDemoPage.getFirstDisabledCheckBox();
        var secondDisabledCheckBox = checkboxDemoPage.getSecondDisabledCheckBox();

        boolean isFirstCheckBoxEnabled = checkboxDemoPage.checkWhetherCheckBoxIsDisabled(firstDisabledCheckBox);
        boolean isSecondCheckBoxEnabled = checkboxDemoPage.checkWhetherCheckBoxIsDisabled(secondDisabledCheckBox);

        // Assert both check-boxes are disabled or not enabled
        Assert.assertEquals(isFirstCheckBoxEnabled, false);
        Assert.assertEquals(isSecondCheckBoxEnabled, false);

        /*
         * Assert both check-boxes cannot be selected, by first clicking on them and
         * then checking whether they are selected or not
         */

        checkboxDemoPage.selectCheckBox(firstDisabledCheckBox);
        checkboxDemoPage.selectCheckBox(secondDisabledCheckBox);

        Assert.assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(firstDisabledCheckBox), false);
        Assert.assertEquals(checkboxDemoPage.checkWhetherCheckBoxIsSelected(secondDisabledCheckBox), false);

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
