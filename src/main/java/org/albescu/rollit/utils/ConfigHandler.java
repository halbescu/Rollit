package org.albescu.rollit.utils;

import org.albescu.rollit.Rollit;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
    static FileConfiguration config = Rollit.getInstance().getConfig();

    public static void Reload() {
        Rollit.getInstance().reloadConfig();
        config = Rollit.getInstance().getConfig();
    }

    public static int getMaxDice() {
        return config.getInt("max_dice", 100);
    }

    public static int getMaxSides() {
        return config.getInt("max_sides", 100);
    }

    public static int getLocalRadius() {
        return config.getInt("local_radius", 200);
    }
}
