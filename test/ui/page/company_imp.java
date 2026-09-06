package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.company_poj;
import steps.ScenarioContext;

import java.time.Duration;

public class company_imp {
    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(10);

    private final WebDriver driver;
    private final login_imp loginPage;
    private String capturedSuccessMessage;

    public company_imp(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new login_imp(driver);
    }

    // Log in first using the existing login page methods
    public void loginToCrmApplication() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.enterValidUsernameAndPassword();
        loginPage.clickSignIn();
        takeScreenshot("company_step_01_logged_in");
    }

    // Click "Company" in the left navigation menu
    public void clickCompanyOptionInLeftNavigation() {
        click(company_poj.COMPANY_NAV_ENTRY);
        takeScreenshot("company_step_02_left_nav_company_clicked");
    }

    // Check the Company page opened and "Company" is shown as active
    public boolean isCompanyListingPageOpenedWithActiveNavigation() {
        boolean result = isDisplayed(company_poj.COMPANY_NAV_ACTIVE);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takeScreenshot("company_step_03_company_listing_page_active");
        return result;
    }

    // Click the "Create Company" button
    public void clickCreateCompany() {
        click(company_poj.CREATE_COMPANY_BUTTON);
    }

    // Check the Create Company pop-up opened on the "Company Information" step
    public boolean isCreateCompanyPopupOpenedOnCompanyInformationStep() {
        boolean result = isDisplayed(company_poj.COMPANY_DRAWER_TITLE)
                && isDisplayed(company_poj.COMPANY_INFORMATION_STEP);
        takeScreenshot("company_step_05_create_company_popup_opened");
        return result;
    }

    // Type the Company Name
    public void enterCompanyName(String name) {
        type(company_poj.COMPANY_NAME_INPUT, name);
        takeScreenshot("company_step_06_company_name_entered");
    }

    // Pick a value from the Industry dropdown
    public void selectIndustry(String industryValue) {
        selectDropdownOption(company_poj.INDUSTRY_DROPDOWN, company_poj.INDUSTRY_SEARCH_INPUT, industryValue);
        takeScreenshot("company_step_07_industry_selected");
    }

    // Pick a value from the Company Type dropdown
    public void selectCompanyType(String companyTypeValue) {
        selectDropdownOption(company_poj.COMPANY_TYPE_DROPDOWN, company_poj.COMPANY_TYPE_SEARCH_INPUT, companyTypeValue);
        takeScreenshot("company_step_08_company_type_selected");
    }

    // Type the Website URL
    public void enterWebsite(String websiteValue) {
        type(company_poj.WEBSITE_INPUT, websiteValue);
        takeScreenshot("company_step_09_website_entered");
    }

    // Type the Phone number
    public void enterPhoneNumber(String phoneValue) {
        type(company_poj.PHONE_INPUT, phoneValue);
        takeScreenshot("company_step_10_phone_entered");
    }

    // Click the "Next" button
    public void clickNext() {
        click(company_poj.NEXT_BUTTON);
        takeScreenshot("company_step_11_next_clicked");
    }

    // Click "Next" without typing a Company Name first (negative scenario)
    public void clickNextWithoutEnteringCompanyName() {
        clickNext();
    }

    // Check no validation errors are shown and the Billing Information step opened
    public boolean isBillingInformationStepOpenedWithoutValidationErrors() {
        boolean result = !isDisplayed(company_poj.GENERIC_VALIDATION_ERROR)
                && isDisplayed(company_poj.BILLING_INFORMATION_STEP);
        takeScreenshot("company_step_12_billing_information_step_opened");
        return result;
    }

    // Click the submit button, then immediately capture the success message
    // before it disappears (the toast is only shown for a very short time)
    public void submitNewCompany() {
        click(company_poj.SUBMIT_BUTTON);
        capturedSuccessMessage = captureTextQuickly(company_poj.SUCCESS_MESSAGE);
        takeScreenshot("company_step_13_submit_clicked");
    }

    // Check the "company created" success message appeared
    public boolean isCompanyCreatedSuccessfully(String expectedCompanyName) {
        boolean result = capturedSuccessMessage != null && !capturedSuccessMessage.isEmpty();
        takeScreenshot("company_step_14_company_created_successfully_check");
        return result;
    }

    // Check the captured success message text matches what is expected
    public boolean isSuccessMessageDisplayed(String expectedMessage) {
        boolean result = capturedSuccessMessage != null && capturedSuccessMessage.equalsIgnoreCase(expectedMessage);
        takeScreenshot("company_step_15_success_message_validated");
        return result;
    }

    // Check the new company appears in the Company list table.
    // After creation the app navigates to the new company's detail page,
    // so we need to go back to the Company list page first.
    public boolean isCompanyListedWithEnteredDetails(String expectedCompanyName) {
        clickCompanyOptionInLeftNavigation();
        By companyLink = By.xpath("//table//a[normalize-space()='" + expectedCompanyName + "']");
        boolean result;
        try {
            result = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(companyLink))
                    .isDisplayed();
        } catch (TimeoutException ex) {
            result = false;
        }
        takeScreenshot("company_step_16_company_listed_check");
        return result;
    }

    // Check the mandatory Company Name validation error is displayed
    public boolean isMandatoryCompanyNameValidationDisplayed() {
        boolean result = isDisplayed(company_poj.COMPANY_NAME_VALIDATION_ERROR);
        takeScreenshot("company_step_17_mandatory_name_validation_check");
        return result;
    }

    // Enter a Company Name and click "Next" in one step (negative scenario)
    public void enterCompanyNameAndClickNext(String name) {
        enterCompanyName(name);
        clickNext();
    }

    // Check a blank/whitespace Company Name is treated as empty
    public boolean isCompanyNameTreatedAsEmpty() {
        return isMandatoryCompanyNameValidationDisplayed();
    }

    // Enter a duplicate Company Name and click "Next" in one step
    public void enterDuplicateCompanyNameAndClickNext(String name) {
        enterCompanyName(name);
        clickNext();
    }

    // Check the duplicate Company Name validation error is displayed
    public boolean isDuplicateNameValidationDisplayed() {
        boolean result = isDisplayed(company_poj.COMPANY_NAME_VALIDATION_ERROR);
        takeScreenshot("company_step_18_duplicate_name_validation_check");
        return result;
    }

    // Leave the Industry and Company Type dropdowns unselected (do nothing)
    public void leaveMandatoryDropdownsUnselected() {
        // Intentionally empty - the dropdowns are simply left untouched
        takeScreenshot("company_step_19_dropdowns_left_unselected");
    }

    // Check the mandatory dropdown validation error is displayed
    public boolean isMandatoryDropdownValidationDisplayed() {
        boolean result = isDisplayed(company_poj.SELECT_STATUS_ERROR);
        takeScreenshot("company_step_20_mandatory_dropdown_validation_check");
        return result;
    }

    // Select valid values for Industry and Company Type
    public void selectValidMandatoryDropdownValues() {
        selectIndustry("Information Technology and Services");
        selectCompanyType("Private");
    }

    // Check invalid Website/Phone format validation errors are displayed
    public boolean isInvalidFormatValidationDisplayedForWebsiteAndPhone() {
        boolean result = isDisplayed(company_poj.INPUT_STATUS_ERROR);
        takeScreenshot("company_step_21_invalid_format_validation_check");
        return result;
    }

    // Check the user is still on the Company Information step
    public boolean isOnCompanyInformationStep() {
        boolean result = isDisplayed(company_poj.COMPANY_INFORMATION_STEP);
        takeScreenshot("company_step_22_on_company_information_step_check");
        return result;
    }

    // Check no company was created (the Create Company pop-up is still open)
    public boolean isNoCompanyRecordCreated() {
        boolean result = isDisplayed(company_poj.COMPANY_DRAWER_TITLE);
        takeScreenshot("company_step_23_no_company_record_created_check");
        return result;
    }

    // ---------- Simple, reusable Selenium helper methods ----------

    private void click(By locator) {
        requireElement(locator, "Element not found: " + locator).click();
    }

    private void type(By locator, String text) {
        WebElement field = requireElement(locator, "Element not found: " + locator);
        field.clear();
        field.sendKeys(text);
    }

    private boolean isDisplayed(By locator) {
        try {
            WebElement element = new WebDriverWait(driver, DEFAULT_WAIT)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }
    }

    // Opens an Ant Design dropdown, types the value to filter, then clicks the matching option
    private void selectDropdownOption(By dropdownLocator, By searchInputLocator, String optionText) {
        click(dropdownLocator);
        type(searchInputLocator, optionText);
        By option = By.xpath("//div[contains(@class,'ant-select-item-option-content') and normalize-space()='" + optionText + "']");
        click(option);
    }

    private WebElement requireElement(By locator, String message) {
        try {
            return new WebDriverWait(driver, DEFAULT_WAIT)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException ex) {
            throw new AssertionError(message);
        }
    }

    // Quickly polls for a short-lived element (e.g. a toast message) and
    // returns its text, or null if it never appeared in time
    private String captureTextQuickly(By locator) {
        Wait<WebDriver> fastWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        try {
            return fastWait.until(webDriver -> {
                String text = webDriver.findElement(locator).getText();
                return (text != null && !text.trim().isEmpty()) ? text.trim() : null;
            });
        } catch (TimeoutException ex) {
            return null;
        }
    }

    // Takes a screenshot and attaches it to the currently running Cucumber scenario report
    private void takeScreenshot(String stepName) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        if (ScenarioContext.getScenario() != null) {
            ScenarioContext.getScenario().attach(screenshot, "image/png", stepName == null ? "screenshot" : stepName);
        }
    }
}
