package io.github._7isenko.scenariomix.scenarios;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import io.github._7isenko.scenariomix.utils.MaterialUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
@SuppressWarnings("rawtypes")
public abstract class Scenario {
    private final String name;
    private final String configName;
    private final String[] description;
    private Material icon;
    private boolean started;
    private Map<BukkitRunnable, Integer> runnables;
    private final Set<Listener> listeners;
    private final Map<String, Configuration> configs;

    public Scenario(String name, String configName, String icon, String... description) {
        this.name = name;
        this.configName = configName;
        this.description = description;
        this.icon = MaterialUtils.getMaterial(icon);
        this.started = false;
        this.runnables = new HashMap<>();
        this.listeners = new HashSet<>();
        this.configs = new HashMap<>();
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

    public void disable() {
        if (started) {
            started = false;
            stop();
            stopListeners();
            stopBukkitRunnables();
        }
    }

    public abstract void start();

    public abstract void stop();

    public void addConfig(Configuration configuration) {
        configs.put(configuration.getName(), configuration);
    }

    public void addBukkitRunnable(BukkitRunnable runnable, int period) {
        runnables.put(runnable, period);
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    private void startListeners() {
        listeners.forEach((listener) -> Bukkit.getPluginManager().registerEvents(listener, ScenarioMix.plugin));
    }

    private void stopListeners() {
        listeners.forEach(HandlerList::unregisterAll);
    }

    private void startBukkitRunnables() {
        runnables.forEach((runnable, period) -> runnable.runTaskTimer(ScenarioMix.plugin, 20L, (long) period));
    }

    private void stopBukkitRunnables() {
        HashMap<BukkitRunnable, Integer> newRunnables = new HashMap<>();
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

    public boolean isConfigurable() {
        return !configs.isEmpty();
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return Arrays.asList(description);
    }

    public Set<String> getConfigStrings() {
        return configs.keySet();
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = MaterialUtils.getMaterial(icon);
    }

    public boolean isStarted() {
        return started;
    }

    public String getConfigName() {
        return configName;
    }

    public Map<String, Configuration> getConfigs() {
        return configs;
    }
}
