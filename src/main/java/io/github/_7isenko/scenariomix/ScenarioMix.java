package io.github._7isenko.scenariomix;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ScenarioMix extends JavaPlugin {
    // How to build: Maven/ScenarioMix/Lifecycle/package
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        this.getCommand("mix").setExecutor(new ScenarioMixCommand());
        getServer().getPluginManager().registerEvents(new SpigotListener(), plugin);
    }

    @Override
    public void onDisable() {

    }
}