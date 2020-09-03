package io.github._7isenko.scenariomix.scenarios;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ScenarioManager {
    private static ScenarioManager instance;
    private Map<Integer, Scenario> scenarios;
    private Map<Integer, Scenario> toolScenarios;

    public void addScenario(Scenario scenario) {
        scenarios.put(scenarios.size(), scenario);
    }

    public void addToolScenario(Scenario scenario) {
        toolScenarios.put(toolScenarios.size(), scenario);
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

    public boolean switchToolScenario(int number) {
        if (toolScenarios.get(number).enable()) {
            return true;
        } else {
            toolScenarios.get(number).disable();
            return false;
        }
    }

    public Map<Integer, Scenario> getToolScenarios() {
        return toolScenarios;
    }

    public Map<Integer, Scenario> getScenarios() {
        return scenarios;
    }

    public Scenario getScenario(String name) {
        for (Scenario scenario : getAllScenarios()) {
            if (scenario.getConfigName().equalsIgnoreCase(name))
                return scenario;
        }
        return null;
    }

    public Scenario getToolScenario(int number) {
        return toolScenarios.get(number);
    }

    public Scenario getScenario(int number) {
        return scenarios.get(number);
    }

    public Collection<Scenario> getAllScenarios() {
        Collection<Scenario> scenarios = new HashSet<>(this.scenarios.values());
        scenarios.addAll(this.toolScenarios.values());
        return scenarios;
    }

    public Collection<String> getAllScenariosNames() {
        Collection<Scenario> scenarios = getAllScenarios();
        Collection<String> names = new HashSet<>();
        scenarios.forEach(scenario -> names.add(scenario.getConfigName()));
        return names;
    }

    private ScenarioManager() {
        this.scenarios = new HashMap<>();
        this.toolScenarios = new HashMap<>();
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
