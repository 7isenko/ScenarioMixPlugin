package io.github._7isenko.scenariomix.scenarios;

import org.bukkit.Material;

public class Configuration<T> {
    private String name;
    private String[] description;
    private Material icon;
    private T value;

    public Configuration(String name, T defaultValue, Material icon, String[] description) {
        this.name = name;
        this.value = defaultValue;
        this.icon = icon;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String[] getDescription() {
        return this.description;
    }

    public Material getIcon() {
        return this.icon;
    }

    public T value() {
        return value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
