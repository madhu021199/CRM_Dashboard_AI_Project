package steps;

import base.login_base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

// Starts and stops ONE browser per scenario, shared by all step classes
public class Hooks {
    private static login_base base;

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        ScenarioContext.setScenario(scenario);
        base = new login_base();
        base.initializeDriver();
    }

    @After(order = 0)
    public void afterScenario() {
        ScenarioContext.clear();
        base.quitDriver();
    }

    public static login_base getBase() {
        return base;
    }
}
