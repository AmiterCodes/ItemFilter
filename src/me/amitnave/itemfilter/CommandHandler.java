package me.amitnave.itemfilter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import java.util.List;

public class CommandHandler implements CommandExecutor {
    private Player player;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if(args.length == 0) {

        }
        if(args.length == 1) {
         switch(args[0]) {
             case "hand":
                addMaterialToPlayer(player, player.getInventory().getItemInMainHand().getType());




                 break;

             case "view":


                GUIHandler.openGUI(player, 1);


                 break;

             case "reload":





                 break;

             case "help":

                //TODO: help function
                 player.sendMessage("hey\nheya");


                 break;
                 default:
                     player.sendMessage(ConfigHandler.getString("message.unknown-command"));
                     break;
         }
        }



        return true;
    }
    public void addMaterialToPlayer(Player player, Material mat) {
        if(mat.equals(Material.AIR)) {
            player.sendMessage(ConfigHandler.getString("message.command-filter-hand-no-item-in-hand"));
        }
        if(Main.playerFilterList.containsKey(player)) {
            if(Main.playerFilterList.get(player).contains(mat)) {
                player.sendMessage(ConfigHandler.getString("message.command-filter-hand-already-in-filter"));
            } else {
                Main.playerFilterList.get(player).add(mat);
                player.sendMessage(ConfigHandler.getString("message.command-filter-hand-successful"));
            }
        } else {
            List<Material> matList = new ArrayList<Material>();
            matList.add(mat);
            player.sendMessage(ConfigHandler.getString("message.command-filter-hand-successful"));
            Main.playerFilterList.put(player, matList);
        }
    }
}
