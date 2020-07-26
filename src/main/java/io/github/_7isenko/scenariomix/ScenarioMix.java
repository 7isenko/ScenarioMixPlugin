package io.github._7isenko.scenariomix;

import io.github._7isenko.scenariomix.scenarios.lowestkiller.LowestKiller;
import io.github._7isenko.scenariomix.scenarios.pusher.Pusher;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ScenarioMix extends JavaPlugin {
    // How to build: Maven/ScenarioMix/Lifecycle/package
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        loadScenarioManager();
        this.getCommand("mix").setExecutor(new ScenarioMixCommand());
    }

    @Override
    public void onDisable() {
        ScenarioManager.getInstance().disableAll();
    }

    private void loadScenarioManager() {
        ScenarioManager.getInstance().addScenario(new Pusher());
        ScenarioManager.getInstance().addScenario(new LowestKiller());
    }
}