package io.github._7isenko.scenariomix.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public abstract class Menu {
    protected Inventory inventory;

    public void open(HumanEntity player) {
        player.openInventory(inventory);
    }
}
