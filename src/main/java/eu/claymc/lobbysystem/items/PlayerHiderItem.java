package eu.claymc.lobbysystem.items;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.enums.ItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class PlayerHiderItem implements Listener {

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemEnum.PLAYER_HIDER.getItemStack().getItemMeta().getDisplayName())) {

            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                final Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§6•§e● Verstecker");

                inventory.setItem(0, new ItemBuilder(Material.INK_SACK).setDurability((short) 10).setDisplayName("§2•§a● Alle").toItemStack());
                inventory.setItem(1, new ItemBuilder(Material.INK_SACK).setDurability((short) 5).setDisplayName("§5•§d● VIP").toItemStack());
                inventory.setItem(2, new ItemBuilder(Material.INK_SACK).setDurability((short) 1).setDisplayName("§4•§c● Keiner").toItemStack());

                player.openInventory(inventory);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

            }

        }

    }

}
