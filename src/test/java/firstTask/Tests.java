package firstTask;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends TestNgTestBase {

	@Test
	public void firstTask() {

		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.findElement(By.id("mat-input-0")).sendKeys("5");
		driver.findElement(By.id("mat-input-1")).sendKeys("9");
		String result = driver.findElement(By.id("mat-input-2")).getAttribute("value");
		String expectedResult = "14";
		Assert.assertTrue(result.equals(expectedResult));

		// Unicorn
		driver.findElement(By.id("mat-input-3")).sendKeys("bobi" + Keys.ENTER);
		Assert.assertTrue(driver.findElements(By.xpath("//*[text()=' Bobi Codeshake ']")).size() != 0);

		// Return of Popup
		driver.findElement(By.xpath("//*[text()='Let the battle begin']")).click();
		driver.findElement(By.xpath("//i[@style='color: orange; cursor: pointer;']")).click();

		// Address
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe"));
		System.out.println(driver.findElement(By.xpath("//*[@id='comp-kvi6khho']/p/span")).getText());

	}
	
}
