package io.github._7isenko.scenariomix.scenarios.snowballs;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowballHitListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball && event.getHitEntity() instanceof LivingEntity) {
            ((LivingEntity) event.getHitEntity()).damage(1, (LivingEntity) event.getEntity().getShooter());
            event.getHitEntity().getWorld().spawnParticle(Particle.SNOW_SHOVEL, event.getHitEntity().getLocation(), 40, 1, 2, 1, 0.1);
        }
    }
}
