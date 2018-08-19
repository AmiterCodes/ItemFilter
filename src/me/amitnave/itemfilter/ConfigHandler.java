package me.amitnave.itemfilter;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigHandler {
    public FileConfiguration getConfig() {
        return config;
    }

    public static void setConfig(FileConfiguration config) {
        ConfigHandler.config = config;
    }

    private static FileConfiguration config;
    public static String getString(String path) {
        String prefix = config.getString("message.prefix");
        return config.getString(path)
                .replaceAll("&", "§")
                .replaceAll("%prefix%", prefix);
        }

        public static int getInt(String path) {
            return config.getInt(path);
        }

        public static List<String> getList(String path) {
        List<String> tempList = config.getStringList(path);
        tempList.forEach(s -> s.replaceAll("&", "§"));
        return tempList;
        }
}
