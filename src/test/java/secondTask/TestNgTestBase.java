package secondTask;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import pages.HomePage;
import pages.Page;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {

	protected static URL gridHubUrl = null;
	protected static String baseUrl;
	protected static Capabilities capabilities;

	protected WebDriver driver;

	@BeforeSuite
	public void initTestSuite() throws IOException {
		SuiteConfiguration config = new SuiteConfiguration();
		baseUrl = config.getProperty("codeshake.url");

		capabilities = config.getCapabilities();
	}

	@BeforeMethod
	public void initWebDriver() {
		driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		WebDriverPool.DEFAULT.dismissAll();
	}

	public HomePage startTest() {
		driver.get(baseUrl);
		driver.manage().window().maximize();
		return new HomePage(driver);
	}
	
}
