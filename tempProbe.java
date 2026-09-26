import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class ProbeContact {
  public static void main(String[] args) {
    ChromeOptions opts = new ChromeOptions();
    opts.addArguments("--headless=new","--window-size=1400,1200");
    WebDriver d = new ChromeDriver(opts);
    try {
      d.get("https://crm.osllc.us/admin/auth/login");
      WebDriverWait wait = new WebDriverWait(d, java.time.Duration.ofSeconds(20));
      wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#username")));
      d.findElement(By.cssSelector("input#username")).sendKeys("demo");
      d.findElement(By.cssSelector("input#password")).sendKeys("5555");
      d.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();
      wait.until(ExpectedConditions.urlContains("/dashboard"));
      System.out.println("Dashboard URL=" + d.getCurrentUrl());
      List<WebElement> matches = d.findElements(By.xpath("//*[contains(@href,'contact') or contains(normalize-space(.),'CONTACT') or contains(normalize-space(.),'Contacts')]"));
      System.out.println("match count=" + matches.size());
      for (int i=0; i<matches.size() && i<20; i++) {
        WebElement el = matches.get(i);
        System.out.println((i+1)+") text=" + el.getText() + " href=" + el.getAttribute("href"));
      }
      try {
        WebElement nav = d.findElement(By.xpath("//a[contains(@href,'/admin/contact') or contains(normalize-space(.),'CONTACT') or contains(normalize-space(.),'Contacts')]"));
        nav.click();
        wait.until(ExpectedConditions.urlContains("/contact"));
        System.out.println("Contact URL=" + d.getCurrentUrl());
      } catch (Exception e) { System.out.println("Open contact failed: " + e); }
      try {
        d.findElement(By.xpath("//button[.//span[contains(normalize-space(),'Create Contact')]]")).click();
        System.out.println("Create button clicked");
      } catch (Exception e) { System.out.println("Create button failed: " + e); }
      String[] ids = {"#industryId", "#contactSourceId", "#contactStageId", "#firstName", "#email", "#lastName", "#jobTitle", "#phone", "#department", "#presentAddress"};
      for (String sel : ids) {
        try {
          WebElement e = d.findElement(By.cssSelector(sel));
          System.out.println("found " + sel + " tag=" + e.getTagName() + " value=" + e.getAttribute("value"));
        } catch (Exception ex) {
          System.out.println("missing " + sel + " reason=" + ex.getMessage());
        }
      }
    } finally {
      d.quit();
    }
  }
}
