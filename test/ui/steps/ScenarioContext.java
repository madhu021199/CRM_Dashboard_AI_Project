package steps;

import io.cucumber.java.Scenario;

public final class ScenarioContext {
    private static final ThreadLocal<Scenario> CURRENT_SCENARIO = new ThreadLocal<>();

    private ScenarioContext() {
    }

    public static void setScenario(Scenario scenario) {
        CURRENT_SCENARIO.set(scenario);
    }

    public static Scenario getScenario() {
        return CURRENT_SCENARIO.get();
    }

    public static void clear() {
        CURRENT_SCENARIO.remove();
    }
}
