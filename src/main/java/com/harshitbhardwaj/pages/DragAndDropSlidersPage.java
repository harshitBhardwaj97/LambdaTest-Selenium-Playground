package com.harshitbhardwaj.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DragAndDropSlidersPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//div[@id='slider1']/div/input")
    @CacheLookup
    private WebElement firstSliderInput;

    @FindBy(id = "range")
    private WebElement firstInputDisplayValue;

    @FindBy(xpath = "//div[@id='slider2']/div/input")
    @CacheLookup
    private WebElement secondSliderInput;

    @FindBy(id = "rangePrimary")
    private WebElement secondInputDisplayValue;

    @FindBy(xpath = "//div[@id='slider3']/div/input")
    @CacheLookup
    private WebElement thirdSliderInput;

    @FindBy(id = "rangeSuccess")
    private WebElement thirdInputDisplayValue;

    @FindBy(xpath = "//div[@id='slider4']/div/input")
    @CacheLookup
    private WebElement fourthSliderInput;

    @FindBy(id = "rangeInfo")
    private WebElement fourthInputDisplayValue;

    @FindBy(xpath = "//div[@id='slider5']/div/input")
    @CacheLookup
    private WebElement fifthSliderInput;

    @FindBy(id = "rangeWarning")
    private WebElement fifthInputDisplayValue;

    @FindBy(xpath = "//div[@id='slider6']/div/input")
    @CacheLookup
    private WebElement sixthSliderInput;

    @FindBy(id = "rangeDanger")
    private WebElement sixthInputDisplayValue;

    @FindBy(xpath = "//div[@id='slider7']/div/input")
    @CacheLookup
    private WebElement seventhSliderInput;

    @FindBy(xpath = "//div[@id='slider7']/div/output")
    private WebElement seventhInputDisplayValue;

    @FindBy(xpath = "//div[@id='slider8']/div/input")
    @CacheLookup
    private WebElement eigthSliderInput;

    @FindBy(xpath = "//div[@id='slider8']/div/output")
    private WebElement eigthInputDisplayValue;

    public DragAndDropSlidersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public Map<WebElement, WebElement> getInputSliderAndOutputValuesMap() {
        var inputSliderAndOutputValuesMap = new HashMap<WebElement, WebElement>();

        inputSliderAndOutputValuesMap.put(firstSliderInput, firstInputDisplayValue);
        inputSliderAndOutputValuesMap.put(secondSliderInput, secondInputDisplayValue);
        inputSliderAndOutputValuesMap.put(thirdSliderInput, thirdInputDisplayValue);
        inputSliderAndOutputValuesMap.put(fourthSliderInput, fourthInputDisplayValue);
        inputSliderAndOutputValuesMap.put(fifthSliderInput, fifthInputDisplayValue);
        inputSliderAndOutputValuesMap.put(sixthSliderInput, sixthInputDisplayValue);
        inputSliderAndOutputValuesMap.put(seventhSliderInput, seventhInputDisplayValue);
        inputSliderAndOutputValuesMap.put(eigthSliderInput, eigthInputDisplayValue);

        return inputSliderAndOutputValuesMap;

    }

    public boolean checkSlider(Map.Entry<WebElement, WebElement> sliderEntry) {

        final int MIN_RANGE = 1;
        final int MAX_RANGE = 100;

        boolean result = true; // Assuming slider is working fine originally

        WebElement inputSlider = sliderEntry.getKey();
        WebElement inputSliderValue = sliderEntry.getValue();

        inputSlider.click();

        // First of all after clicking the input slider, drag it all way across upto 100
        for (int i = 51; i <= MAX_RANGE; i++) {
            System.out.println("Current Value -> (Sliding Right) " + (Integer.parseInt(inputSliderValue.getText())));
            System.out.println("Current Index -> (Sliding Right) " + i);
            System.out.println("Current value equals current index -> (Sliding Right) "
                    + (Integer.parseInt(inputSliderValue.getText()) == i));

            if (!(Integer.parseInt(inputSliderValue.getText()) == i)) {
                result = false; // If values are found to be different, then store result as false
            }

            inputSlider.sendKeys(Keys.RIGHT);
        }

        // Now drag it till MIN_RANGE (1) and check for the same thing
        for (int i = 100; i >= MIN_RANGE; i--) {
            System.out.println("Current Value -> (Sliding Left) " + (Integer.parseInt(inputSliderValue.getText())));
            System.out.println("Current Index -> (Sliding Left) " + i);
            System.out.println("Current value equals current index -> (Sliding Left) "
                    + (Integer.parseInt(inputSliderValue.getText()) == i));

            if (!(Integer.parseInt(inputSliderValue.getText()) == i)) {
                result = false; // If values are found to be different, then store result as false
            }

            inputSlider.sendKeys(Keys.LEFT);
        }

        return result;
    }

}
