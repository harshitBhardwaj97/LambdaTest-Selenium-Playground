package com.harshitbhardwaj.pages;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class BrokenImagePage {

    private final WebDriver driver;

    @FindBy(xpath = "//img[@src='abcd.jpg' and @alt='Image']")
    private WebElement brokenImageWithAlt;

    @FindBy(xpath = "//img[@src='abcd.jpg' and @alt='...']")
    private WebElement brokenImageWithoutAlt;

    public BrokenImagePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getImageOne() {
        return brokenImageWithAlt;
    }

    public WebElement getImageTwo() {
        return brokenImageWithoutAlt;
    }

    public boolean checkImageIsBrokenWithNaturalWidth(WebDriver driver, WebElement imageElement) {
        try {
            return (imageElement.getAttribute("naturalWidth").equals("0"));
        } catch (Exception e) {
            e.printStackTrace();
            return true; // Assuming image is actually broken to be causing an exception
        }

    }

    public boolean checkImageIsBrokenWithHttpResponse(WebElement imageElement) {
        try {
            String imageUrl = imageElement.getAttribute("src");

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(imageUrl);
            HttpResponse response = client.execute(request);

            // Check if the HTTP response code is in the success range
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("The status code received while trying to get the following image is -> " + statusCode);
            return !(statusCode >= 200 && statusCode < 300);
        } catch (IOException e) {
            e.printStackTrace();
            return true; // Assume the image is broken
        }
    }

}
