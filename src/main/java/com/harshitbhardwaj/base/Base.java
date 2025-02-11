package com.harshitbhardwaj.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Base {

    static Properties properties;
    static String dir = createPath(System.getProperty("user.dir"));

    // Use File.separator for platform independence
    public static String downloadsDirectory = createPath(dir, "src", "test", "resources", "downloads");
    public static String uploadDirectory = createPath(dir, "src", "test", "resources", "uploads");
    public static String resourcesDirectory = createPath(dir, "src", "test", "resources");
    public static String testOutput = createPath(dir, "test-output");

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    String baseUrl = "https://www.lambdatest.com/selenium-playground/";

    public Base() {
        try {
            properties = new Properties();
            // Use File.separator for platform independence
            FileInputStream propertyFile = new FileInputStream(
                    createPath(dir, "src", "main", "java", "com", "harshitbhardwaj", "config", "config.properties"));
            properties.load(propertyFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String createPath(String... paths) {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Paths cannot be empty");
        }

        for (final String path : paths) {
            if (path.isEmpty()) {
                throw new IllegalArgumentException("Path cannot be empty");
            }
        }

        if (paths.length == 1) return paths[0];

        final StringBuilder result = new StringBuilder();
        result.append(paths[0]);
        int i = 1;

        while (i < paths.length) {
            result.append(File.separator);
            result.append(paths[i]);
            i++;
        }
        return result.toString();
    }

    public WebDriver initializeDriver() {
        /*
         * By default, Chrome will be considered as the browser, hence chromeOptions are
         * defined here once
         */
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        // Uncomment the below line to execute tests in headless mode
        // chromeOptions.addArguments("--headless");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadsDirectory);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("disable-popup-blocking");

        try {
            String browserName = properties.getProperty("browser");
            switch (browserName.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    driver.get(baseUrl);
                    return driver;

                default:
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    driver.get(baseUrl);
                    return driver;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Please make sure browser property is passed in config.properties.");
            return null;
        }
    }
}
