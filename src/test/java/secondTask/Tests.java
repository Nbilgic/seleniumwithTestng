package secondTask;

import org.testng.annotations.Test;

public class Tests extends TestNgTestBase {

	@Test
	public void listOfUsers() throws InterruptedException {
		startTest().payThemAVisit().clickThisisCloudwise().getUsers();

	}
	
}