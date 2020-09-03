package io.github._7isenko.scenariomix.cui;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.scenarios.ScenarioManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<Scenario> scenarios = ScenarioManager.getInstance().getConfigurableScenarios();
        if (args.length == 1) {
            List<String> filtered = new ArrayList<>();
            for (Scenario scenario : scenarios) {
                filtered.add(scenario.getConfigName());
            }
            return filtered;
        }

        if (args.length == 2) {
            Scenario scenario = ScenarioManager.getInstance().getScenario(args[0]);
            if (scenario.isConfigurable()) {
                return new ArrayList<>(scenario.getConfigStrings());
            }
            return null;
        }

        return null;
    }
}
