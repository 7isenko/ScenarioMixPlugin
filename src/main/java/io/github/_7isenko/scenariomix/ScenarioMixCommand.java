package io.github._7isenko.scenariomix;

import io.github._7isenko.scenariomix.gui.Menu;
import io.github._7isenko.scenariomix.gui.ScenarioMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScenarioMixCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Menu.getInstance().open(((Player) commandSender));
        } else commandSender.sendMessage(ChatColor.RED + "Эта команда доступна только для игроков");
        return true;
    }
}
