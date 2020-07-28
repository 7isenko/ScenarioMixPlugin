package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.scenarios.ScenarioManager;
import io.github._7isenko.scenariomix.ScenarioMix;
import org.bukkit.Bukkit;

public class ToolScenarioMenu extends ScenarioMenu {

    public ToolScenarioMenu() {
        String name = "Доступные инструменты";
        scenarios = ScenarioManager.getInstance().getToolScenarios();
        inventory = createInventory(name, ScenarioManager.getInstance().getToolScenarios());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), ScenarioMix.plugin);
    }

    protected boolean switchScenario(int slot) {
        return ScenarioManager.getInstance().switchToolScenario(slot);
    }

}
