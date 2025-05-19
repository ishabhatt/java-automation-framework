package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;

public class TC002_LoginTest extends TestBase {

	@Test(groups = { "sanity", "master" })
	public void verify_login() {

		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		try {

			logger.info(testName, "****** " + testName + " ******");

			HomePage homepage = new HomePage(driver);

			logger.info("Clicking Login Menu");
			homepage.clickLoginMenu();

			LoginPage loginpage = new LoginPage(driver);

			logger.info("Enter credentials");
			loginpage.enterUsername(prop.getProperty("username"));
			loginpage.enterPassword(prop.getProperty("password"));

			logger.info("Click Login Button");
			loginpage.clickLoginButton();

			MyAccountPage myaccountpage = new MyAccountPage(driver);

			if (!myaccountpage.isMyAccountPage()) {
				logger.error("Test Failed with Error!");
			} else {
				logger.info("PASS!");
			}
			Assert.assertTrue(myaccountpage.isMyAccountPage(), "My Account page not displayed after login");
		} catch (Exception e) {
			logger.debug(e.getStackTrace().toString());
			Assert.fail();
		}
		logger.info("Completed!");

	}

}
