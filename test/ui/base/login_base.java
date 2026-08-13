package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class login_base {
    private WebDriver driver;

    public void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Driver initialized and window maximized.");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
