package me.amitnave.itemfilter;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {
    public static FileConfiguration config;
    public static String getString(String path) {
        String prefix = config.getString("message.prefix");
        return config.getString(path)
                .replaceAll("%prefix%", prefix)
                .replaceAll("&", "§");

        }

        public static int getInt(String path) {
            return config.getInt(path);
        }

        public static List<String> getList(String path) {
        List<String> tempList = config.getStringList(path);
        List<String> newList = new ArrayList<String>();
        for(String s : tempList) {
            s = s.replaceAll("&", "§");
            newList.add(s);
        }

        return newList;
        }
}
