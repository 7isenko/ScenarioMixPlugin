package io.github._7isenko.scenariomix.scenarios.tools.autospectator;

import io.github._7isenko.scenariomix.ScenarioMix;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

class RespawnListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL || event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            // It is buggy
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().setGameMode(GameMode.SPECTATOR);
                }
            }.runTask(ScenarioMix.plugin);
        }
    }
}
