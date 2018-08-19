package me.amitnave.itemfilter;

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
        this.getCommand("filter").setExecutor(new CommandHandler());
        ConfigHandler.setConfig(config);
    }
    @Override
    public void onDisable() {

    }



}
