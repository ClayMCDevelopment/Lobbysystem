package eu.claymc.lobbysystem.items;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.thesimplecloud.api.CloudAPI;
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

public class LobbiesItem implements Listener {

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();

        if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemEnum.LOBBY_SWITCHER.getItemStack().getItemMeta().getDisplayName())) {

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                final Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§6•§e● Lobbies");

                CloudAPI.getInstance().getCloudServiceManager().getCloudServicesInLobbyStateByGroupName("Lobby").forEach(iCloudService -> {
                    inventory.addItem(new ItemBuilder(Material.POTION).setDisplayName("§6•§e● " + iCloudService.getName()).toItemStack());
                });

                player.openInventory(inventory);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

            }

        }

    }

}
