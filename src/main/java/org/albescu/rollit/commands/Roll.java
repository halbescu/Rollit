package org.albescu.rollit.commands;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.albescu.rollit.classes.RollResult;
import org.albescu.rollit.utils.ConfigHandler;
import org.albescu.rollit.utils.DiceRoller;
import org.albescu.rollit.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Roll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        // Did we even get data
        if (args.length < 1) {
            Logger.Message(sender, "Format: /roll [n_dice]d[n_sides] <l/local>");
            return true;
        }

        // Proper format
        String raw = args[0].toLowerCase();
        String[] values = raw.split("d");
        if (values.length != 2) {
            Logger.Error(sender, "Invalid arguments!");
            return true;
        }

        int n_dice;
        int n_sides;

        // Actually numbers
        try {
            n_dice = Integer.parseInt(values[0]);
            n_sides = Integer.parseInt(values[1]);
        }
        catch (Exception e) {
            Logger.Error(sender, "Values must be numbers!");
            return true;
        }

        // Range
        if (n_dice < 1 || n_dice > ConfigHandler.getMaxDice()) {
            Logger.Error(sender, "Number of dice has to be between 1 and "  + ConfigHandler.getMaxDice() + "!");
            return true;
        }

        if (n_sides <= 1 || n_sides > ConfigHandler.getMaxSides()) {
            Logger.Error(sender, "Number of sides has to be between 2 and "  + ConfigHandler.getMaxSides() + "!");
            return true;
        }

        // Locality (must be player)
        boolean local = false;
        if (args.length >= 2) {
            String locality = args[1];
            if (locality.equalsIgnoreCase("local") || locality.equalsIgnoreCase("l")) {
                if (!(sender instanceof Player)) {
                    Logger.Error(sender, "You must be a player to use this command!");
                    return true;
                }

                local = true;
            }
        }


        String name = sender.getName();
        if (sender instanceof Player) {
            name = ((Player) sender).getDisplayName();
        }

        RollResult result = DiceRoller.Roll(n_dice, n_sides);

        TextComponent message = new TextComponent(
                name + ChatColor.GRAY + " rolled " + ChatColor.WHITE + ChatColor.UNDERLINE + result.getSum()
        );
        String details = n_dice + "D" +  n_sides + "\n" + "Maximum: " + n_dice*n_sides;

        message.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(details)
        ));

        if (local) {
            TextComponent localMessage = new TextComponent(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "L" + ChatColor.GRAY + "] ");
            localMessage.addExtra(message);
            Player p = (Player) sender;
            int radius = ConfigHandler.getLocalRadius();
            for (Entity entity : p.getNearbyEntities(radius, radius, radius)) {
                if (entity instanceof Player nearby) {
                    Logger.Message(nearby, localMessage);
                }
            }
            Logger.Message(p, localMessage);
        }

        else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Logger.Message(p, message);
            }
        }

        return true;
    }
}
