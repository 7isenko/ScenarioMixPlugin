package io.github._7isenko.scenariomix.scenarios;

import io.github._7isenko.scenariomix.ScenarioMix;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public abstract class Scenario {
    private String name;
    private String configName;
    private String[] description;
    private Material icon;
    private boolean started;
    private Map<BukkitRunnable, Integer> runnables;
    private Set<Listener> listeners;
    private Map<String, Configuration> configs;

    public Scenario(String name, String configName, String[] description, Material icon) {
        this.name = name;
        this.configName = configName;
        this.description = description;
        this.icon = icon;
        this.started = false;
        this.runnables = new HashMap();
        this.listeners = new HashSet();
        this.configs = new HashMap();
    }

    public boolean enable() {
        if (this.started) {
            return false;
        } else {
            this.started = true;
            this.start();
            this.startListeners();
            this.startBukkitRunnables();
            return true;
        }
    }

    public boolean disable() {
        if (!this.started) {
            return false;
        } else {
            this.started = false;
            this.stop();
            this.stopListeners();
            this.stopBukkitRunnables();
            return true;
        }
    }

    public abstract void start();

    public abstract void stop();

    public void addConfig(Configuration config) {
        this.configs.put(config.getName(), config);
    }

    public void addBukkitRunnable(BukkitRunnable runnable, int period) {
        this.runnables.put(runnable, period);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void startListeners() {
        this.listeners.forEach((listener) -> {
            Bukkit.getPluginManager().registerEvents(listener, ScenarioMix.plugin);
        });
    }

    public void stopListeners() {
        this.listeners.forEach(HandlerList::unregisterAll);
    }

    public void startBukkitRunnables() {
        this.runnables.forEach((runnable, period) -> {
            runnable.runTaskTimer(ScenarioMix.plugin, 20L, (long)period);
        });
    }

    public void stopBukkitRunnables() {
        HashMap<BukkitRunnable, Integer> newRunnables = new HashMap();
        this.runnables.forEach((runnable, integer) -> {
            runnable.cancel();

            try {
                newRunnables.put(runnable.getClass().newInstance(), integer);
            } catch (IllegalAccessException | InstantiationException var4) {
                var4.printStackTrace();
            }

        });
        this.runnables.clear();
        this.runnables = newRunnables;
    }

    public Configuration getConfig(String name) {
        return this.configs.get(name);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getDescription() {
        return Arrays.asList(this.description);
    }

    public Material getIcon() {
        return this.icon;
    }

    public boolean isStarted() {
        return this.started;
    }

    public String getConfigName() {
        return this.configName;
    }
}
