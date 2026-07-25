package page;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Login Page Implementation - Page Object Model
 * This class represents the Login page and contains all the elements and actions related to login functionality
 */
public class Login_imp {
    private static WebDriver driver;

    // ...existing code... (web element fields removed)
@Before
    public void setUp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Method for launching the url
    public static void navigateURL() throws InterruptedException {
        requireDriver();
        String url ="https://crm.osllc.us/admin/auth/login";
        driver.get(url);
        Thread.sleep(3000);
    }


    public static void requireDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Initialize it in a hook or test runner before executing steps.");
        }
    }

    public void clearInput(By[] locators) {
        WebElement input = requireElement(locators, "Input field was not found");
        input.clear();
    }

    public void enterCredentials(String usernameValue, String passwordValue) {
        WebElement username = requireElement(usernameLocators(), "Username field was not found");
        WebElement password = requireElement(passwordLocators(), "Password field was not found");
        username.clear();
        username.sendKeys(usernameValue);
        password.clear();
        password.sendKeys(passwordValue);
    }

    public WebElement requireElement(By[] locators, String failureMessage) {
        WebElement element = findFirstPresent(locators);
        Assert.assertNotNull(failureMessage, element);
        return element;
    }

    public WebElement findFirstPresent(By... locators) {
        for (By locator : locators) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                return elements.get(0);
            }
        }
        return null;
    }

    public By[] usernameLocators() {
        return new By[]{
            By.id("username"),
            By.name("username"),
            By.cssSelector("input[type='text']"),
            By.cssSelector("input[name*='user']")
        };
    }

    public By[] passwordLocators() {
        return new By[]{
            By.id("password"),
            By.name("password"),
            By.cssSelector("input[type='password']")
        };
    }

    public By[] signInLocators() {
        return new By[]{
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(normalize-space(), 'Sign in') or contains(normalize-space(), 'Sign In')]"),
            By.xpath("//input[@type='submit' and (contains(@value, 'Sign in') or contains(@value, 'Sign In'))]")
        };
    }

    public By[] passwordToggleLocators() {
        return new By[]{
            By.xpath("//button[contains(@aria-label, 'show') or contains(@aria-label, 'hide')]"),
            By.xpath("//button[contains(@class, 'eye') or contains(@class, 'toggle')]"),
            By.xpath("//*[contains(@class, 'eye') or contains(@class, 'toggle-password')]")
        };
    }

    public By[] logoutLocators() {
        return new By[]{
            By.xpath("//button[contains(normalize-space(), 'Logout') or contains(normalize-space(), 'Log out')]"),
            By.xpath("//a[contains(normalize-space(), 'Logout') or contains(normalize-space(), 'Log out')]")
        };
    }
}
