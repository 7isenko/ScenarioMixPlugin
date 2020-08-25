package io.github._7isenko.scenariomix.scenarios.gameplay.lastsight;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class KillingRunnable extends BukkitRunnable {
    public KillingRunnable() {
    }

    public void run() {
        Bukkit.getOnlinePlayers().stream().filter((player) -> {
            return player.getScoreboardTags().contains("last_sight");
        }).forEach((player) -> {
            this.getEntities(player).forEach((entity) -> {
                entity.damage(100.0D, player);
            });
        });
    }

    private Set<LivingEntity> getEntities(Player player) {
        Set<LivingEntity> entities = new HashSet();
        Iterator var3 = player.getNearbyEntities(50.0D, 100.0D, 50.0D).iterator();

        while(var3.hasNext()) {
            Entity entity = (Entity)var3.next();
            if (entity instanceof LivingEntity && this.isLookingAt(player, (LivingEntity)entity)) {
                entities.add((LivingEntity)entity);
            }
        }

        return entities;
    }

    private boolean isLookingAt(Player player, LivingEntity livingEntity) {
        Location eyes = livingEntity.getEyeLocation();
        Vector toPlayer = player.getEyeLocation().toVector().subtract(eyes.toVector());
        double dot = toPlayer.normalize().dot(eyes.getDirection());
        Vector toPlayer2 = player.getLocation().toVector().subtract(eyes.toVector());
        dot = Math.max(toPlayer2.normalize().dot(eyes.getDirection()), dot);
        return dot > 0.99D;
    }
}