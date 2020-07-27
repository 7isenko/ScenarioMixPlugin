package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.ScenarioManager;
import io.github._7isenko.scenariomix.ScenarioMix;
import org.bukkit.Bukkit;

public class ToolMenu extends Menu {
    private static ToolMenu instance;

    private ToolMenu() {
        String name = "Доступные инструменты";
        scenarios = ScenarioManager.getInstance().getToolScenarios();
        inventory = createInventory(name, ScenarioManager.getInstance().getToolScenarios());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), ScenarioMix.plugin);
    }

    protected boolean switchScenario(int slot) {
        return ScenarioManager.getInstance().switchToolScenario(slot);
    }

    public static ToolMenu getInstance() {
        if (instance == null)
            instance = new ToolMenu();
        return instance;
    }
}
