package io.github._7isenko.scenariomix.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 7isenko
 */
public class CooldownCounter {

    private final Map<UUID, Integer> cooldownMap;

    public CooldownCounter(Plugin plugin, int period) {
        cooldownMap = new HashMap<>();
        new BukkitRunnable() {
            @Override
            public void run() {
                tick();
            }
        }.runTaskTimer(plugin, 0, period);
    }

    private void tick() {
        decrementCooldownValues();
        cleanupCooldownEntries();
    }

    private void decrementCooldownValues() {
        cooldownMap.replaceAll(((uuid, cooldown) -> cooldown - 1));
    }

    private void cleanupCooldownEntries() {
        cooldownMap.keySet().removeIf(uuid -> cooldownMap.get(uuid) < 0);
    }

    public int getCooldown(Player player) {
        return getCooldown(player.getUniqueId());
    }

    public int getCooldown(UUID uuid) {
        return cooldownMap.getOrDefault(uuid, 0);
    }

    public void setCooldown(Player player, int cooldown) {
        setCooldown(player.getUniqueId(), cooldown);
    }

    public void setCooldown(UUID uuid, int cooldown) {
        cooldownMap.put(uuid, cooldown);
    }

}
