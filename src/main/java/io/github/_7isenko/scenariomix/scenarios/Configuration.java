package io.github._7isenko.scenariomix.scenarios;

import io.github._7isenko.scenariomix.utils.Parser;
import org.bukkit.Material;

import javax.activation.UnsupportedDataTypeException;
import java.util.NoSuchElementException;

public class Configuration<T> {
    private final String name;
    private final String[] description;
    private Material icon;
    private T value;
    private final Scenario scenario;

    public Configuration(String name, T defaultValue, Material icon, Scenario scenario, String... description) {
        this.name = name;
        this.value = defaultValue;
        this.icon = icon;
        this.scenario = scenario;
        this.description = description;
    }

    public Configuration(String name, Material icon, Scenario scenario, String... description) {
        this.name = name;
        this.icon = icon;
        this.scenario = scenario;
        this.description = description;

        this.value = null;
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

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public T value() {
        return value;
    }

    public void setStringValue(T value) {
        this.value = value;
    }

    public void setStringValue(String string) throws IllegalArgumentException, UnsupportedDataTypeException {
        if (value instanceof Integer)
            setValue((T) Integer.valueOf(string));
        else if (value instanceof Boolean)
            setValue((T) Parser.parseBoolean(string));
        else if (value instanceof Material) {
            Material material = Material.getMaterial(string.toUpperCase());
            if (material != null) setValue((T) material);
            else
                throw new NoSuchElementException("Такого материала нет");
        } else if (value instanceof String)
            setValue((T) string);
        else throw new UnsupportedDataTypeException("Такой тип данных не поддерживается.");
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public Class getValueClass() {
        return value.getClass();
    }

}
