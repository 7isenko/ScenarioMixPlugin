package io.github._7isenko.scenariomix.scenarios.gameplay.snowballs;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Snowballs extends Scenario {

    public Snowballs() {
        super("Снежки", "snowballs", Material.SNOW_BALL, new String[]{"Теперь только снежки", "могут наносить урон"});
    }

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(player -> player.getInventory().addItem(new ItemStack(Material.PUMPKIN, 1), new ItemStack(Material.SNOW_BLOCK, 2)));
        addListener(new SnowballHitListener());
    }

    @Override
    public void stop() {

    }
}
