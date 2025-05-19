package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDTest extends TestBase {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = { "functional", "master" })
	public void verify_login_data(String username, String password, String expResult) {

		try {

			logger.info("****** verify_login_data ******");

			HomePage homepage = new HomePage(driver);
			logger.info("Clicking Login Menu");
			homepage.clickLoginMenu();

			LoginPage loginpage = new LoginPage(driver);

			logger.info("Enter credentials: " + username);
			loginpage.enterUsername(username);
			loginpage.enterPassword(password);

			logger.info("Click Login Button");
			loginpage.clickLoginButton();

			MyAccountPage myaccountpage = new MyAccountPage(driver);
			boolean myAccountDisplayed = myaccountpage.isMyAccountPage();
			logger.info("Is Account Page Displayed: " + myAccountDisplayed);
			logger.info("expResult Excel: " + expResult);

			if (expResult.equalsIgnoreCase("valid")) {
				if (myAccountDisplayed == true) {
					myaccountpage.clickLogout();
					Assert.assertTrue(myAccountDisplayed, "My Account page not displayed after login");
				} else {
					Assert.assertTrue(myAccountDisplayed, "My Account page not displayed after login");
				}
			}
			if (expResult.equalsIgnoreCase("invalid")) {
				if (myAccountDisplayed == true) {
					myaccountpage.clickLogout();
					Assert.assertFalse(myAccountDisplayed, "My Account page displayed after invalid login");
				} else {
					Assert.assertFalse(myAccountDisplayed, "My Account page displayed after invalid login");
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
			System.err.println("An exception occurred:");
			e.printStackTrace();
			Assert.fail();
		}
		logger.info("Completed!");

	}

}
