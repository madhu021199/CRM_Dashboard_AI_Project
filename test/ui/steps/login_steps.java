package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.Login_imp;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class login_steps {
    private WebDriver driver;
    private String previousPasswordType;
    private final Login_imp loginPage = new Login_imp();

    public login_steps() {
    }

    public login_steps(WebDriver driver) {
        this.driver = driver;
    }

    @Before
    public void beforeScenario() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        loginPage.setUp(this.driver);
    }

    @After
    public void afterScenario() {
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }

    private Login_imp page() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Initialize it in a hook or test runner before executing steps.");
        }
        return loginPage;
    }

    @Given("the user opens the browser and navigates to the CRM application URL")
    public void the_user_opens_the_browser_and_navigates_to_the_crm_application_url() throws InterruptedException {
        Login_imp.navigateURL();
    }

    @Then("the CRM login page should be displayed with Username, Password fields and a Sign In button")
    public void the_crm_login_page_should_be_displayed_with_username_password_fields_and_a_sign_in_button() {
        page().requireDriver();
        Assert.assertNotNull("Username field was not found", page().findFirstPresent(page().usernameLocators()));
        Assert.assertNotNull("Password field was not found", page().findFirstPresent(page().passwordLocators()));
        Assert.assertNotNull("Sign in button was not found", page().findFirstPresent(page().signInLocators()));
    }

    @When("^the user types a password into the Password field and clicks the \"eye\" \\(show/hide\\) icon$")
    public void the_user_types_a_password_and_clicks_eye_icon() {
        page().requireDriver();
        WebElement password = page().requireElement(page().passwordLocators(), "Password field was not found");
        password.clear();
        password.sendKeys(System.getProperty("crm.test.pass", "demo"));
        previousPasswordType = password.getAttribute("type");
        WebElement eyeToggle = page().requireElement(page().passwordToggleLocators(), "Password show/hide icon was not found");
        eyeToggle.click();
    }

    @Then("the password should toggle between masked and plain text view")
    public void the_password_should_toggle_between_masked_and_plain_text_view() {
        page().requireDriver();
        WebElement password = page().requireElement(page().passwordLocators(), "Password field was not found");
        String currentType = password.getAttribute("type");
        Assert.assertNotNull("Password input type is unavailable", currentType);
        Assert.assertNotEquals("Password visibility did not toggle", previousPasswordType, currentType);
    }

    @When("the user enters a valid username and a valid password")
    public void the_user_enters_a_valid_username_and_a_valid_password() {
        page().requireDriver();
        WebElement username = page().requireElement(page().usernameLocators(), "Username field was not found");
        WebElement password = page().requireElement(page().passwordLocators(), "Password field was not found");
        username.clear();
        username.sendKeys(System.getProperty("crm.test.user", "demo"));
        password.clear();
        password.sendKeys(System.getProperty("crm.test.pass", "demo"));
    }

    @And("clicks the \"Sign in\" button")
    public void clicks_the_sign_in_button() {
        page().requireDriver();
        WebElement signIn = page().requireElement(page().signInLocators(), "Sign in button was not found");
        signIn.click();
    }

    @Then("the user should be successfully redirected to the CRM dashboard page")
    public void the_user_should_be_successfully_redirected_to_the_crm_dashboard_page() {
        page().requireDriver();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse("User is still on login page", currentUrl.toLowerCase().contains("login"));
    }

    @When("the user clicks the \"Logout\" button on the dashboard")
    public void the_user_clicks_the_logout_button_on_the_dashboard() {
        page().requireDriver();
        WebElement logout = page().requireElement(page().logoutLocators(), "Logout button was not found");
        logout.click();
    }

    @Then("the user should be redirected to the CRM login page")
    public void the_user_should_be_redirected_to_the_crm_login_page() {
        page().requireDriver();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("User was not redirected to login page", currentUrl.toLowerCase().contains("login"));
    }

    @And("the session should be terminated")
    public void the_session_should_be_terminated() {
        page().requireDriver();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("Session appears active; expected login page after logout", currentUrl.toLowerCase().contains("login"));
    }

    @Given("the user is on the CRM login page")
    public void the_user_is_on_the_crm_login_page() {
        page().requireDriver();
        String url = System.getProperty("crm.url", "http://localhost:8080");
        driver.get(url);
    }

    @When("the user leaves both the Username and Password fields empty and clicks \"Sign in\"")
    public void the_user_leaves_both_fields_empty_and_clicks_sign_in() {
        page().requireDriver();
        page().clearInput(page().usernameLocators());
        page().clearInput(page().passwordLocators());
        clicks_the_sign_in_button();
    }

    @Then("validation error messages should be displayed for both fields")
    public void validation_error_messages_should_be_displayed_for_both_fields() {
        page().requireDriver();
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue("Username required validation message was not shown", page.contains("username is required"));
        Assert.assertTrue("Password required validation message was not shown", page.contains("password is required"));
    }

    @And("the user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {
        page().requireDriver();
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue("User did not remain on login page", currentUrl.contains("login"));
    }

    @When("the user leaves the Username field empty, enters a valid password, and clicks \"Sign in\"")
    public void the_user_leaves_username_empty_enters_valid_password_and_clicks_sign_in() {
        page().requireDriver();
        page().clearInput(page().usernameLocators());
        WebElement password = page().requireElement(page().passwordLocators(), "Password field was not found");
        password.clear();
        password.sendKeys(System.getProperty("crm.test.pass", "demo"));
        clicks_the_sign_in_button();
    }

    @Then("a validation error message \"Username is required\" should be displayed")
    public void username_required_message_should_be_displayed() {
        page().requireDriver();
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue("Expected 'Username is required' message was not shown", page.contains("username is required"));
    }

    @When("the user enters a valid username, leaves the Password field empty, and clicks \"Sign in\"")
    public void the_user_enters_valid_username_leaves_password_empty_and_clicks_sign_in() {
        page().requireDriver();
        WebElement username = page().requireElement(page().usernameLocators(), "Username field was not found");
        username.clear();
        username.sendKeys(System.getProperty("crm.test.user", "demo"));
        page().clearInput(page().passwordLocators());
        clicks_the_sign_in_button();
    }

    @Then("a validation error message \"Password is required\" should be displayed")
    public void password_required_message_should_be_displayed() {
        page().requireDriver();
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue("Expected 'Password is required' message was not shown", page.contains("password is required"));
    }

    @When("the user enters an invalid/non-existent username with a valid password and clicks \"Sign in\"")
    public void the_user_enters_invalid_username_with_valid_password_and_clicks_sign_in() {
        page().requireDriver();
        page().enterCredentials("invalid_user_123", System.getProperty("crm.test.pass", "demo"));
        clicks_the_sign_in_button();
    }

    @When("the user enters a valid username with an incorrect password and clicks \"Sign in\"")
    public void the_user_enters_valid_username_with_incorrect_password_and_clicks_sign_in() {
        page().requireDriver();
        page().enterCredentials(System.getProperty("crm.test.user", "demo"), "wrong_password_123");
        clicks_the_sign_in_button();
    }

    @When("the user enters both an invalid username and an invalid password and clicks \"Sign in\"")
    public void the_user_enters_invalid_username_and_invalid_password_and_clicks_sign_in() {
        page().requireDriver();
        page().enterCredentials("invalid_user_123", "wrong_password_123");
        clicks_the_sign_in_button();
    }

    @Then("an error pop-up with message \"Invalid username or password\" should be displayed")
    public void invalid_credentials_error_should_be_displayed() {
        page().requireDriver();
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue("Expected invalid credentials message was not shown", page.contains("invalid username or password"));
    }

}
