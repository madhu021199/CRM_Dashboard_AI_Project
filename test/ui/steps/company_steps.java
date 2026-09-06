package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import page.company_imp;

public class company_steps {
    private company_imp companyPage;

    @Before(order = 1)
    public void setupCompanyPage() {
        companyPage = new company_imp(Hooks.getBase().getDriver());
    }

    @Given("the user is logged into the CRM application")
    public void the_user_is_logged_into_the_crm_application() throws InterruptedException {
        companyPage.loginToCrmApplication();
    }

    @When("the user clicks on the {string} option in the left navigation panel")
    public void the_user_clicks_on_the_option_in_the_left_navigation_panel(String optionName) {
        companyPage.clickCompanyOptionInLeftNavigation();
    }

    @Then("the user is navigated to the Company listing page and {string} is shown as active in the navigation")
    public void the_user_is_navigated_to_the_company_listing_page_and_is_shown_as_active_in_the_navigation(String optionName) {
        Assert.assertTrue("'Company' is not shown as active in the left navigation panel",
                companyPage.isCompanyListingPageOpenedWithActiveNavigation());
    }

    @When("the user clicks {string}")
    public void the_user_clicks_create_company(String buttonName) {
        Assert.assertEquals("Unexpected button label", "Create Company", buttonName);
        companyPage.clickCreateCompany();
    }

    @Then("the {string} pop-up opens showing the {string} step")
    public void the_pop_up_opens_showing_the_step(String popupTitle, String stepName) {
        Assert.assertEquals("Unexpected pop-up title", "Create Company", popupTitle);
        Assert.assertEquals("Unexpected step name", "Company Information", stepName);
        Assert.assertTrue("Create Company pop-up was not opened on the Company Information step",
                companyPage.isCreateCompanyPopupOpenedOnCompanyInformationStep());
    }

    @When("the user enters the unique Company Name {string}")
    public void the_user_enters_the_unique_company_name(String companyName) {
        companyPage.enterCompanyName(companyName);
    }

    @And("selects Industry as {string}")
    public void selects_industry_as(String industry) {
        companyPage.selectIndustry(industry);
    }

    @And("selects Company Type as {string}")
    public void selects_company_type_as(String companyType) {
        companyPage.selectCompanyType(companyType);
    }

    @And("enters a valid Website {string}")
    public void enters_a_valid_website(String website) {
        companyPage.enterWebsite(website);
    }

    @And("enters a valid Phone number {string}")
    public void enters_a_valid_phone_number(String phoneNumber) {
        companyPage.enterPhoneNumber(phoneNumber);
    }

    @And("clicks Next button")
    public void clicks_next_button() {
        companyPage.clickNext();
    }

    @Then("no validation errors are displayed and the user proceeds to the {string} step")
    public void no_validation_errors_are_displayed_and_the_user_proceeds_to_the_step(String stepName) {
        Assert.assertEquals("Unexpected next step", "Billing Information", stepName);
        Assert.assertTrue("User did not proceed to the Billing Information step",
                companyPage.isBillingInformationStepOpenedWithoutValidationErrors());
    }

    @And("clicks submit button to create the New Company")
    public void clicks_submit_button_to_create_the_new_company() {
        companyPage.submitNewCompany();
    }

    @Then("the company {string} is created successfully")
    public void the_company_is_created_successfully(String companyName) {
        Assert.assertTrue("Company was not created successfully",
                companyPage.isCompanyCreatedSuccessfully(companyName));
    }

    @And("user validate the message as {string}")
    public void user_validate_the_message_as(String message) {
        Assert.assertTrue("Success message did not match expected value",
                companyPage.isSuccessMessageDisplayed(message));
    }

    @And("{string} appears in the Company list on the Company page with the details entered")
    public void appears_in_the_company_list_on_the_company_page_with_the_details_entered(String companyName) {
        Assert.assertTrue("Company was not listed with the entered details",
                companyPage.isCompanyListedWithEnteredDetails(companyName));
    }

    @When("the user clicks {string} without entering a Company Name")
    public void the_user_clicks_without_entering_a_company_name(String buttonName) {
        Assert.assertEquals("Unexpected button action", "Next", buttonName);
        companyPage.clickNextWithoutEnteringCompanyName();
    }

    @Then("a mandatory field validation error is displayed for {string} and the form is not submitted")
    public void a_mandatory_field_validation_error_is_displayed_for_and_the_form_is_not_submitted(String fieldName) {
        Assert.assertEquals("Unexpected mandatory field", "Company Name", fieldName);
        Assert.assertTrue("Mandatory company name validation was not displayed",
                companyPage.isMandatoryCompanyNameValidationDisplayed());
    }

    @When("the user enters only blank spaces {string} into the Company Name field and clicks {string}")
    public void the_user_enters_only_blank_spaces_into_the_company_name_field_and_clicks(String blankValue, String buttonName) {
        Assert.assertEquals("Unexpected button action", "Next", buttonName);
        companyPage.enterCompanyNameAndClickNext(blankValue);
    }

    @Then("the input is treated as empty and the mandatory field validation error is displayed again")
    public void the_input_is_treated_as_empty_and_the_mandatory_field_validation_error_is_displayed_again() {
        Assert.assertTrue("Whitespace-only company name was not treated as empty",
                companyPage.isCompanyNameTreatedAsEmpty());
    }

    @When("the user enters {string}, a Company Name that already exists, and clicks {string}")
    public void the_user_enters_a_company_name_that_already_exists_and_clicks(String companyName, String buttonName) {
        Assert.assertEquals("Unexpected button action", "Next", buttonName);
        companyPage.enterDuplicateCompanyNameAndClickNext(companyName);
    }

    @Then("a duplicate name validation error is displayed and the form is not submitted")
    public void a_duplicate_name_validation_error_is_displayed_and_the_form_is_not_submitted() {
        Assert.assertTrue("Duplicate company name validation was not displayed",
                companyPage.isDuplicateNameValidationDisplayed());
    }

    @When("the user changes the Company Name to the unique value {string}")
    public void the_user_changes_the_company_name_to_the_unique_value(String companyName) {
        companyPage.enterCompanyName(companyName);
    }

    @And("leaves the {string} and {string} dropdowns unselected")
    public void leaves_the_and_dropdowns_unselected(String firstDropdown, String secondDropdown) {
        Assert.assertEquals("Unexpected first dropdown", "Industry", firstDropdown);
        Assert.assertEquals("Unexpected second dropdown", "Company Type", secondDropdown);
        companyPage.leaveMandatoryDropdownsUnselected();
    }

    @Then("validation errors are displayed for the unselected mandatory {string} and {string} fields")
    public void validation_errors_are_displayed_for_the_unselected_mandatory_and_fields(String firstField, String secondField) {
        Assert.assertEquals("Unexpected first mandatory field", "Industry", firstField);
        Assert.assertEquals("Unexpected second mandatory field", "Company Type", secondField);
        Assert.assertTrue("Mandatory dropdown validation was not displayed",
                companyPage.isMandatoryDropdownValidationDisplayed());
    }

    @When("the user selects valid values for {string} and {string}")
    public void the_user_selects_valid_values_for_and(String firstField, String secondField) {
        Assert.assertEquals("Unexpected first field", "Industry", firstField);
        Assert.assertEquals("Unexpected second field", "Company Type", secondField);
        companyPage.selectValidMandatoryDropdownValues();
    }

    @And("enters an invalid Website URL {string}")
    public void enters_an_invalid_website_url(String website) {
        companyPage.enterWebsite(website);
    }

    @And("enters an invalid Phone number {string}")
    public void enters_an_invalid_phone_number(String phoneNumber) {
        companyPage.enterPhoneNumber(phoneNumber);
    }

    @Then("invalid format validation errors are displayed for both the {string} and {string} fields")
    public void invalid_format_validation_errors_are_displayed_for_both_the_and_fields(String firstField, String secondField) {
        Assert.assertEquals("Unexpected first field", "Website", firstField);
        Assert.assertEquals("Unexpected second field", "Phone", secondField);
        Assert.assertTrue("Invalid format validation was not displayed for website and phone",
                companyPage.isInvalidFormatValidationDisplayedForWebsiteAndPhone());
    }

    @And("the user remains on the {string} step")
    public void the_user_remains_on_the_step(String stepName) {
        Assert.assertEquals("Unexpected current step", "Company Information", stepName);
        Assert.assertTrue("User was not kept on the Company Information step",
                companyPage.isOnCompanyInformationStep());
    }

    @And("no company record is created or added to the Company list at any point in this flow")
    public void no_company_record_is_created_or_added_to_the_company_list_at_any_point_in_this_flow() {
        Assert.assertTrue("A company record was created unexpectedly",
                companyPage.isNoCompanyRecordCreated());
    }
}
