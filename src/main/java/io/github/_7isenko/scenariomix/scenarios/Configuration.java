package io.github._7isenko.scenariomix.scenarios;

import org.bukkit.Material;

public class Configuration<T> {
    private String name;
    private String[] description;
    private Material icon;
    private T value;

    public Configuration(String name, String[] description, Material icon, T defaultValue) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.value = defaultValue;
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
