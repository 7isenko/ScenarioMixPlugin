package io.github._7isenko.scenariomix.utils;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Configuration;
import org.bukkit.Material;

public class Parser {
    public static Boolean parseBoolean(String string) throws IllegalArgumentException {
        if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("allow"))
            return true;
        if (string.equalsIgnoreCase("false") || string.equalsIgnoreCase("falce") || string.equalsIgnoreCase("none") || string.equalsIgnoreCase("deny"))
            return false;
        throw new IllegalArgumentException("Can't parse boolean");
    }
    public static String getConfigCommand(Configuration configuration) {
        return "/" + ScenarioMix.command + " " + configuration.getScenario().getConfigName() + " " + configuration.getName() + " <" + createTypeMessage(configuration) + ">";
    }

    public static String createTypeMessage(Configuration configuration) {
        if (configuration.value() instanceof Integer)
            return "целое число";
        if (configuration.value() instanceof Boolean)
            return "true/false"; // или allow/deny
        if (configuration.value() instanceof Material)
            return "тип";
        if (configuration.value() instanceof String)
            return "текст";
        return "значение";
    }
}
