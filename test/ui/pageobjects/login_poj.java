package pageobjects;

import org.openqa.selenium.By;

public class login_poj {
    public static final By USERNAME_LABEL = By.xpath("//*[normalize-space()='Username']");
    public static final By PASSWORD_LABEL = By.xpath("//*[normalize-space()='Password']");
    public static final By SIGN_IN_BUTTON = By.xpath("//button[normalize-space()='Sign in']");
    public static final By USERNAME_INPUT = By.xpath("//input[@type='text' or contains(translate(@name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'user') or contains(translate(@id,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'user')]");
    public static final By PASSWORD_INPUT = By.xpath("//input[@placeholder = 'Enter your password']");
    public static final By PASSWORD_TOGGLE = By.xpath("(//button[contains(@class,'eye') or contains(@class,'toggle') or @type='button'] | //*[(contains(@class,'eye') or contains(@class,'toggle-password')) and (@role='button' or self::span or self::i)])[1]");
    public static final By PROFILE_MENU_BUTTON = By.xpath("//div[contains(@class,'flex items-end mr-[1rem] gap-4 md:gap-4')]/span");
    public static final By LOGOUT_BUTTON = By.xpath("//button[contains(normalize-space(),'Logout') or contains(normalize-space(),'Log out') or contains(normalize-space(),'Log Out')] | //a[contains(normalize-space(),'Logout') or contains(normalize-space(),'Log out') or contains(normalize-space(),'Log Out')]");
    public static final By USERNAME_REQUIRED = By.xpath("//p[@class='mt-1 text-sm text-red-500' and normalize-space()='Please input your username!']");
    public static final By PASSWORD_REQUIRED = By.xpath("//p[@class='mt-1 text-sm text-red-500' and normalize-space()='Please input password!']");
    public static final By INVALID_CREDENTIALS = By.xpath("//div[@class='p-3 text-sm text-red-500 bg-red-50 border border-red-200 rounded-lg' and normalize-space()='Login failed. Please try again.']");
    public static final By SESSION_EXPIRED_MESSAGE = By.xpath("//*[contains(normalize-space(),'Session expired') or contains(normalize-space(),'session expired') or contains(normalize-space(),'log in again') or contains(normalize-space(),'logged out')]");
    public static final By MAX_LENGTH_VALIDATION = By.xpath("//p[contains(@class,'text-red-500') or contains(@class,'error') or contains(@class,'invalid') or contains(@class,'validation')]");
}
