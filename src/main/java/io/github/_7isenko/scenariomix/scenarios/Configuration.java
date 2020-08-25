package io.github._7isenko.scenariomix.scenarios;

import org.bukkit.Material;

public class Configuration {
    private String name;
    private String configName;
    private String[] description;
    private Material icon;

    public Configuration(String name, String configName, String[] description, Material icon) {
        this.name = name;
        this.configName = configName;
        this.description = description;
        this.icon = icon;
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

    public String getConfigName() {
        return this.configName;
    }
}
