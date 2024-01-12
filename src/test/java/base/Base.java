package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

		String browserName = properties.getProperty("browser");
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get("https://www.lambdatest.com/selenium-playground/");
			return driver;

		default:
			return null;
		}

	}

}
