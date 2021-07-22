package io.github._7isenko.scenariomix.scenarios.gameplay.waterdamage;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.utils.CooldownCounter;
import io.github._7isenko.scenariomix.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author 7isenko
 */
public class WaterCheckRunnable extends BukkitRunnable {
    private static CooldownCounter cooldownCounter = new CooldownCounter(ScenarioMix.plugin, 1);

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (PlayerUtils.isValid(player) && isInWater(player)) {
                if (cooldownCounter.getCooldown(player) == 0) {
                    player.damage(4);
                    cooldownCounter.setCooldown(player, 10);
                }
            }
        }
    }

    private boolean isInWater(Player player) {
        if (checkWater(player.getLocation()) || checkWater(player.getEyeLocation())) {
            return true;
        } else if (checkWater(player.getLocation().add(0.3, 0, 0.3)) || checkWater(player.getLocation().add(-0.3, 0, 0.3)) || checkWater(player.getLocation().add(0.3, 0, -0.3)) || checkWater(player.getLocation().add(-0.3, 0, -0.3))) {
            return true;
        } else
            return checkWater(player.getEyeLocation().add(0.3, 0, 0.3)) || checkWater(player.getEyeLocation().add(-0.3, 0, 0.3)) || checkWater(player.getEyeLocation().add(0.3, 0, -0.3)) || checkWater(player.getEyeLocation().add(-0.3, 0, -0.3));
    }

    private boolean checkWater(Location location) {
        if (location.getBlock().getBlockData() instanceof Waterlogged) {
            Waterlogged waterlogged = (Waterlogged) location.getBlock().getBlockData();
            return waterlogged.isWaterlogged();
        }
        return location.getBlock().getType() == Material.WATER;
    }
}
