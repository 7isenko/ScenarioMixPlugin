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

public class ScenarioMenu {
    private static ScenarioMenu instance;
    private Inventory inventory;

    public void open(Player player) {
        player.openInventory(inventory);
    }

    private ScenarioMenu() {
        String name = "Доступные сценарии";
        inventory = createInventory(name, ScenarioManager.getInstance().getScenarios());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), ScenarioMix.plugin);
    }

    private Inventory createInventory(String name, Map<Integer, Scenario> scenarioSet) {
        Inventory inventory = Bukkit.createInventory(null, Calculator.calculateInventorySize(scenarioSet.size()), name);
        scenarioSet.forEach((number, scenario) -> {
            ItemStack item = new ItemStack(scenario.getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + scenario.getName());
            meta.setLore(scenario.getDescription());
            item.setItemMeta(meta);
            inventory.setItem(number, item);
        });
        return inventory;
    }

    public class InventoryListener implements Listener {
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onClick(InventoryClickEvent event) {
            if (!event.getInventory().getName().equals(inventory.getName()))
                return;
            event.setCancelled(true);
            final ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
            int slot = event.getSlot();
            boolean enabled = ScenarioManager.getInstance().switchScenario(slot);
            String name = ScenarioManager.getInstance().getScenarios().get(slot).getName();
            if (enabled)
                event.getWhoClicked().sendMessage(ChatColor.GREEN + "Вы включили сценарий " + ChatColor.BLUE + ChatColor.BOLD + name);
            else
                event.getWhoClicked().sendMessage(ChatColor.YELLOW + "Вы отключили сценарий " + ChatColor.RED + ChatColor.BOLD + name);
            Enchanter.enchantItem(inventory.getItem(slot), !enabled);
        }

        @EventHandler
        public void onInventoryClick(final InventoryDragEvent e) {
            if (e.getInventory() == inventory) {
                e.setCancelled(true);
            }
        }
    }

    public static ScenarioMenu getInstance() {
        if (instance == null)
            instance = new ScenarioMenu();
        return instance;
    }
}
