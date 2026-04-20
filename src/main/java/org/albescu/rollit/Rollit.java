package org.albescu.rollit;

import org.albescu.rollit.commands.Reload;
import org.albescu.rollit.commands.Roll;
import org.albescu.rollit.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rollit extends JavaPlugin {
    private static Rollit instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getCommand("roll").setExecutor(new Roll());
        getCommand("reload").setExecutor(new Reload());

        setupConfig();

        Logger.Console(ChatColor.GREEN + "Plugin loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.Console(ChatColor.RED + "Shutting down!");
    }

    public static Rollit getInstance() {
        return instance;
    }

    public void setupConfig() {
        FileConfiguration config = getConfig();

        // Base Settings
        config.addDefault("max_dice", 100);
        config.addDefault("max_sides", 100);
        config.addDefault("local_radius", 200);

        config.options().copyDefaults(true);
        saveConfig();
    }
}
