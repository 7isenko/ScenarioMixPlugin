package io.github._7isenko.scenariomix.scenarios.tools.autospectator;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL)
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
    }
}
