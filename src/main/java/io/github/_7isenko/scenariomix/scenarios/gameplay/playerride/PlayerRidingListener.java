package io.github._7isenko.scenariomix.scenarios.gameplay.playerride;

import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
            } else if (player2.getVehicle() == null || !(player2.getVehicle() instanceof Pig)) {
                sitOnStand(event.getPlayer(), player2);
            }
        }
    }

    @EventHandler
    public void onLeave(VehicleExitEvent event) {
        if (event.getVehicle().getType() == EntityType.PLAYER || event.getVehicle().getType() == EntityType.PIG) {
            if (!allowLeave.getValue())
                event.setCancelled(true);
        }
    }

    private Entity getTopPlayer(Entity entity) {
        if (!entity.getPassengers().isEmpty()) {
            return getTopPlayer(entity.getPassengers().get(0));
        } else if (entity instanceof Player)
            return createPig(entity);
        else return entity;
    }

    private void sitOnStand(Player player, Player vehicle) {
        if (vehicle.getPassengers().isEmpty()) {
            createPig(vehicle).addPassenger(player);
        } else vehicle.getPassengers().get(0).addPassenger(player);
    }

    private Entity createPig(Entity player) {
        if (!player.getPassengers().isEmpty())
            if (player.getPassengers().get(0) instanceof ArmorStand)
                return player.getPassengers().get(0);
        Pig entity = player.getWorld().spawn(player.getLocation(), Pig.class);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30000, 1, true, false));
        entity.setInvulnerable(true);
        entity.setSilent(true);
        player.addPassenger(entity);
        return entity;
    }
}
