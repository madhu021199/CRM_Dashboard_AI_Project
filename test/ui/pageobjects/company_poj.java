package pageobjects;

import org.openqa.selenium.By;

public class company_poj {
    public static final By COMPANY_NAV_ENTRY = By.cssSelector("a[href='/admin/company']");
    public static final By COMPANY_NAV_ACTIVE = By.cssSelector("a.active[href='/admin/company'][aria-current='page']");
    public static final By CREATE_COMPANY_BUTTON = By.xpath("//button[.//span[normalize-space()='Create Company']]");
    public static final By COMPANY_DRAWER_TITLE = By.xpath("//div[@role='dialog']//div[contains(@class,'ant-drawer-title') and normalize-space()='Create Company']");
    public static final By COMPANY_INFORMATION_STEP = By.xpath("//div[contains(@class,'ant-steps-item-process')]//div[contains(@class,'ant-steps-item-title') and normalize-space()='Company Information']");
    public static final By COMPANY_NAME_INPUT = By.cssSelector("#companyName");
    public static final By INDUSTRY_DROPDOWN = By.xpath("//label[@for='industryId']/ancestor::div[contains(@class,'ant-form-item')]//div[contains(@class,'ant-select-selector')]");
    public static final By INDUSTRY_SEARCH_INPUT = By.cssSelector("#industryId");
    public static final By COMPANY_TYPE_DROPDOWN = By.xpath("//label[@for='companyTypeId']/ancestor::div[contains(@class,'ant-form-item')]//div[contains(@class,'ant-select-selector')]");
    public static final By COMPANY_TYPE_SEARCH_INPUT = By.cssSelector("#companyTypeId");
    public static final By WEBSITE_INPUT = By.cssSelector("#website");
    public static final By PHONE_INPUT = By.cssSelector("#phone");
    public static final By NEXT_BUTTON = By.xpath("//button[contains(@class,'ant-btn-primary')]//span[normalize-space()='Next']/parent::button");
    public static final By SUBMIT_BUTTON = By.xpath("//div[@role='dialog']//button[.//span[normalize-space()='Submit']]");
    public static final By DRAWER_CLOSE_BUTTON = By.cssSelector(".ant-drawer-close");
    public static final By BILLING_INFORMATION_STEP = By.xpath("//div[contains(@class,'ant-steps-item-process')]//div[contains(@class,'ant-steps-item-title') and normalize-space()='Billing Information']");
    public static final By SUCCESS_MESSAGE = By.xpath("//*[@id=\"root\"]/div/div/div/div[2]");
    public static final By COMPANY_NAME_VALIDATION_ERROR = By.xpath("//*[@id=\"companyName_help\"]/div");
    public static final By GENERIC_VALIDATION_ERROR = By.cssSelector(".ant-form-item-explain-error");
    public static final By INPUT_STATUS_ERROR = By.cssSelector(".ant-input-status-error");
    public static final By SELECT_STATUS_ERROR = By.cssSelector(".ant-select-status-error .ant-select-selector");
}
