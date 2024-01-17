package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	protected WebDriver driver;
	protected WebDriverWait webDriverWait;
	static Properties properties;
	static String dir = System.getProperty("user.dir");
	public static String downloadsDirectory = dir + "\\src\\test\\resources\\downloads";
	public static String uploadDirectory = dir + "\\src\\test\\resources\\uploads";
	public static String resourcesDirectory = dir + "\\src\\test\\resources";
	public static String testOutput = dir + "\\test-output";
	String baseUrl = "https://www.lambdatest.com/selenium-playground/";

	public Base() {
		try {
			properties = new Properties();
			FileInputStream propertyFile = new FileInputStream(dir + "\\src\\test\\java\\config\\config.properties");
			properties.load(propertyFile);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeDriver() {

		/*
		 * By default, Chrome will be considered as the browser, hence chromeOptions are
		 * defined here once
		 */
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		// Uncomment the below line to execute tests in headless mode
//		chromeOptions.addArguments("--headless");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", downloadsDirectory);
		chromeOptions.setExperimentalOption("prefs", prefs);
		chromeOptions.addArguments("disable-popup-blocking");

		try {
			String browserName = properties.getProperty("browser");
			switch (browserName.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.get(baseUrl);
				return driver;

			default:
				WebDriverManager.chromedriver().setup();
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
