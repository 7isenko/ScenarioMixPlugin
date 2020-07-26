package io.github._7isenko.scenariomix.scenarios;

import org.bukkit.Material;

public abstract class Scenario {
    private String name;
    private String description;
    private Material icon;
    private boolean started;

    public abstract void start();

    public abstract void stop();
}
