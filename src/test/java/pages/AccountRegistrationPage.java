package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstNameInput;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastNameInput;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailInput;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement telephoneInput;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordInput;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confirmPasswordInput;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement policyChk;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueBtn;

	@FindBy(css = "div#content h1")
	WebElement successMsg;

	public void enterFirstName(String fname) {
		firstNameInput.sendKeys(fname);
	}

	public void enterLastName(String lname) {
		lastNameInput.sendKeys(lname);
	}

	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}

	public void enterTelephone(String phonenumber) {
		telephoneInput.sendKeys(phonenumber);
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public void enterConfirmPassword(String password) {
		confirmPasswordInput.sendKeys(password);
	}

	public void agreePolicy() {
		policyChk.click();
	}

	public void clickContinueButton() {
		// sol 1
		continueBtn.click();

		// sol2
		// continueBtn.submit();

		// sol3
		// Actions action = new Action(driver);
		// action.click(continueBtn).click().perform();

		// sol4
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		// js.executeScript("arguments[0].click()", continueBtn);

		// sol 5
		// continueBtn.sendKeys(Keys.RETURN);

		// sol6
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	}

	public String getSuccessMessage() {
		try {
			return successMsg.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
