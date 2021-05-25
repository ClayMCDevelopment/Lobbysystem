package eu.claymc.lobbysystem.items.click;

import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.claymc.lobbysystem.enums.LocationEnum;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class NavigatorClickListener implements Listener {

    @EventHandler
    public void handle(final InventoryClickEvent event) {

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        final Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals(ItemEnum.NAVIGATOR.getInventoryName())) {

            switch (event.getSlot()) {

                case 49:
                    teleport(player, LocationEnum.SPAWN);
                    break;
                case 18:
                    teleport(player, LocationEnum.TTT);
                    break;
                case 12:
                    teleport(player, LocationEnum.SHOP);
                    break;
                case 14:
                    break;
                case 26:
                    teleport(player, LocationEnum.KITPVP);
                case 28:
                    teleport(player, LocationEnum.CLAYJUMP);
                    break;
                case 34:
                    teleport(player, LocationEnum.UHC);
                    break;
            }

        }

    }

    private void teleport(Player player, LocationEnum locationEnum) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                Vector vector = new Vector();
                vector.setY(0.3);
                player.setVelocity(vector);
                player.teleport(locationEnum.getLocation());
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
            }
        }, 2);
    }

}
