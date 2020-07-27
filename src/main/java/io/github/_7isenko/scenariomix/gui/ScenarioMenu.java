package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.ScenarioManager;
import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.utils.Calculator;
import io.github._7isenko.scenariomix.utils.Enchanter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class ScenarioMenu extends Menu {
    private static ScenarioMenu instance;

    private ScenarioMenu() {
        String name = "Доступные сценарии";
        scenarios = ScenarioManager.getInstance().getScenarios();
        inventory = createInventory(name, ScenarioManager.getInstance().getScenarios());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), ScenarioMix.plugin);
    }

    protected boolean switchScenario(int slot) {
        return ScenarioManager.getInstance().switchScenario(slot);
    }

    public static ScenarioMenu getInstance() {
        if (instance == null)
            instance = new ScenarioMenu();
        return instance;
    }
}
