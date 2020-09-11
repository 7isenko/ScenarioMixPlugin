package io.github._7isenko.scenariomix;

import io.github._7isenko.scenariomix.cui.ConfigurationTabCompleter;
import io.github._7isenko.scenariomix.cui.ScenarioMixCommand;
import io.github._7isenko.scenariomix.scenarios.ScenarioManager;
import io.github._7isenko.scenariomix.scenarios.gameplay.apocalypse.Apocalypse;
import io.github._7isenko.scenariomix.scenarios.gameplay.fill.Fill;
import io.github._7isenko.scenariomix.scenarios.gameplay.lastsight.LastSight;
import io.github._7isenko.scenariomix.scenarios.gameplay.lowestkiller.LowestKiller;
import io.github._7isenko.scenariomix.scenarios.gameplay.playerride.PlayerRide;
import io.github._7isenko.scenariomix.scenarios.gameplay.pusher.Pusher;
import io.github._7isenko.scenariomix.scenarios.gameplay.snowballs.Snowballs;
import io.github._7isenko.scenariomix.scenarios.gameplay.snowfall.Snowfall;
import io.github._7isenko.scenariomix.scenarios.gameplay.spiderpocalypse.Spiderpocalypse;
import io.github._7isenko.scenariomix.scenarios.tools.autorespawn.AutoRespawn;
import io.github._7isenko.scenariomix.scenarios.tools.autospectator.AutoSpectator;
import io.github._7isenko.scenariomix.scenarios.tools.fightme.FightMe;
import io.github._7isenko.scenariomix.scenarios.tools.heightlimit.HeightLimit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ScenarioMix extends JavaPlugin {
    // How to build: Maven/ScenarioMix/Lifecycle/package
    public static Plugin plugin;
    public static final String command = "mix";

    @Override
    public void onEnable() {
        plugin = this;
        loadScenarioManager();
        this.getCommand(command).setExecutor(new ScenarioMixCommand());
        this.getCommand(command).setTabCompleter(new ConfigurationTabCompleter());
    }

    @Override
    public void onDisable() {
        ScenarioManager.getInstance().disableAll();
    }

    private void loadScenarioManager() {
        ScenarioManager scenarioManager = ScenarioManager.getInstance();
        scenarioManager.addToolScenario(new AutoSpectator());
        scenarioManager.addToolScenario(new AutoRespawn());
        scenarioManager.addToolScenario(new FightMe());
        scenarioManager.addToolScenario(new HeightLimit());
        scenarioManager.addScenario(new Pusher());
        scenarioManager.addScenario(new LowestKiller());
        scenarioManager.addScenario(new Snowballs());
        scenarioManager.addScenario(new Snowfall());
        scenarioManager.addScenario(new LastSight());
        scenarioManager.addScenario(new Fill());
        scenarioManager.addScenario(new Spiderpocalypse());
        scenarioManager.addScenario(new Apocalypse());
        scenarioManager.addScenario(new PlayerRide());
    }
}