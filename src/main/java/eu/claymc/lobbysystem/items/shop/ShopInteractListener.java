package eu.claymc.lobbysystem.items.shop;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class ShopInteractListener implements Listener {

    @EventHandler
    public void handle(final PlayerInteractAtEntityEvent event) {
        final Player player = event.getPlayer();
        final IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());

        if(event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {

            if(event.getRightClicked().getName().equals("§6•§e● Shop")) {

                final Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§6•§e● Shop");
                Lobbysystem.getInstance().getData().setPanes(inventory, 9 * 3);
                inventory.setItem(13, new ItemBuilder(Material.PAPER).setDisplayName("§2•§a● ClayPass").toItemStack());
                player.openInventory(inventory);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

            }

        }

    }

}
