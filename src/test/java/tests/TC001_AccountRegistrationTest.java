package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.AccountRegistrationPage;
import pages.HomePage;

public class TC001_AccountRegistrationTest extends TestBase {

	@Test(groups = { "regression", "master" })
	public void verify_account_registration() {

		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		try {

			logger.info(testName, "****** " + testName + " ******");

			HomePage homepage = new HomePage(driver);

			logger.info("Clicking Register Menu");
			homepage.clickRegisterMenu();

			AccountRegistrationPage registerpage = new AccountRegistrationPage(driver);

			logger.info("Enter details");
			registerpage.enterFirstName(randomString().toUpperCase());
			registerpage.enterLastName(randomString().toUpperCase());
			registerpage.enterEmail(randomString() + "@gmail.com");
			registerpage.enterTelephone(randomNumber());
			String password = randomAlphaNumber();
			registerpage.enterPassword(password);
			registerpage.enterConfirmPassword(password);
			registerpage.agreePolicy();

			logger.info("Click Continue");
			registerpage.clickContinueButton();

			String expectedMessage = "Your Account Has Been Created!";

			String actualString = registerpage.getSuccessMessage();
			logger.info("Actual Message: " + actualString);
			logger.info("Expected Message: " + expectedMessage);
			if (!actualString.equals(expectedMessage)) {
				logger.error("Test Failed with Error!");
			} else {
				logger.info("PASS!");
			}
			Assert.assertEquals(registerpage.getSuccessMessage(), expectedMessage);

		} catch (Exception e) {
			logger.debug(e.getStackTrace().toString());
			Assert.fail();
		}
		logger.info("Completed!");

	}
}
