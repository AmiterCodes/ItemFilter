package me.amitnave.itemfilter;

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





                 break;

             case "reload":





                 break;

             case "help":




                 break;
         }
        }



        return true;
    }
    public void addMaterialToPlayer(Player player, Material mat) {
        if(Main.playerFilterList.containsKey(player)) {
            if(Main.playerFilterList.get(player).contains(mat)) {

            } else
            Main.playerFilterList.get(player).add(mat);
        } else {
            List<Material> matList = new ArrayList<Material>();
            matList.add(mat);
            Main.playerFilterList.put(player, matList);
        }
    }
}
