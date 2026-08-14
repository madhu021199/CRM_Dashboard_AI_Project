package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "test/ui/feature/login.feature",
        glue = {"steps"},
        plugin = {
                "pretty",
                "summary",
                "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json",
                "junit:target/cucumber-report/cucumber.xml",
        },
        monochrome = true
)
public class loginRunner {
}
