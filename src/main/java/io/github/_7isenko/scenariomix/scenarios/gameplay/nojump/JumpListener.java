package io.github._7isenko.scenariomix.scenarios.gameplay.nojump;

import io.github._7isenko.scenariomix.utils.PlayerUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class JumpListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (PlayerUtils.isValid(event.getPlayer()))
            if (!event.getPlayer().isOnGround() && event.getFrom().getY() + 0.419 < event.getTo().getY()) {
                event.getPlayer().setHealth(0);
            }
    }
}
