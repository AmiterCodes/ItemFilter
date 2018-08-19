package me.amitnave.itemfilter;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onPickUp(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getEntity();
        if (Main.playerFilterList.get(player).contains(e.getItem().getItemStack().getType())) {
            e.setCancelled(true);
            return;
        }
    }
}
