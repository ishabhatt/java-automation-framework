package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelfHealingElement {

	private WebDriver driver;

	public SelfHealingElement(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Attempts to find the first visible WebElement from a list of locators.
	 * 
	 * @param locators List of By locators to try.
	 * @return WebElement if found, else throws NoSuchElementException.
	 */
	public WebElement findElement(List<By> locators) {
		for (By locator : locators) {
			try {
				WebElement element = driver.findElement(locator);
				System.out.println("Locator: " + locator + " element.isDisplayed():" + element.isDisplayed());
				if (element.isDisplayed()) {
					System.out.println("Element found using locator: " + locator.toString());
					return element;
				}
			} catch (Exception e) {
				System.out.println("Locator failed: " + locator.toString());
			}
		}
		throw new NoSuchElementException("None of the locators matched any element on the page.");
	}
}
