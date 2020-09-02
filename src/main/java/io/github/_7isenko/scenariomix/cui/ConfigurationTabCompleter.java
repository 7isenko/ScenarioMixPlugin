package io.github._7isenko.scenariomix.cui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class ConfigurationTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // TODO: https://bukkit.org/threads/easy-no-api-setting-up-custom-tab-completion.299956/
        return null;
    }
}
