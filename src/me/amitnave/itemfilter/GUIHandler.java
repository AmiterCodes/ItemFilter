package me.amitnave.itemfilter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class GUIHandler {

    private Integer page;
    private Inventory filterGui = Bukkit.createInventory(null,
            ConfigHandler.getInt("GUI.size"),
            ConfigHandler.getString("GUI.inventoryname").replaceAll("%page%", page.toString()));
    public void openGUI(Player player, Integer page) {
        List<Material> list = Main.playerFilterList.get(player);
        this.page = page;
        if(Main.playerFilterList.get(player).isEmpty()) {
            try {
                ItemStack noFilters = new ItemStack(Material.getMaterial(ConfigHandler.getString(("No-item-filtered-displayitem"))));

                noFilters
                .getItemMeta()
                .setDisplayName(ConfigHandler.getString("No-item-filtered-displayitem-name"));
                filterGui.setItem(ConfigHandler.getInt("displayeritem-positions") - 1, noFilters);
                player.openInventory(filterGui);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            for(Integer i = 0;i<ConfigHandler.getInt("PreviousPage-positions") - 1&&i<list.size();i++) {
                ItemStack item = new ItemStack(list.get(i));
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ConfigHandler.getString("GUI.Name").replaceAll("%item name", im.getDisplayName()));
                im.setLore(ConfigHandler.getList("GUI.Lore"));

            }
            ItemStack prev = new ItemStack(Material.getMaterial(ConfigHandler.getString("PreviousPage-item")));
            ItemMeta prevItemMeta = prev.getItemMeta();
            prevItemMeta.setDisplayName(ConfigHandler.getString("PreviousPage-item-name"));
            filterGui.setItem(ConfigHandler.getInt("PreviousPage-positions"),prev);
            ItemStack next = new ItemStack(Material.getMaterial(ConfigHandler.getString("nextpages-item")));
            ItemMeta nextItemMeta = next.getItemMeta();
            prevItemMeta.setDisplayName(ConfigHandler.getString("nextpages-item-name"));
            filterGui.setItem(ConfigHandler.getInt("nextpages-positions"),next);




        }



    }
}
