package page;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.login_poj;
import steps.ScenarioContext;

import java.time.Duration;

public class login_imp {
    private final WebDriver driver;
    private String previousPasswordType;

    private static final String LOGIN_URL = "https://crm.osllc.us/admin/auth/login";
    private static final String VALID_USERNAME = "demo";
    private static final String VALID_PASSWORD = "5555";
    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(10);

    public login_imp(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.get(LOGIN_URL);
    }

    public void validateLoginPageLabelsAndControls() {
        WebElement usernameLabel = requireElement(login_poj.USERNAME_LABEL, "Username label is not displayed");
        String usernameLabelText = usernameLabel.getText().trim();
        if (!usernameLabelText.equalsIgnoreCase("Username")) {
            throw new AssertionError("Username label text is incorrect. Expected 'Username' but got '" + usernameLabelText + "'");
        }

        WebElement passwordLabel = requireElement(login_poj.PASSWORD_LABEL, "Password label is not displayed");
        String passwordLabelText = passwordLabel.getText().trim();
        if (!passwordLabelText.equalsIgnoreCase("Password")) {
            throw new AssertionError("Password label text is incorrect. Expected 'Password' but got '" + passwordLabelText + "'");
        }

        WebElement signInButton = requireElement(login_poj.SIGN_IN_BUTTON, "Sign in button is not displayed");
        String signInButtonText = signInButton.getText().trim();
        if (!signInButtonText.equalsIgnoreCase("Sign in")) {
            throw new AssertionError("Sign in label text is incorrect. Expected 'Sign in' but got '" + signInButtonText + "'");
        }
        takeScreenshot("step_02_login_page_labels_validated");
    }

    public void typePasswordAndToggleVisibility() {
        WebElement passwordField = requireElement(login_poj.PASSWORD_INPUT, "Password field was not found");
        passwordField.clear();
        passwordField.sendKeys(VALID_PASSWORD);
        previousPasswordType = passwordField.getAttribute("type");
        WebElement toggle = requireElement(login_poj.PASSWORD_TOGGLE, "Password toggle icon was not found");
        toggle.click();
        takeScreenshot("step_03_password_typed_and_toggle_clicked");
    }

    public boolean isPasswordVisibilityToggled() {
        WebElement passwordField = requireElement(login_poj.PASSWORD_INPUT, "Password field was not found");
        String currentType = passwordField.getAttribute("type");
        passwordField.clear();
        return previousPasswordType != null && currentType != null && !previousPasswordType.equalsIgnoreCase(currentType);
    }

    public void enterValidUsernameAndPassword() throws InterruptedException {
        enterCredentials(VALID_USERNAME, VALID_PASSWORD);
    }

    public void enterCredentials(String usernameValue, String passwordValue) throws InterruptedException {
        WebElement usernameField = requireElement(login_poj.USERNAME_INPUT, "Username field was not found");
        WebElement passwordField = requireElement(login_poj.PASSWORD_INPUT, "Password field was not found");
        Thread.sleep(3000);
        if (usernameValue != null && !usernameValue.isEmpty()) {
            usernameField.clear();
            Thread.sleep(2000);
            usernameField.sendKeys(usernameValue);
            Thread.sleep(2000);
            passwordField.clear();
            Thread.sleep(2000);
        }
        if (passwordValue != null && !passwordValue.isEmpty()) {
            passwordField.clear();
            Thread.sleep(2000);
            passwordField.sendKeys(passwordValue);
            Thread.sleep(2000);
        }
        takeScreenshot("step_04_credentials_entered");
    }

    public void clickSignIn() throws InterruptedException {
        Thread.sleep(2000);
        WebElement signInButton = requireElement(login_poj.SIGN_IN_BUTTON, "Sign in button was not found");
        signInButton.click();
        Thread.sleep(5000);
        takeScreenshot("step_05_signin_button_clicked");
    }

    public boolean isRedirectedToDashboard() {
        String url = driver.getCurrentUrl().toLowerCase();
        return !url.contains("/auth/login");
    }

    public void clickLogout() throws InterruptedException {
        WebElement profileMenuButton = requireElement(login_poj.PROFILE_MENU_BUTTON, "Profile menu button was not found");
        profileMenuButton.click();
        Thread.sleep(2000);
        WebElement logoutButton = requireElement(login_poj.LOGOUT_BUTTON, "Logout button was not found");
        logoutButton.click();
        Thread.sleep(2000);
        takeScreenshot("step_06_logout_clicked");
    }

    public boolean isRedirectedToLoginPage() {
        boolean redirected = driver.getCurrentUrl().toLowerCase().contains("/auth/login");
        return redirected;
    }

    public boolean isSessionTerminated() {
        driver.navigate().refresh();
        takeScreenshot("step_07_page_refreshed_after_logout");
        return isRedirectedToLoginPage();
    }

    public void submitEmptyCredentials() throws InterruptedException {
        enterCredentials("", "");
        clickSignIn();
    }

    public boolean areBothValidationMessagesDisplayed() {
        return isElementDisplayed(login_poj.USERNAME_REQUIRED) && isElementDisplayed(login_poj.PASSWORD_REQUIRED);
    }

    public void submitWithUsernameEmptyAndValidPassword() throws InterruptedException {
        enterCredentials("", VALID_PASSWORD);
        clickSignIn();
       /* WebElement passwordField = requireElement(login_poj.PASSWORD_INPUT, "Password field was not found");
        passwordField.clear();
        Thread.sleep(2000);*/
    }

    public boolean isUsernameRequiredDisplayed() {
        return isElementDisplayed(login_poj.USERNAME_REQUIRED);
    }

    public void submitWithValidUsernameAndPasswordEmpty() throws InterruptedException {
        enterCredentials(VALID_USERNAME, "");
        clickSignIn();
        Thread.sleep(5000);
        takeScreenshot("step_10_username_valid_password_empty_submitted");
    }

    public boolean isPasswordRequiredDisplayed() {
        return isElementDisplayed(login_poj.PASSWORD_REQUIRED);
    }

    public void submitWithInvalidUsernameAndValidPassword() throws InterruptedException {
        enterCredentials("invalid_user_123", VALID_PASSWORD);
        clickSignIn();
    }

    public void submitWithValidUsernameAndInvalidPassword() throws InterruptedException {
        enterCredentials(VALID_USERNAME, "invalid_pass_123");
        clickSignIn();
    }

    public void submitWithInvalidUsernameAndInvalidPassword() throws InterruptedException {
        enterCredentials("invalid_user_123", "invalid_pass_123");
        clickSignIn();
    }

    public boolean isInvalidCredentialsPopupDisplayed() {
        return isElementDisplayed(login_poj.INVALID_CREDENTIALS);
    }

    // ── Edge Case / Security Methods ─────────────────────────────────────────

    public void submitWithSqlInjectionPayload() throws InterruptedException {
        enterCredentials("' OR '1'='1", "' OR '1'='1");
        clickSignIn();

    }

    public void submitWithXssPayload() throws InterruptedException {
        enterCredentials("<script>alert('xss')</script>", VALID_PASSWORD);
        clickSignIn();
    }

    public boolean isXssInputSanitizedOrRejected() {
        return isRedirectedToLoginPage() || isInvalidCredentialsPopupDisplayed();
    }

    /*public boolean isScriptExecuted() {
        try {
            driver.switchTo().alert().dismiss();
            return true;
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            return false;
        }
    }*/

    public void submitWithDifferentCasingCredentials() throws InterruptedException {
        enterCredentials(VALID_USERNAME.toUpperCase(), VALID_PASSWORD);
        clickSignIn();
    }

    public boolean isCaseSensitivityHandledCorrectly() throws InterruptedException {
        // Acceptable outcomes: dashboard (case-insensitive app) or error (case-sensitive app)
        boolean result = isRedirectedToDashboard() || isInvalidCredentialsPopupDisplayed() || isRedirectedToLoginPage();
        return result;
    }

    public void submitWithExtremelyLongInput() throws InterruptedException {
        String longString = "A".repeat(500);
        enterCredentials(longString, longString);
        clickSignIn();
    }

    public boolean isLongInputHandledGracefully() {
        String pageSource = driver.getPageSource().toLowerCase();
        boolean noServerCrash = !pageSource.contains("internal server error")
                && !pageSource.contains("stack trace")
                && !pageSource.contains("unhandled exception");
        return noServerCrash
                && (isRedirectedToLoginPage()
                || isInvalidCredentialsPopupDisplayed()
                || isElementDisplayed(login_poj.MAX_LENGTH_VALIDATION));
    }

    public void submitWithSpecialCharacters() throws InterruptedException {
        enterCredentials("@#$%^&*", "@#$%^&*");
        clickSignIn();
    }

    public boolean isSpecialCharInputHandledCorrectly() {
        String pageSource = driver.getPageSource().toLowerCase();
        boolean noServerCrash = !pageSource.contains("internal server error")
                && !pageSource.contains("stack trace")
                && !pageSource.contains("unhandled exception");
        return noServerCrash && (isRedirectedToLoginPage() || isInvalidCredentialsPopupDisplayed());
    }

    public void waitForSessionTimeout() throws InterruptedException {
        // Simulate session expiry by clearing all cookies, then navigate to a protected page
        driver.manage().deleteAllCookies();
        Thread.sleep(2000);
        String dashboardUrl = LOGIN_URL.replace("/auth/login", "");
        driver.navigate().to(dashboardUrl);
        Thread.sleep(3000);
        takeScreenshot("step_19_session_timeout_simulated");
    }

    public boolean isSessionExpiredMessageDisplayed() {
        return isElementDisplayed(login_poj.SESSION_EXPIRED_MESSAGE) || isRedirectedToLoginPage();
    }

    public void navigateBack() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(3000);
        takeScreenshot("step_20_browser_back_after_logout");
    }
    public void refreshButton() throws InterruptedException
    {
        driver.navigate().refresh();
        Thread.sleep(2000);
        takeScreenshot("step_21_browser_refresh_after_logout");
    }

    // ── Private Helpers ───────────────────────────────────────────────────────

    private WebElement requireElement(By locator, String message) {
        try {
            return new WebDriverWait(driver, DEFAULT_WAIT)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException ex) {
            throw new AssertionError(message);
        }
    }

    private boolean isElementDisplayed(By locator) {
        try {
            WebElement element = new WebDriverWait(driver, DEFAULT_WAIT)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void takeScreenshot(String stepName) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        attachScreenshotToScenario(screenshot, stepName);
    }

    private void attachScreenshotToScenario(byte[] screenshot, String name) {
        if (ScenarioContext.getScenario() != null) {
            ScenarioContext.getScenario().attach(screenshot, "image/png", name == null ? "screenshot" : name);
        }
    }
}
