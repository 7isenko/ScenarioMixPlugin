package io.github._7isenko.scenariomix.scenarios.gameplay.lastsight;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class LastSight extends Scenario {

    public LastSight() {
        super("Последний взгляд", "last_sight", new String[]{"Взгляд на человека с тегом", "last_sight вас мгновенно убьёт", "/scoreboard players tag <nick> add last_sight"}, Material.GLASS);
    }

    public void start() {
        addBukkitRunnable(new KillingRunnable(), 2);
    }

    public void stop() {
    }

}
