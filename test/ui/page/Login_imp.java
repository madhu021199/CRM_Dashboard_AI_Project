package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Login Page Implementation - Page Object Model
 * This class represents the Login page and contains all the elements and actions related to login functionality
 */
public class Login_imp {
    private WebDriver driver;

    // ...existing code... (web element fields removed)

    /**
     * Constructor - Initialize the WebDriver and initialize page elements using PageFactory
     *
     * @param driver WebDriver instance
     */
    public Login_imp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // ...existing code... (other methods removed - only constructor retained)
}
