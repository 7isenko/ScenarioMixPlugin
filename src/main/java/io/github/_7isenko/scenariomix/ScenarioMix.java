package io.github._7isenko.scenariomix;

import io.github._7isenko.scenariomix.scenarios.ScenarioManager;
import io.github._7isenko.scenariomix.scenarios.gameplay.lowestkiller.LowestKiller;
import io.github._7isenko.scenariomix.scenarios.gameplay.pusher.Pusher;
import io.github._7isenko.scenariomix.scenarios.gameplay.snowballs.Snowballs;
import io.github._7isenko.scenariomix.scenarios.tools.autospectator.AutoSpectator;
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
        ScenarioManager scenarioManager = ScenarioManager.getInstance();
        scenarioManager.addToolScenario(new AutoSpectator());
        scenarioManager.addScenario(new Pusher());
        scenarioManager.addScenario(new LowestKiller());
        scenarioManager.addScenario(new Snowballs());
    }
}