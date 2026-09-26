package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.ScenarioContext;

import java.time.Duration;

public class contact_imp {
    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(10);

    private final WebDriver driver;
    private final login_imp loginPage;

    public contact_imp(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new login_imp(driver);
    }

    public void loginToCrmApplication() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.enterValidUsernameAndPassword();
        loginPage.clickSignIn();
        takeScreenshot("contact_step_01_logged_in");
    }

    public boolean isContactMenuDisplayed() {
        return isDisplayed(By.xpath("//a[contains(@href,'/admin/contact') or .//span[normalize-space()='CONTACT'] or .//span[normalize-space()='Contacts']]"));
    }

    public void clickContactMenuItem() {
        click(By.xpath("//a[contains(@href,'/admin/contact') or .//span[normalize-space()='CONTACT'] or .//span[normalize-space()='Contacts']]"));
        takeScreenshot("contact_step_02_contact_menu_clicked");
    }

    public boolean isContactsPageDisplayed() {
        boolean result = isDisplayed(By.xpath("//*[contains(normalize-space(),'Contacts')]"));
        takeScreenshot("contact_step_03_contacts_page_visible");
        return result;
    }

    public boolean isContactDetailsPageDisplayed() {
        boolean result = isDisplayed(By.xpath("//*[normalize-space()='Contact Details']"))
                || isDisplayed(By.xpath("//*[@role='tab' and normalize-space()='Contact Information']"))
                || driver.getCurrentUrl().contains("/admin/contact/");
        takeScreenshot("contact_step_contact_details_page_visible");
        return result;
    }

    public void clickCreateContact() {
        click(By.xpath("//button[.//span[normalize-space()='+ Create Contact' or normalize-space()='Create Contact']]"));
        takeScreenshot("contact_step_04_create_contact_clicked");
    }

    public boolean isCreateContactPanelOpen() {
        boolean result = isDisplayed(By.xpath("//*[normalize-space()='Create Contact']"))
                && isDisplayed(By.cssSelector("#firstName"));
        takeScreenshot("contact_step_05_contact_panel_open");
        return result;
    }

    public void enterFirstName(String value) { type(By.cssSelector("#firstName"), value); }
    public void enterLastName(String value) { type(By.cssSelector("#lastName"), value); }
    public void enterEmail(String value) { type(By.cssSelector("#email"), value); }
    public void enterJobTitle(String value) { type(By.cssSelector("#jobTitle"), value); }
    public void enterPhoneNumber(String value) { type(By.cssSelector("#phone"), value); }
    public void enterDepartment(String value) { type(By.cssSelector("#department"), value); }
    public void selectIndustry(String value) { selectDropdownByIdWithFallback("industryId", value); takeScreenshot("all fields are filled");}
    public void selectContactSource(String value) { selectDropdownByIdWithFallback("contactSourceId", value); }
    public void selectContactStage(String value) { selectDropdownByIdWithFallback("contactStageId", value); }

    public void clickNext() {
        click(By.xpath("//button[.//span[normalize-space()='Next']]"));
        takeScreenshot("contact_step_06_next_clicked");
    }

    public boolean isWizardOnAddressStep() {
        try {
            // Wait for the wizard to advance - give the UI time to render the next step
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Use a longer wait specifically for the address step transition
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@title='Present address']")));
            boolean result = element.isDisplayed();
            takeScreenshot("contact_step_07_on_address_step");
            return result;
        } catch (TimeoutException ex) {
            takeScreenshot("contact_step_07_on_address_step");
            return false;
        }
    }

    public void enterPresentAddress(String value) { type(By.cssSelector("#presentAddress"), value); }
    public void enterPresentCity(String value) { type(By.cssSelector("#presentCity"), value); }
    public void enterPresentZipCode(String value) { type(By.cssSelector("#presentZipCode"), value); }
    public void enterPresentState(String value) { type(By.cssSelector("#presentState"), value); }
    public void enterPresentCountry(String value) { type(By.cssSelector("#presentCountry"), value); }
    public void enterTwitter(String value) { type(By.cssSelector("#twitter"), value); }
    public void enterLinkedin(String value) { type(By.cssSelector("#linkedin"), value);
    takeScreenshot("all fields are updated with vallues");}


    public void clickSubmit() {
        click(By.xpath("//button[@type='submit']"));
        takeScreenshot("contact_step_08_submit_clicked");
    }

    public boolean isContactCreatedSuccessfully() {
        return isContactCreatedSuccessfully("Contact Created Successfully");
    }

    public boolean isContactCreatedSuccessfully(String expectedMessage) {
        String text = driver.getPageSource();
        boolean result = text.contains(expectedMessage)
                || isDisplayed(By.xpath("//*[contains(normalize-space(), '" + expectedMessage + "')]"));
        takeScreenshot("contact_step_09_contact_created");
        return result;
    }

    public boolean isContactNameRequiredErrorDisplayed() {
        return isDisplayed(By.xpath("//*[normalize-space()='First Name is Required.']"));
    }

    public boolean isEmailRequiredErrorDisplayed() {
        return isDisplayed(By.xpath("//*[normalize-space()='Please Enter The Email!']"));
    }

    public boolean isDuplicateEmailErrorDisplayed() {
        return isDisplayed(By.xpath("//*[normalize-space()='Email Is Available']"))
                || isDisplayed(By.xpath("//*[normalize-space()='Email Already Exists']"))
                || isDisplayed(By.xpath("//*[contains(normalize-space(), 'Email') and contains(normalize-space(), 'Exist')]"));
    }

    private void click(By locator) {
        WebElement element = requireElement(locator, "Element was not found");
        element.click();
    }

    private void type(By locator, String value) {
        if (value == null) {
            return;
        }
        WebElement element = requireElement(locator, "Field was not found");
        element.clear();
        element.sendKeys(value);
    }

    /* private void selectDropdownById_old(String fieldId, String optionText) {
        if (optionText == null || optionText.trim().isEmpty()) {
            return;
        }

        WebElement field;
        try {
            field = requireElement(By.cssSelector("#" + fieldId), "Dropdown not found");
        } catch (AssertionError ex) {
            // fallback: locate selector by label -> select element
            field = requireElement(By.xpath("//label[@for='" + fieldId + "']/ancestor::div[contains(@class,'ant-form-item')]//div[contains(@class,'ant-select-selector')]"), "Dropdown not found");
        }

        // open dropdown
        try {
            field.click();
        } catch (Exception ignored) { }

        // wait for dropdown and ensure any "No data" overlay is gone
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT);
        By dropdown = By.xpath("//div[contains(@class,'ant-select-dropdown') and not(contains(@class,'hidden'))]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(normalize-space(),'No data')]")));
        } catch (TimeoutException ignored) { }

        // try normal option click
        By optionLocator = By.xpath("//div[contains(@class,'ant-select-item') and contains(normalize-space(.), '" + optionText + "')] | //div[contains(@class,'ant-select-item-option-content') and normalize-space()='" + optionText + "']");
        try {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            option.click();
            return;
        } catch (TimeoutException ignored) { }

        // try typing into the search input if present
        try {
            WebElement input = driver.findElement(By.xpath("//input[contains(@class,'ant-select-search__field') or contains(@class,'ant-select-selection-search-input')]") );
            input.clear();
            input.sendKeys(optionText);
            input.sendKeys(org.openqa.selenium.Keys.ENTER);
            return;
        } catch (Exception ignored) { }

        // JS fallback: click by title or by option content text
        String js = "var val=arguments[0];"
                + "var found = document.querySelectorAll(\"div[title='\"+val+"\']\");"
                + "if(found.length){ found[0].scrollIntoView({block:'center'}); found[0].click(); return true; }"
                + "var elems = Array.from(document.querySelectorAll('div.ant-select-item-option-content'));"
                + "var e = elems.find(function(el){ return el.textContent.trim()===val; });"
                + "if(e){ e.scrollIntoView({block:'center'}); e.click(); return true; }"
                + "return false;";

        Boolean ok = false;
        try {
            ok = (Boolean)((JavascriptExecutor)driver).executeScript(js, optionText);
        } catch (Exception ignored) { }

        if (Boolean.TRUE.equals(ok)) {
            return;
        }

        // final fallback: set value and dispatch events (some components read input value)
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true })); arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    field,
                    optionText
            );
        } catch (Exception ex) {
            throw new AssertionError("Failed to select dropdown option: " + optionText);
        }
    } */

    private void selectDropdownByIdWithFallback(String fieldId, String optionText) {
        if (optionText == null || optionText.trim().isEmpty()) {
            return;
        }

        WebElement field = null;
        // Try several strategies to find the clickable selector element (some ant-selects render differently)
        By[] selectorLocators = new By[]{
                By.cssSelector("#" + fieldId + " .ant-select-selector"),
                By.xpath("//*[@id='" + fieldId + "']"),
                By.xpath("//label[@for='" + fieldId + "']/ancestor::div[contains(@class,'ant-form-item')]//div[contains(@class,'ant-select-selector')]") ,
                By.xpath("//*[@id='" + fieldId + "']//div[contains(@class,'ant-select-selector')]")
        };

        for (By loc : selectorLocators) {
            try {
                field = requireElement(loc, "Dropdown selector not found");
                if (field != null) break;
            } catch (AssertionError ignored) { }
        }

        if (field == null) {
            throw new AssertionError("Dropdown not found: " + fieldId);
        }

        try { field.click(); } catch (Exception e) {
            try { ((JavascriptExecutor)driver).executeScript("arguments[0].click();", field); } catch (Exception ignored) { }
        }

        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT);
        By dropdown = By.xpath("//div[contains(@class,'ant-select-dropdown') and not(contains(@class,'hidden'))] | //*[@id='" + fieldId + "_list']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(normalize-space(),'No data')]")));
        } catch (TimeoutException ignored) { }

        // Prefer options inside the virtualized list container (id like industryId_list)
        By optionLocator = By.xpath("//div[@id='" + fieldId + "_list']//div[contains(@class,'ant-select-item-option-content') and normalize-space()='" + optionText + "'] | //div[contains(@class,'ant-select-item') and contains(normalize-space(.), '" + optionText + "')] | //div[contains(@class,'ant-select-item-option-content') and normalize-space()='" + optionText + "']");
        try {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            try { option.click(); return; } catch (Exception e) { ((JavascriptExecutor)driver).executeScript("arguments[0].click();", option); return; }
        } catch (TimeoutException ignored) { }

        // Try typing into the search input if present
        try {
            WebElement input = driver.findElement(By.xpath("//input[contains(@class,'ant-select-search__field') or contains(@class,'ant-select-selection-search-input')]") );
            input.clear();
            input.sendKeys(optionText);
            input.sendKeys(org.openqa.selenium.Keys.ENTER);
            return;
        } catch (Exception ignored) { }

        // JS fallback: search within the list container first, then globally by option content or title
        String js = "" +
                "var val=arguments[0]; var lid=arguments[1];" +
                "var list = document.getElementById(lid + '_list');" +
                "if(list){ var elems = Array.from(list.querySelectorAll('div.ant-select-item-option-content')); var e = elems.find(function(el){ return el.textContent.trim()===val; }); if(e){ e.scrollIntoView({block:'center'}); e.click(); return true; } }" +
                "var elems2 = Array.from(document.querySelectorAll('div.ant-select-item-option-content')); var e2 = elems2.find(function(el){ return el.textContent.trim()===val; }); if(e2){ e2.scrollIntoView({block:'center'}); e2.click(); return true; }" +
                "var found = document.querySelectorAll(\"div[title='\" + val + \"']\"); if(found.length){ found[0].scrollIntoView({block:'center'}); found[0].click(); return true; }" +
                "return false;";
        Boolean ok = false;
        try {
            ok = (Boolean)((JavascriptExecutor)driver).executeScript(js, optionText, fieldId);
        } catch (Exception ignored) { }

        if (Boolean.TRUE.equals(ok)) {
            return;
        }

        // Last resort: set the value on the field and dispatch events
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true })); arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    field,
                    optionText
            );
        } catch (Exception ex) {
            throw new AssertionError("Failed to select dropdown option: " + optionText);
        }
    }

    private void selectDropdown(By locator, String optionText) {
        click(locator);
        WebElement option = new WebDriverWait(driver, DEFAULT_WAIT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'ant-select-dropdown')]//*[contains(normalize-space(), '" + optionText + "')]")));
        option.click();
    }

    private WebElement requireElement(By locator, String message) {
        try {
            return new WebDriverWait(driver, DEFAULT_WAIT)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException ex) {
            throw new AssertionError(message);
        }
    }

    private boolean isDisplayed(By locator) {
        try {
            WebElement element = new WebDriverWait(driver, DEFAULT_WAIT)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException ex) {
            return false;
        }
    }

    public void takeScreenshot(String stepName) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        if (ScenarioContext.getScenario() != null) {
            ScenarioContext.getScenario().attach(screenshot, "image/png", stepName == null ? "screenshot" : stepName);
        }
    }
}
