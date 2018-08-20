package me.amitnave.itemfilter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;


public class GUIHandler implements Listener {
    private static ItemStack prev = new ItemStack(Material.getMaterial(ConfigHandler.getString("PreviousPage-item").toUpperCase()));
    private static ItemStack next = new ItemStack(Material.getMaterial(ConfigHandler.getString("Nextpages-item").toUpperCase()));
    private static Integer page;
    private static Inventory filterGui;
    public static void openGUI(Player player, Integer page) {
        filterGui = Bukkit.createInventory(null,
                ConfigHandler.getInt("GUI.size"),
                ConfigHandler.getString("GUI.inventoryname").replaceAll("%page%", page.toString()));
        GUIHandler.page = page;
        List<Material> list = Main.playerFilterList.get(player);
        if(!Main.playerFilterList.containsKey(player)) {
            Main.playerFilterList.put(player, new ArrayList<>());
        }
        if(Main.playerFilterList.get(player).isEmpty()) {
            try {
                ItemStack noFilters = new ItemStack(Material.getMaterial(ConfigHandler.getString("No-item-filtered-displayitem").toUpperCase()));
                ItemMeta barrier = noFilters.getItemMeta();
                barrier
                .setDisplayName(ConfigHandler.getString("No-item-filtered-displayitem-name"));
                noFilters.setItemMeta(barrier);
                filterGui.setItem(ConfigHandler.getInt("displayeritem-positions") - 1, noFilters);
                player.openInventory(filterGui);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
           Integer pageAmount = ConfigHandler.getInt("PreviousPage-positions") - 1;
            for(Integer i = 0;i<ConfigHandler.getInt("PreviousPage-positions") - 1&&i<list.size() - page * pageAmount + pageAmount;i++) {

                Material mat = list.get(i + (page - 1)*pageAmount);
                ItemStack item = new ItemStack(mat);

                ItemMeta im = item.getItemMeta();
                im.setLore(ConfigHandler.getList("GUI.Lore"));
                item.setItemMeta(im);
                filterGui.setItem(i, item);
            }

            ItemMeta prevItemMeta = prev.getItemMeta();
            prevItemMeta.setDisplayName(ConfigHandler.getString("PreviousPage-item-name"));
            prev.setItemMeta(prevItemMeta);
            if(!page.equals(1))
            filterGui.setItem(ConfigHandler.getInt("PreviousPage-positions") - 1,prev);

            ItemMeta nextItemMeta = next.getItemMeta();
            nextItemMeta.setDisplayName(ConfigHandler.getString("Nextpages-item-name"));
            next.setItemMeta(nextItemMeta);
            filterGui.setItem(ConfigHandler.getInt("Nextpages-positions") - 1,next);




            if(player.getOpenInventory().getTopInventory().equals(filterGui)) {
                player.updateInventory();
            } else {
                player.openInventory(filterGui);
            }
        }



    }
    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        try {
        if(e.getClickedInventory() != null)
        if(e.getClickedInventory().equals(filterGui)) {
            if(e.getCurrentItem() == null ||e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            ItemMeta clickedItemMeta = clickedItem.getItemMeta();
            Player player = (Player) e.getWhoClicked();
            Material mat = clickedItem.getType();
            if (clickedItemMeta.getDisplayName() != null)
                if (clickedItemMeta.getDisplayName().equalsIgnoreCase(ConfigHandler.getString("Nextpages-item-name"))) {
                    openGUI(player, page + 1);
                    Bukkit.getConsoleSender().sendMessage("Hit the next page");
                    e.setCancelled(true);
                    return;
                }
            if (clickedItemMeta.getDisplayName() != null)
                if (clickedItemMeta.getDisplayName().equalsIgnoreCase(ConfigHandler.getString("PreviousPage-item-name"))) {
                    openGUI(player, page - 1);
                    Bukkit.getConsoleSender().sendMessage("Hit the next page");
                    return;
                }
            if (Main.playerFilterList.get(player).contains(mat)) {
                Main.playerFilterList.get(player).remove(clickedItem.getType());
                openGUI(player, page);
            }
            e.setCancelled(true);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}
