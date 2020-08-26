package io.github._7isenko.scenariomix.scenarios.tools.autospectator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

class RespawnListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            // It is buggy
            // event.getPlayer().setGameMode(GameMode.SPECTATOR);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode 3 " + event.getPlayer().getName());
        }
    }
}
