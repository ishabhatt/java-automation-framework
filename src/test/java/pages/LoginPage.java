package pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.SelfHealingElement;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement usernameInput;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordInput;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;

	public WebElement getLoginButton() {
		By[] byLocators = { By.id("loginBtn"), By.cssSelector("button[type='submit']"),
				By.xpath("//input[@value='Login']") };

		List<By> locators = Arrays.asList(byLocators);
		return new SelfHealingElement(driver).findElement(locators);
	}

	public WebElement getUsername() {
		By[] byLocators = { By.id("email"), By.cssSelector("input[name='username']"),
				By.xpath("//input[@name='email']") };

		List<By> locators = Arrays.asList(byLocators);
		return new SelfHealingElement(driver).findElement(locators);
	}

	public void enterUsername(String username) {
		usernameInput.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public void clickLoginButton() {
		getLoginButton().click();
	}

}
