package stepDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import junit.framework.Assert;

/**
 * This class contains step definitions for Cucumber scenarios related to 
 * user interactions with the Free CRM application, such as login, navigation, 
 * and creating new deals.
 */
public class DealStepDefnition {
	
	// WebDriver instance to interact with the browser
	WebDriver driver;

	/**
	 * Initializes the WebDriver and navigates to the Free CRM login page.
	 */
	@Given("^user is already on Login Page$")
	public void user_already_on_login_page() {
		System.setProperty("webdriver.chrome.driver", "/Users/naveenkhunteta/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.freecrm.com/index.html");
	}

	/**
	 * Verifies the title of the login page.
	 */
	@When("^title of login page is Free CRM$")
	public void title_of_login_page_is_free_CRM() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("#1 Free CRM for Any Business: Online Customer Relationship Software", title);
	}

	/**
	 * Enters the username and password provided in the Cucumber DataTable.
	 * 
	 * @param credentials A DataTable containing username and password.
	 */
	@Then("^user enters username and password$")
	public void user_enters_username_and_password(DataTable credentials) {
		List<List<String>> data= credentials.asLists(String.class);
		driver.findElement(By.name("username")).sendKeys(data.get(0).get(0));
		driver.findElement(By.name("password")).sendKeys(data.get(0).get(1));
	}

	/**
	 * Clicks the login button using JavaScriptExecutor.
	 */
	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() {
		WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	/**
	 * Verifies that the user is on the home page by checking the page title.
	 */
	@Then("^user is on home page$")
	public void user_is_on_hopme_page() {
		String title = driver.getTitle();
		System.out.println("Home Page title ::" + title);
		Assert.assertEquals("CRMPRO", title);
	}

	/**
	 * Navigates to the "New Deal" page by interacting with the Deals menu.
	 */
	@Then("^user moves to new deal page$")
	public void user_moves_to_new_contact_page() {
		driver.switchTo().frame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Deals')]"))).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'New Deal')]")).click();
	}

	/**
	 * Enters deal details provided in the Cucumber DataTable.
	 * 
	 * @param dealData A DataTable containing deal details such as title, amount, 
	 *                 probability, and commission.
	 */
	@Then("^user enters deal details$")
	public void user_enters_contacts_details(DataTable dealData) {
		List<List<String>> dealValues = dealData.asLists(String.class);
		driver.findElement(By.id("title")).sendKeys(dealValues.get(0).get(0));
		driver.findElement(By.id("amount")).sendKeys(dealValues.get(0).get(1));
		driver.findElement(By.id("probability")).sendKeys(dealValues.get(0).get(2));
		driver.findElement(By.id("commission")).sendKeys(dealValues.get(0).get(3));
	
		driver.findElement(By.id("title")).sendKeys(dealValues.get(1).get(0));
		driver.findElement(By.id("amount")).sendKeys(dealValues.get(1).get(1));
		driver.findElement(By.id("probability")).sendKeys(dealValues.get(1).get(2));
		driver.findElement(By.id("commission")).sendKeys(dealValues.get(1).get(3));
	
	}

	/**
	 * Closes the browser and quits the WebDriver session.
	 */
	@Then("^Close the browser$")
	public void close_the_browser() {
		driver.quit();
	}
}
