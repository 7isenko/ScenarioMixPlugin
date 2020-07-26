package io.github._7isenko.scenariomix;

import io.github._7isenko.scenariomix.scenarios.Scenario;

import java.util.HashMap;
import java.util.Map;

public class ScenarioManager {
    private static ScenarioManager instance;
    private Map<Integer, Scenario> scenarios;

    public void addScenario(Scenario scenario) {
        scenarios.put(scenarios.size(), scenario);
    }

    /**
     * @param number id of the scenario
     * @return true if it enables and false if disables
     */
    public boolean switchScenario(int number) {
        if (scenarios.get(number).enable()) {
            return true;
        } else {
            scenarios.get(number).disable();
            return false;
        }
    }

    public Map<Integer, Scenario> getScenarios() {
        return scenarios;
    }

    private ScenarioManager() {
        this.scenarios = new HashMap<>();
    }

    public static ScenarioManager getInstance() {
        if (instance == null)
            instance = new ScenarioManager();
        return instance;
    }

    public void disableAll() {
        scenarios.forEach((integer, scenario) -> scenario.disable());
    }
}
