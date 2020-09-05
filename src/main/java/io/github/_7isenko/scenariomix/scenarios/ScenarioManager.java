package io.github._7isenko.scenariomix.scenarios;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ScenarioManager {
    private static ScenarioManager instance;
    private final Map<Integer, Scenario> scenarios;
    private final Map<Integer, Scenario> toolScenarios;

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

    @Nullable
    public Scenario getAnyScenario(String name) {
        for (Scenario scenario : getAllScenarios()) {
            if (scenario.getConfigName().equalsIgnoreCase(name))
                return scenario;
        }
        return null;
    }

    @Nullable
    public Scenario getToolScenario(int number) {
        return toolScenarios.get(number);
    }

    @Nullable
    public Scenario getScenario(int number) {
        return scenarios.get(number);
    }

    public Collection<Scenario> getAllScenarios() {
        Collection<Scenario> scenarios = new HashSet<>(this.scenarios.values());
        scenarios.addAll(this.toolScenarios.values());
        return scenarios;
    }

    public List<Scenario> getConfigurableScenarios() {
        ArrayList<Scenario> list = new ArrayList<>();
        for (Scenario scenario : getAllScenarios())
            if (scenario.isConfigurable())
                list.add(scenario);
        return list;
    }

    public List<String> getAllScenariosNames() {
        Collection<Scenario> scenarios = getAllScenarios();
        List<String> names = new ArrayList<>();
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
        scenarios.values().forEach(Scenario::disable);
    }
}
