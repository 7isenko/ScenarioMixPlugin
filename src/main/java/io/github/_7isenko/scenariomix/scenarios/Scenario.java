package io.github._7isenko.scenariomix.scenarios;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public abstract class Scenario {
    private String name;
    private String[] description;
    private Material icon;
    private boolean started;

    public Scenario(String name, String[] description, Material icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.started = false;
    }

    public boolean enable() {
        if (started) return false;
        started = true;
        start();
        return true;
    }

    public boolean disable() {
        if (!started) return false;
        started = false;
        stop();
        return true;
    }

    public abstract void start();

    public abstract void stop();

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return Arrays.asList(description);
    }

    public Material getIcon() {
        return icon;
    }

    public boolean isStarted() {
        return started;
    }
}
