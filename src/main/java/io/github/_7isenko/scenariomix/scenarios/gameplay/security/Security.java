package io.github._7isenko.scenariomix.scenarios.gameplay.security;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class Security extends Scenario {
    public Security() {
        super("Security", "security", "SHIELD", "weak - не может бить;" +
                "strong - не может ломать");
        addListener(new WeakAttackListener());
        addListener(new BreakListener());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
