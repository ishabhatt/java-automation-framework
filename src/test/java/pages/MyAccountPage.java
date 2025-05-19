package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']/li[last()]")
	WebElement currentPageBreadcrumb;

	@FindBy(css = "div#content h2:nth-child(1)")
	WebElement currentPageTitle;

	@FindBy(css = "div.list-group a[href$='/logout']")
	WebElement logoutLink;

	public boolean isMyAccountPage() {
		try {
			return currentPageBreadcrumb.getText().equals("Account");
		} catch (Exception e) {
			return false;
		}
	}

	public void clickLogout() {
		logoutLink.click();
	}

}
