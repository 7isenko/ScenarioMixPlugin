package io.github._7isenko.scenariomix.scenarios.gameplay.playerride;

import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class PlayerRidingListener implements Listener {

    private final Configuration<Boolean> allowMany;
    private final Configuration<Boolean> allowLeave;

    public PlayerRidingListener(Configuration<Boolean> allowMany, Configuration<Boolean> allowLeave) {
        this.allowMany = allowMany;
        this.allowLeave = allowLeave;
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player player2 = (Player) event.getRightClicked();
            if (allowMany.getValue()) {
                getTopPlayer(player2).addPassenger(event.getPlayer());
            } else if (player2.getVehicle() == null || !(player2.getVehicle() instanceof Player)) {
                player2.addPassenger(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onLeave(VehicleExitEvent event) {
        if (event.getVehicle() instanceof Player) {
            if (!allowLeave.getValue())
                event.setCancelled(true);
        }
    }

    private Entity getTopPlayer(Entity entity) {
        if (!entity.getPassengers().isEmpty()) {
            return getTopPlayer(entity.getPassengers().get(0));
        }
        return entity;
    }


}
