package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.pages.InputFormSubmitPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

public class InputFormSubmitTests extends Base {

    WebDriver driver;
    InputFormSubmitPage inputFormSubmitPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        inputFormSubmitPage = new InputFormSubmitPage(driver);
        homePage.navigateToInputFormSubmitPage();
    }

    @Test(priority = 1)
    public void verifyInputFormDataAreRequired() {

        var allInputFields = inputFormSubmitPage.getAllInputFields();

        allInputFields.forEach((fieldName, webElement) -> {
            Assert.assertTrue(inputFormSubmitPage.checkRequiredAttributeIsPresentForInputField(webElement));

            if (fieldName.equals("email")) {
                Assert.assertTrue(inputFormSubmitPage.checkRequiredAttributeIsPresentForInputField(webElement));
                Assert.assertEquals(webElement.getAttribute("type"), "email");
            }

        });

    }

    @Test(priority = 2)
    public void verifyFormSubmitSuccessMessageIsNotDisplayed_withoutEnteringTheFormData() {

        // Originally the success message should not be displayed
        Assert.assertFalse(inputFormSubmitPage.checkFormSubmitSuccessMessageIsDisplayed());

        inputFormSubmitPage.clickOnFormSubmitButton();

        /*
         * After clicking submit button, once again verify that success message is not
         * displayed
         */
        Assert.assertFalse(inputFormSubmitPage.checkFormSubmitSuccessMessageIsDisplayed());

    }

    @Test(priority = 3)
    public void verifyFormSubmitSuccessMessageIsDisplayed_afterEnteringTheFormData() {

        // Originally the success message should not be displayed
        Assert.assertFalse(inputFormSubmitPage.checkFormSubmitSuccessMessageIsDisplayed());

        // Now enter the correct form data
        var inputFormData = new LinkedHashMap<String, String>();

        inputFormData.put("name", "test");
        inputFormData.put("email", "test@mail.com");
        inputFormData.put("password", "password");
        inputFormData.put("company", "company");
        inputFormData.put("website", "abc.com");
        inputFormData.put("city", "Delhi");
        inputFormData.put("addressOne", "New Delhi");
        inputFormData.put("addressTwo", "New Delhi");
        inputFormData.put("state", "Delhi");
        inputFormData.put("zipCode", "110001");

        var allInputFields = inputFormSubmitPage.getAllInputFields();

        allInputFields.forEach((fieldName, webElement) -> {

            // Enter data based on field name
            if (inputFormData.containsKey(fieldName)) {
                // Get the value that matches the key name to enter it in inputField
                String value = inputFormData.get(fieldName);
                webElement.clear();
                webElement.sendKeys(value);
            }
        });

        // Select the country option
        inputFormSubmitPage.selectCountryOption();

        /*
         * After entering the data and clicking submit button, verify that success
         * message is displayed
         */
        inputFormSubmitPage.clickOnFormSubmitButton();
        Assert.assertTrue(inputFormSubmitPage.checkFormSubmitSuccessMessageIsDisplayed());

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
