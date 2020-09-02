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

    public Scenario(String name, String configName, Material icon, String[] description) {
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
        if (started) {
            return false;
        } else {
            started = true;
            start();
            startListeners();
            startBukkitRunnables();
            return true;
        }
    }

    public boolean disable() {
        if (!started) {
            return false;
        } else {
            started = false;
            stop();
            stopListeners();
            stopBukkitRunnables();
            return true;
        }
    }

    public abstract void start();

    public abstract void stop();

    public void addConfig(Configuration config) {
        configs.put(config.getName(), config);
    }

    public void addBukkitRunnable(BukkitRunnable runnable, int period) {
        runnables.put(runnable, period);
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void startListeners() {
        listeners.forEach((listener) -> {
            Bukkit.getPluginManager().registerEvents(listener, ScenarioMix.plugin);
        });
    }

    public void stopListeners() {
        listeners.forEach(HandlerList::unregisterAll);
    }

    public void startBukkitRunnables() {
        runnables.forEach((runnable, period) -> {
            runnable.runTaskTimer(ScenarioMix.plugin, 20L, (long) period);
        });
    }

    public void stopBukkitRunnables() {
        HashMap<BukkitRunnable, Integer> newRunnables = new HashMap();
        runnables.forEach((runnable, integer) -> {
            runnable.cancel();

            try {
                newRunnables.put(runnable.getClass().newInstance(), integer);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

        });
        runnables.clear();
        runnables = newRunnables;
    }

    public Configuration getConfig(String name) {
        return configs.get(name);
    }

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

    public String getConfigName() {
        return configName;
    }
}
