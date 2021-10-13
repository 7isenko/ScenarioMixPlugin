package io.github._7isenko.scenariomix.scenarios.tools.nocrouchwithtag;

import io.github._7isenko.scenariomix.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * @author 7isenko
 */
public class CrouchListener implements Listener {
    @EventHandler
    public void onCrouch(PlayerToggleSneakEvent event) {
        if (PlayerUtils.isValid(event.getPlayer()) && event.getPlayer().getScoreboardTags().contains("nocrouch")) {
            Player player = event.getPlayer();
            player.setHealth(0d);
        }
    }
}
