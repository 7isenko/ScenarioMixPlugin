package io.github._7isenko.scenariomix.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchanter {
    public static void enchantItem(ItemStack item, boolean removeAll) {
        ItemMeta meta = item.getItemMeta();
        if (removeAll)
            meta.getEnchants().forEach((enchantment, integer) -> meta.removeEnchant(enchantment));
        else
            meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        item.setItemMeta(meta);
    }
}
