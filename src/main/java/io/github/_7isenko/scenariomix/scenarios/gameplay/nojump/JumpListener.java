package io.github._7isenko.scenariomix.scenarios.gameplay.nojump;

import com.google.common.collect.Sets;
import io.github._7isenko.scenariomix.utils.PlayerUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;
import java.util.UUID;

public class JumpListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (PlayerUtils.isValid(event.getPlayer()))
            if (!event.getPlayer().isOnGround() && event.getFrom().getY() + 0.419 < event.getTo().getY()) {
                event.getPlayer().setHealth(0);
            }
    }
}
