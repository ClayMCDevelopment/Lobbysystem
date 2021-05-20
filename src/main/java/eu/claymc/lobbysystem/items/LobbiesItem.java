package eu.claymc.lobbysystem.items;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.enums.ItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class LobbiesItem implements Listener {

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemEnum.LOBBY_SWITCHER.getItemStack().getItemMeta().getDisplayName())) {

            final Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§6•§e● Lobbies");

            inventory.setItem(0, new ItemBuilder(Material.EXP_BOTTLE).setDisplayName("§6•§e● Premiumlobby-1").toItemStack());
            inventory.setItem(1, new ItemBuilder(Material.POTION).setDisplayName("§6•§e● Lobby-1").toItemStack());
            inventory.setItem(2, new ItemBuilder(Material.POTION).setDisplayName("§6•§e● Lobby-2").toItemStack());
            inventory.setItem(3, new ItemBuilder(Material.POTION).setDisplayName("§6•§e● Lobby-3").toItemStack());
            inventory.setItem(4, new ItemBuilder(Material.POTION).setDisplayName("§6•§e● Lobby-4").toItemStack());

            player.openInventory(inventory);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

        }

    }

}
