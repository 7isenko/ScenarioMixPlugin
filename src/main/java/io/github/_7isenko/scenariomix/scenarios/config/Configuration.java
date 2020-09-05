package io.github._7isenko.scenariomix.scenarios.config;

import io.github._7isenko.scenariomix.scenarios.Scenario;
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

    @SuppressWarnings("unchecked")
    public void setStringValue(String string) throws IllegalArgumentException, UnsupportedDataTypeException {
        ValueType type = getValueType();
        switch (type) {
            case STRING:
                setValue((T) string);
                break;
            case BOOLEAN:
                setValue((T) Parser.parseBoolean(string));
                break;
            case INTEGER:
                setValue((T) Integer.valueOf(string));
                break;
            case MATERIAL:
                Material material = Material.getMaterial(string.toUpperCase());
                if (material != null) setValue((T) material);
                else
                    throw new NoSuchElementException("Такого материала нет");
                break;
            default:
                throw new UnsupportedDataTypeException("Такой тип данных не поддерживается.");
        }
    }

    public T getValue() {
        return value;
    }

    public ValueType getValueType() {
        if (value instanceof Integer)
            return ValueType.INTEGER;
        else if (value instanceof Boolean)
            return ValueType.INTEGER;
        else if (value instanceof Material)
            return ValueType.MATERIAL;
        else if (value instanceof String)
            return ValueType.STRING;
        else return ValueType.UNKNOWN;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Scenario getScenario() {
        return scenario;
    }

}
