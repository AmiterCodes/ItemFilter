package me.amitnave.itemfilter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {
    public FileConfiguration config = this.getConfig();

    public static HashMap<Player, List<Material>> playerFilterList = new HashMap<>();
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        ConfigHandler.config = config;
        Bukkit.getConsoleSender().sendMessage(config.getString("PreviousPage-item"));
        Bukkit.getPluginManager().registerEvents(new GUIHandler(), this);
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        this.getCommand("filter").setExecutor(new CommandHandler());

    }
    @Override
    public void onDisable() {

    }




}
