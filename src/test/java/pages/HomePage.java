package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div#top-links a[href*='account/account']")
	WebElement accountLink;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerLink;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement loginLink;

	public void clickLoginMenu() {
		accountLink.click();
		loginLink.click();
	}

	public void clickRegisterMenu() {
		accountLink.click();
		registerLink.click();
	}

}
