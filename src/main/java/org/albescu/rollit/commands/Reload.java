package org.albescu.rollit.commands;

import org.albescu.rollit.utils.ConfigHandler;
import org.albescu.rollit.utils.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ConfigHandler.Reload();
        Logger.Success(commandSender, "Config reloaded!");
        return true;
    }
}
