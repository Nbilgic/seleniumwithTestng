package pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Sample page
 */
public class HomePage extends Page {

	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}

	public HomePage payThemAVisit() {

		WebElement visit = driver.findElement(By.xpath("//*[text()='Pay Them a Visit']"));
		((JavascriptExecutor) driver).executeScript("scroll(0, 250)", visit);
		visit.click();

		Set<String> allHandles = driver.getWindowHandles();
		if (allHandles.size() == 1)
			visit.click();
		return this;
	}

	public HomePage clickThisisCloudwise() throws InterruptedException {

		switchToNewWindow();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement thisisCloudwise = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='This is Cloudwise'])[1]")));
		thisisCloudwise.click();
		return this;
	}

	public HomePage getUsers() throws InterruptedException {

		Thread.sleep(3000);
		driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonAccept")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		clickElement(By.xpath("(//*[text()='All Cloudwisers'])[1]"));

		WebElement meet = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Meet our team']")));

		new Actions(driver).moveToElement(meet).click().perform();

		Thread.sleep(3000);

		getAllOfTheUsersFromDepartments();

		return this;
	}

	public void switchToNewWindow() {
		// To handle all new opened window
		String MainWindow = driver.getWindowHandle();
		System.out.println("Main window handle is " + MainWindow);

		Set<String> s1 = driver.getWindowHandles();
		System.out.println("Child window handle is" + s1);
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			driver.switchTo().window(ChildWindow);
			System.out.println(driver.getTitle());

		}

	}

	public void clickElement(By by) throws InterruptedException {
	//	WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elem = driver.findElement(by);
		JavascriptExecutor ex = (JavascriptExecutor) driver;
		ex.executeScript("arguments[0].scrollIntoView(true);arguments[0].focus", elem);
		elem.click();

	}

	public void getAllOfTheUsersFromDepartments() throws InterruptedException {

		ArrayList<String> allUsers = new ArrayList<String>();
		ArrayList<String> dublicateUsers = new ArrayList<String>();
		HashSet<String> uniqueUsers = new HashSet<String>();

		String depart[] = new String[] { "directie", "supportteam", "projecten-support", "Sales", "Marketing",
				"trainingen", "development", "hr-administratie", "logistiek-en-service" };

		for (int i = 0; i < depart.length; i++) {
			ArrayList<String> users = new ArrayList<String>();
			driver.navigate().to("https://cloudwise.nl/dit-is-cloudwise/alle-cloudwisers/" + depart[i] + "/");

			List<WebElement> txtNames = driver.findElements(By.xpath("//*[@class='flip-box-front']/div/h3"));
			for (WebElement b : txtNames) {

				users.add(b.getText());
				allUsers.add(b.getText());
				if (uniqueUsers.add(b.getText()) == false) {
					dublicateUsers.add(b.getText());
				}				
			}		

			System.out.println(depart[i] + " : " + users);
		}
		System.out.println("All users : " + allUsers);		
		System.out.println("Dublicate users :  " + dublicateUsers);
	}
}
