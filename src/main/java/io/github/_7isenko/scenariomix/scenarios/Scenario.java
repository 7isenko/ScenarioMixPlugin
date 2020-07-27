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
    private String[] description;
    private Material icon;
    private boolean started;
    private Map<BukkitRunnable, Integer> runnables;
    private Set<Listener> listeners;

    public Scenario(String name, String[] description, Material icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.started = false;
        // TODO: recreate runnable system, cancel and restart cause exception
        runnables = new HashMap<>();
        listeners = new HashSet<>();
    }

    public boolean enable() {
        if (started) return false;
        started = true;
        start();
        startListeners();
        startBukkitRunnables();
        return true;
    }

    public boolean disable() {
        if (!started) return false;
        started = false;
        stop();
        stopListeners();
        stopBukkitRunnables();
        return true;
    }

    public abstract void start();

    public abstract void stop();

    public void addBukkitRunnable(BukkitRunnable runnable, int period) {
        runnables.put(runnable, period);
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void startListeners() {
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, ScenarioMix.plugin));
    }

    public void stopListeners() {
        listeners.forEach(HandlerList::unregisterAll);
    }

    public void startBukkitRunnables() {
        runnables.forEach((runnable, period) -> runnable.runTaskTimer(ScenarioMix.plugin, 20, period));
    }

    public void stopBukkitRunnables() {
        HashMap<BukkitRunnable, Integer> newRunnables = new HashMap<>();
        runnables.forEach((runnable, integer) -> {
            runnable.cancel();
            try {
                newRunnables.put(runnable.getClass().newInstance(), integer);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        runnables.clear();
        runnables = newRunnables;
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
}
