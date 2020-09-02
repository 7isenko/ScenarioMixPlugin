package io.github._7isenko.scenariomix.scenarios.tools.autospectator;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class AutoSpectator extends Scenario {

    public AutoSpectator() {
        super("Авто-гм 3", "auto_spectator", Material.FEATHER, new String[]{"Когда игрок возраждается, он","автоматически получает гм 3"});
    }

    @Override
    public void start() {
        addListener(new RespawnListener());
    }

    @Override
    public void stop() {

    }
}
