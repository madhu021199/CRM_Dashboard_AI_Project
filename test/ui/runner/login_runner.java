package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "test/ui/feature/login.feature",
    glue = "steps",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true,
    dryRun = false
)
public class login_runner {
}