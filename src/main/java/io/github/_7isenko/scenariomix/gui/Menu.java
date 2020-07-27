package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.utils.Calculator;
import io.github._7isenko.scenariomix.utils.Enchanter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class Menu {
    private static Menu instance;
    protected Inventory inventory;
    protected Map<Integer, Scenario> scenarios;

    public void open(HumanEntity player) {
        player.openInventory(inventory);
    }

    /**
     * "There is no default constructor available in Menu" sucks, don't use it, cuz it is singleton
     */
    public Menu() {
        String name = "Выберите тип сценариев";
        inventory = createMenuInventory(name);
        Bukkit.getPluginManager().registerEvents(new Menu.MenuListener(), ScenarioMix.plugin);
    }

    private Inventory createMenuInventory(String name) {
        Inventory inventory = Bukkit.createInventory(null, 9, name);

        ItemStack gameplay = new ItemStack(Material.BEACON);
        ItemMeta meta = gameplay.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Игровые сценарии");
        gameplay.setItemMeta(meta);
        inventory.setItem(3, gameplay);

        ItemStack tools = new ItemStack(Material.ANVIL);
        ItemMeta mt = tools.getItemMeta();
        mt.setDisplayName(ChatColor.YELLOW + "Инструменты");
        tools.setItemMeta(mt);
        inventory.setItem(5, tools);

        return inventory;
    }


    private class MenuListener implements Listener {
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onClick(InventoryClickEvent event) {
            if (!event.getInventory().getName().equals(inventory.getName()))
                return;
            event.setCancelled(true);
            int slot = event.getSlot();

            switch (slot) {
                case 3:
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            ScenarioMenu.getInstance().open(event.getWhoClicked());
                        }
                    }.runTaskLater(ScenarioMix.plugin, 1);
                    break;

                case 5:
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            ToolMenu.getInstance().open(event.getWhoClicked());
                        }
                    }.runTaskLater(ScenarioMix.plugin, 1);
                    break;
            }
        }

        @EventHandler
        public void onInventoryClick(final InventoryDragEvent e) {
            if (e.getInventory() == inventory) {
                e.setCancelled(true);
            }
        }
    }

    protected boolean switchScenario(int slot) {
        return false;
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
            boolean enabled = switchScenario(slot);
            String name = scenarios.get(slot).getName();
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

    public Inventory createInventory(String name, Map<Integer, Scenario> scenarioSet) {
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


    public static Menu getInstance() {
        if (instance == null)
            instance = new Menu();
        return instance;
    }

}
