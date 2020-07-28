package io.github._7isenko.scenariomix.scenarios.tools.autorespawn;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class AutoRespawn extends Scenario {
    public AutoRespawn() {
        super("Авто-возрожение", new String[]{"При смерти игрок будет", "автоматически возрождён"}, Material.FENCE_GATE);
    }

    @Override
    public void start() {
        addListener(new DeathListener());
    }

    @Override
    public void stop() {

    }
}
