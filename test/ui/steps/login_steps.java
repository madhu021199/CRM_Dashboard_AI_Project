package steps;

import base.login_base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import page.login_imp;

public class login_steps {
    private login_base base;
    private login_imp loginPage;

    @Before
    public void beforeScenario(Scenario scenario) {
        ScenarioContext.setScenario(scenario);
        base = new login_base();
        base.initializeDriver();
        loginPage = new login_imp(base.getDriver());
    }

    @After
    public void afterScenario() {
        ScenarioContext.clear();
        base.quitDriver();
    }

    @Given("the user opens the browser and navigates to the CRM application URL")
    public void the_user_opens_the_browser_and_navigates_to_the_crm_application_url() {
        loginPage.openLoginPage();
    }

    @Then("the CRM login page should be displayed with Username, Password fields and a Sign In button")
    public void the_crm_login_page_should_be_displayed_with_username_password_fields_and_a_sign_in_button() {
        loginPage.validateLoginPageLabelsAndControls();
    }

    @When("^the user types a password into the Password field and clicks the \"eye\" \\(show/hide\\) icon$")
    public void the_user_types_a_password_into_the_password_field_and_clicks_the_eye_show_hide_icon() {
        loginPage.typePasswordAndToggleVisibility();
    }

    @Then("the password should toggle between masked and plain text view")
    public void the_password_should_toggle_between_masked_and_plain_text_view() {
        Assert.assertTrue("Password visibility did not toggle", loginPage.isPasswordVisibilityToggled());
    }

    @When("the user enters a valid username and a valid password")
    public void the_user_enters_a_valid_username_and_a_valid_password() throws InterruptedException {
        loginPage.enterValidUsernameAndPassword();
    }

    @And("clicks the \"Sign in\" button")
    public void clicks_the_sign_in_button() throws InterruptedException {
        loginPage.clickSignIn();
    }

    @Then("the user should be successfully redirected to the CRM dashboard page")
    public void the_user_should_be_successfully_redirected_to_the_crm_dashboard_page() {
        Assert.assertTrue("User was not redirected to dashboard", loginPage.isRedirectedToDashboard());
    }

    @When("the user clicks the \"Logout\" button on the dashboard")
    public void the_user_clicks_the_logout_button_on_the_dashboard() throws InterruptedException {
        loginPage.clickLogout();
    }

    @Then("the user should be redirected to the CRM login page")
    public void the_user_should_be_redirected_to_the_crm_login_page() {
        Assert.assertTrue("User was not redirected to login page", loginPage.isRedirectedToLoginPage());
    }

    @And("the session should be terminated")
    public void the_session_should_be_terminated() {
        Assert.assertTrue("Session was not terminated", loginPage.isSessionTerminated());
    }

    @Given("the user is on the CRM login page")
    public void the_user_is_on_the_crm_login_page() {
        loginPage.openLoginPage();
        Assert.assertTrue("Login page is not opened", loginPage.isRedirectedToLoginPage());
    }

    @When("the user leaves both the Username and Password fields empty and clicks \"Sign in\"")
    public void the_user_leaves_both_the_username_and_password_fields_empty_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitEmptyCredentials();
    }

    @Then("validation error messages should be displayed for both fields")
    public void validation_error_messages_should_be_displayed_for_both_fields() {
        Assert.assertTrue("Both validation messages are not displayed", loginPage.areBothValidationMessagesDisplayed());
    }

    @And("the user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {
        Assert.assertTrue("User did not remain on login page", loginPage.isRedirectedToLoginPage());
    }

    @When("the user leaves the Username field empty, enters a valid password, and clicks \"Sign in\"")
    public void the_user_leaves_the_username_field_empty_enters_a_valid_password_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithUsernameEmptyAndValidPassword();
    }

    @Then("a validation error message \"Username is required\" should be displayed")
    public void a_validation_error_message_username_is_required_should_be_displayed() {
        Assert.assertTrue("Username required message was not displayed", loginPage.isUsernameRequiredDisplayed());
    }

    @When("the user enters a valid username, leaves the Password field empty, and clicks \"Sign in\"")
    public void the_user_enters_a_valid_username_leaves_the_password_field_empty_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithValidUsernameAndPasswordEmpty();
    }

    @Then("a validation error message \"Password is required\" should be displayed")
    public void a_validation_error_message_password_is_required_should_be_displayed() {
        Assert.assertTrue("Password required message was not displayed", loginPage.isPasswordRequiredDisplayed());
    }

    @When("^the user enters an invalid/non-existent username with a valid password and clicks \"Sign in\"$")
    public void the_user_enters_an_invalid_non_existent_username_with_a_valid_password_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithInvalidUsernameAndValidPassword();
    }

    @When("the user enters a valid username with an incorrect password and clicks \"Sign in\"")
    public void the_user_enters_a_valid_username_with_an_incorrect_password_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithValidUsernameAndInvalidPassword();
    }

    @When("the user enters both an invalid username and an invalid password and clicks \"Sign in\"")
    public void the_user_enters_both_an_invalid_username_and_an_invalid_password_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithInvalidUsernameAndInvalidPassword();
    }

    @Then("an error pop-up with message \"Invalid username or password\" should be displayed")
    public void an_error_pop_up_with_message_invalid_username_or_password_should_be_displayed() {
        Assert.assertTrue("Invalid credentials message was not displayed", loginPage.isInvalidCredentialsPopupDisplayed());
    }

    @When("^the user enters a SQL injection payload \\(e\\.g\\. ' OR '1'='1\\) in the Username or Password field and clicks \"Sign in\"$")
    public void the_user_enters_a_sql_injection_payload_in_the_username_or_password_field_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithSqlInjectionPayload();
    }

    @Then("the system should reject the input and show an \"Invalid username or password\" error")
    public void the_system_should_reject_the_input_and_show_an_invalid_username_or_password_error() {
        Assert.assertTrue("SQL injection was not rejected", loginPage.isInvalidCredentialsPopupDisplayed());
    }

//    @And("no unauthorized access or database error should occur")
//    public void no_unauthorized_access_or_database_error_should_occur() {
//        Assert.assertTrue("Unauthorized access or DB error detected after SQL injection", loginPage.isRedirectedToLoginPage());
//    }

    @When("^the user enters a script tag \\(e\\.g\\. <script>alert\\('xss'\\)</script>\\) into the Username field and clicks \"Sign in\"$")
    public void the_user_enters_a_script_tag_into_the_username_field_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithXssPayload();
    }

    @Then("the input should be sanitized or rejected")
    public void the_input_should_be_sanitized_or_rejected() {
        Assert.assertTrue("XSS input was not sanitized or rejected", loginPage.isXssInputSanitizedOrRejected());
    }

    /*@And("no script execution should occur")
    public void no_script_execution_should_occur() {
        Assert.assertFalse("Script execution was detected", loginPage.isScriptExecuted());
    }*/

    @When("^the user enters the correct username/password with different letter casing \\(e\\.g\\. \"DEMO\" instead of \"demo\"\\) and clicks \"Sign in\"$")
    public void the_user_enters_credentials_with_different_casing_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithDifferentCasingCredentials();
    }

    @Then("^the system should behave per the defined case-sensitivity rule \\(login successfully if case-insensitive, or show an invalid credentials error if case-sensitive\\)$")
    public void the_system_should_behave_per_the_defined_case_sensitivity_rule() throws InterruptedException {
        Assert.assertTrue("System did not handle casing as per the defined rule", loginPage.isCaseSensitivityHandledCorrectly());
        loginPage.clickLogout();
        Thread.sleep(3000);
    }

    @When("^the user enters an extremely long string \\(500\\+ characters\\) into the Username or Password field and clicks \"Sign in\"$")
    public void the_user_enters_an_extremely_long_string_into_the_username_or_password_field_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithExtremelyLongInput();
    }

    @Then("the system should handle it gracefully — either truncate, show a max-length validation error, or reject without crashing")
    public void the_system_should_handle_long_input_gracefully() {
        Assert.assertTrue("System did not handle extremely long input gracefully", loginPage.isLongInputHandledGracefully());
    }

    @When("^the user enters special characters \\(e\\.g\\. @#\\$%\\^&\\*\\) in the Username/Password fields and clicks \"Sign in\"$")
    public void the_user_enters_special_characters_in_the_username_password_fields_and_clicks_sign_in() throws InterruptedException {
        loginPage.submitWithSpecialCharacters();
    }

    @Then("^the system should validate/handle the input without errors or unexpected behavior$")
    public void the_system_should_validate_handle_the_input_without_errors_or_unexpected_behavior() {
        Assert.assertTrue("System did not handle special characters properly", loginPage.isSpecialCharInputHandledCorrectly());
    }

    @Given("the user successfully logs into the CRM dashboard")
    public void the_user_successfully_logs_into_the_crm_dashboard() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.enterValidUsernameAndPassword();
        loginPage.clickSignIn();
        Assert.assertTrue("User was not redirected to dashboard", loginPage.isRedirectedToDashboard());
    }

    @When("the session remains idle beyond the configured timeout period and the user attempts to perform an action")
    public void the_session_remains_idle_beyond_the_configured_timeout_period_and_the_user_attempts_to_perform_an_action() throws InterruptedException {
        loginPage.waitForSessionTimeout();
    }

    @Then("the system should automatically log the user out")
    public void the_system_should_automatically_log_the_user_out() {
        Assert.assertTrue("User was not automatically logged out after session timeout", loginPage.isRedirectedToLoginPage());
    }

    @And("redirect the user to the login page with a \"Session expired, please log in again\" message")
    public void redirect_the_user_to_the_login_page_with_a_session_expired_message() {
        Assert.assertTrue("Session expired message was not displayed", loginPage.isSessionExpiredMessageDisplayed());
    }

    @When("the user logs out and then clicks the browser \"Back\" button")
    public void the_user_logs_out_and_then_clicks_the_browser_back_button() throws InterruptedException {
        loginPage.clickLogout();
        loginPage.navigateBack();
        loginPage.refreshButton();
    }
//
//    @Then("the user should not be able to access the dashboard")
//    public void the_user_should_not_be_able_to_access_the_dashboard() {
//        Assert.assertFalse("User was able to access the dashboard after logout", loginPage.isRedirectedToDashboard());
//    }

    @And("should be redirected to the login page")
    public void should_be_redirected_to_the_login_page() {
        Assert.assertTrue("User was not redirected to the login page", loginPage.isRedirectedToLoginPage());
    }


}
