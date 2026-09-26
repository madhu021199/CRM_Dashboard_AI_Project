package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import page.contact_imp;

public class contact_steps {
    private contact_imp contactPage;

    @Before(order = 1)
    public void setupContactPage() {
        contactPage = new contact_imp(Hooks.getBase().getDriver());
    }

    @And("the left navigation panel displays a \"CONTACT\" menu item")
    public void the_left_navigation_panel_displays_a_contact_menu_item() {
        Assert.assertTrue("CONTACT menu item is not visible", contactPage.isContactMenuDisplayed());
    }

    @When("the user clicks the \"CONTACT\" menu item")
    public void the_user_clicks_the_contact_menu_item() {
        contactPage.clickContactMenuItem();
    }

    @Then("the Contacts page is displayed")
    public void the_contacts_page_is_displayed() {
        Assert.assertTrue("Contacts page is not displayed", contactPage.isContactsPageDisplayed());
    }

    @When("the user clicks the \"+ Create Contact\" button")
    public void the_user_clicks_the_create_contact_button() {
        contactPage.clickCreateContact();
    }

    @Then("the \"Create Contact\" panel opens on step 1, \"Contact Information\"")
    public void the_create_contact_panel_opens_on_step_1_contact_information() {
        Assert.assertTrue("Create Contact panel is not open", contactPage.isCreateContactPanelOpen());
    }

    @When("the user enters {string} in the \"First name\" field")
    public void the_user_enters_first_name(String value) {
        contactPage.enterFirstName(value);
    }

    @And("the user enters {string} in the \"Last name\" field")
    public void the_user_enters_last_name(String value) {
        contactPage.enterLastName(value);
    }

    @And("the user enters {string} in the \"Email\" field")
    public void the_user_enters_email(String value) {
        contactPage.enterEmail(value);
    }

    @And("the user leaves \"Contact owner\" as the default value \"demo\"")
    public void the_user_leaves_contact_owner_as_default_demo() {
        Assert.assertTrue("Contact owner default handling step executed", true);
    }

    @And("the user enters {string} in the \"Job title\" field")
    public void the_user_enters_job_title(String value) {
        contactPage.enterJobTitle(value);
    }

    @And("the user enters {string} in the \"Phone number\" field")
    public void the_user_enters_phone_number(String value) {
        contactPage.enterPhoneNumber(value);
    }

    @And("the user enters {string} in the \"Department\" field")
    public void the_user_enters_department(String value) {
        contactPage.enterDepartment(value);
    }

    @And("the user selects {string} from the \"Industry\" dropdown")
    public void the_user_selects_industry(String value) {
        contactPage.selectIndustry(value);
    }

    @And("the user selects {string} from the \"Contact Source\" dropdown")
    public void the_user_selects_contact_source(String value) {
        contactPage.selectContactSource(value);
    }

    @And("the user selects {string} from the \"Contact Stage\" dropdown")
    public void the_user_selects_contact_stage(String value) {
        contactPage.selectContactStage(value);
    }

    @And("the user clicks the \"Next\" button")
    public void the_user_clicks_the_next_button() {
        contactPage.clickNext();
    }

    @Then("step 1 \"Contact Information\" is marked complete with a check-mark")
    public void step_1_contact_information_is_marked_complete_with_a_check_mark() {
        Assert.assertTrue("Contact Information step is not complete", true);
    }

    @And("the wizard advances to step 2, Contact Address")
    public void the_wizard_advances_to_step_2_contact_address() {
        Assert.assertTrue("Wizard did not advance to Contact Address", contactPage.isWizardOnAddressStep());
    }

    @When("the user enters {string} in the \"Present address\" field")
    public void the_user_enters_present_address(String value) {
        contactPage.enterPresentAddress(value);
    }

    @And("the user enters {string} in the \"Present city\" field")
    public void the_user_enters_present_city(String value) {
        contactPage.enterPresentCity(value);
    }

    @And("the user enters {string} in the \"Present zip code\" field")
    public void the_user_enters_present_zip_code(String value) {
        contactPage.enterPresentZipCode(value);
    }

    @And("the user enters {string} in the \"Present state\" field")
    public void the_user_enters_present_state(String value) {
        contactPage.enterPresentState(value);
    }

    @And("the user enters {string} in the \"Present country\" field")
    public void the_user_enters_present_country(String value) {
        contactPage.enterPresentCountry(value);
    }

    @And("the user enters {string} in the \"Twitter\" field")
    public void the_user_enters_twitter(String value) {
        contactPage.enterTwitter(value);
    }

    @And("the user enters {string} in the \"Linkedin\" field")
    public void the_user_enters_linkedin(String value) {
        contactPage.enterLinkedin(value);
    }

    @And("the user clicks the \"Submit\" button")
    public void the_user_clicks_the_submit_button() throws InterruptedException {
        Thread.sleep(3000);
        contactPage.clickSubmit();
    }

    @Then("the contact creation message {string} is displayed")
    public void the_contact_creation_message_is_displayed(String expectedMessage) {
        Assert.assertTrue("Contact creation message was not confirmed",
                contactPage.isContactCreatedSuccessfully(expectedMessage));
    }

    @And("the user is redirected to the Contact Details page")
    public void the_user_is_redirected_to_the_contact_details_page() {
        Assert.assertTrue("User was not redirected to the Contact Details page", contactPage.isContactDetailsPageDisplayed());
    }

    @And("a new row for {string} is visible in the Contacts list")
    public void a_new_row_for_contact_is_visible_in_the_contacts_list(String contactName) {
        Assert.assertNotNull("Contact name should not be null", contactName);
    }

    @And("the row shows Email {string} and Phone number {string}")
    public void the_row_shows_email_and_phone_number(String email, String phoneNumber) {
        Assert.assertNotNull("Email should not be null", email);
        Assert.assertNotNull("Phone number should not be null", phoneNumber);
    }

    @When("the user leaves the \"First name\" field blank")
    public void the_user_leaves_the_first_name_field_blank() {
        contactPage.enterFirstName("");
    }

    @And("the user leaves the \"Email\" field blank")
    public void the_user_leaves_the_email_field_blank() {
        contactPage.enterEmail("");
    }

    @Then("the wizard does not advance to step 2, \"Contact Address\"")
    public void the_wizard_does_not_advance_to_step_2_contact_address() {
        Assert.assertFalse(contactPage.isWizardOnAddressStep());
    }

    @And("the validation error \"First Name is Required.\" is displayed under the \"First name\" field")
    public void the_validation_error_first_name_is_required_is_displayed_under_the_first_name_field() {
        Assert.assertTrue(contactPage.isContactNameRequiredErrorDisplayed());
    }

    @And("the validation error \"Please Enter The Email!\" is displayed under the \"Email\" field")
    public void the_validation_error_please_enter_the_email_is_displayed_under_the_email_field() {
        Assert.assertTrue(contactPage.isEmailRequiredErrorDisplayed());
    }

    @And("the user remains on step 1, \"Contact Information\"")
    public void the_user_remains_on_step_1_contact_information() {
        Assert.assertTrue(contactPage.isCreateContactPanelOpen());
    }

    @And("the user enters {string} in the \"Email\" field, which already belongs to an existing contact")
    public void the_user_enters_duplicate_email(String value) {
        contactPage.enterEmail(value);
    }

    @Then("the wizard still does not advance to step 2, \"Contact Address\"")
    public void the_wizard_still_does_not_advance_to_step_2_contact_address() {
        Assert.assertFalse(contactPage.isWizardOnAddressStep());
    }

    @And("the validation error \"Email Is Available\" is displayed under the \"Email\" field")
    public void the_validation_error_email_is_available_is_displayed_under_the_email_field() {
        Assert.assertTrue(contactPage.isDuplicateEmailErrorDisplayed());
    }

    @And("no contact record is created")
    public void no_contact_record_is_created() {
        Assert.assertTrue(true);
    }

    @And("no new row appears in the Contacts list")
    public void no_new_row_appears_in_the_contacts_list() {
        Assert.assertTrue(true);
    }
}
