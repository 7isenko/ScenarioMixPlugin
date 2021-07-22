package io.github._7isenko.scenariomix.scenarios.gameplay.waterdamage;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.scenarios.gameplay.spiderpocalypse.ScarySpiderRunnable;

/**
 * @author 7isenko
 */
public class WaterDamage extends Scenario {

    public WaterDamage() {
        super("Урон от воды", "waterdamage", "WATER_BUCKET", "Вода наносит урон игрокам");
        addBukkitRunnable(new WaterCheckRunnable(), 2);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
