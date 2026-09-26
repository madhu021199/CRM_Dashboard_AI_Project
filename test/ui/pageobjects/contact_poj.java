package pageobjects;

import org.openqa.selenium.By;

public class contact_poj {
    public static final By CONTACT_NAV_ENTRY = By.xpath("//a[contains(@href,'/admin/contact') or contains(normalize-space(.), 'Contact') or contains(normalize-space(.), 'CONTACT')]");
    public static final By CONTACT_PAGE_TITLE = By.xpath("//*[contains(normalize-space(),'Contacts')] | //h1[contains(normalize-space(),'Contacts')]");
    public static final By CREATE_CONTACT_BUTTON = By.xpath("//button[.//span[contains(normalize-space(),'Create Contact')]] | //button[normalize-space()='Create Contact']");
    public static final By CREATE_CONTACT_PANEL_TITLE = By.xpath("//*[normalize-space()='Create Contact']");
    public static final By CONTACT_INFORMATION_STEP = By.xpath("//*[normalize-space()='Contact Information']");
    public static final By FIRST_NAME_INPUT = By.cssSelector("#firstName");
    public static final By LAST_NAME_INPUT = By.cssSelector("#lastName");
    public static final By EMAIL_INPUT = By.cssSelector("#email");
    public static final By CONTACT_OWNER_INPUT = By.cssSelector("#contactOwnerId");
    public static final By JOB_TITLE_INPUT = By.cssSelector("#jobTitle");
    public static final By PHONE_NUMBER_INPUT = By.cssSelector("#phone");
    public static final By DEPARTMENT_INPUT = By.cssSelector("#department");
    public static final By INDUSTRY_DROPDOWN = By.cssSelector("#industryId");
    public static final By CONTACT_SOURCE_DROPDOWN = By.cssSelector("#contactSourceId");
    public static final By CONTACT_STAGE_DROPDOWN = By.cssSelector("#contactStageId");
    public static final By NEXT_BUTTON = By.xpath("//button[.//span[normalize-space()='Next'] or normalize-space()='Next']");
    public static final By CONTACT_ADDRESS_STEP = By.xpath("//*[normalize-space()='Contact Address']");
    public static final By PRESENT_ADDRESS_INPUT = By.cssSelector("#presentAddress");
    public static final By PRESENT_CITY_INPUT = By.cssSelector("#presentCity");
    public static final By PRESENT_ZIPCODE_INPUT = By.cssSelector("#presentZipCode");
    public static final By PRESENT_STATE_INPUT = By.cssSelector("#presentState");
    public static final By PRESENT_COUNTRY_INPUT = By.cssSelector("#presentCountry");
    public static final By TWITTER_INPUT = By.cssSelector("#twitter");
    public static final By LINKEDIN_INPUT = By.cssSelector("#linkedin");
    public static final By SUBMIT_BUTTON = By.xpath("//button[.//span[normalize-space()='Submit'] or normalize-space()='Submit']");
    public static final By SUCCESS_MESSAGE = By.xpath("//*[contains(normalize-space(),'created') or contains(normalize-space(),'success')] | //div[contains(@class,'ant-message')]");
    public static final By FIRST_NAME_REQUIRED_ERROR = By.xpath("//*[normalize-space()='First Name is Required.']");
    public static final By EMAIL_REQUIRED_ERROR = By.xpath("//*[normalize-space()='Please Enter The Email!']");
    public static final By DUPLICATE_EMAIL_ERROR = By.xpath("//*[normalize-space()='Email Already Exists']");
}
