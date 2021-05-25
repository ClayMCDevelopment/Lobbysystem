package eu.claymc.lobbysystem.items.shop;

import eu.claymc.api.ClayAPI;
import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ShopClickListener implements Listener {

    @EventHandler
    public void handle(final InventoryClickEvent event) {

        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        final Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equals("§6•§e● Shop")) {

            switch (event.getSlot()) {

                case 13:
                    final Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§2•§a● ClayPass");
                    Lobbysystem.getInstance().getData().setPanes(inventory, 9 * 3);
                    inventory.setItem(15, new ItemBuilder(Material.INK_SACK).setDurability((short) 10).setDisplayName("§2•§a● Kaufen").toItemStack());
                    inventory.setItem(11, new ItemBuilder(Material.INK_SACK).setDurability((short) 1).setDisplayName("§4•§c● Abbrechen").toItemStack());
                    player.openInventory(inventory);
                    break;

            }

        } else if(event.getInventory().getName().equals("§2•§a● ClayPass")) {

            switch (event.getSlot()) {

                case 15:
                    if(Lobbysystem.getInstance().getShopSQL().getClayShop(player.getUniqueId().toString()) == 0) {
                        if(ClayAPI.getInstance().getClaysSQL().getClays(player) >= 2500) {
                            Lobbysystem.getInstance().getShopSQL().addClayPass(player.getUniqueId().toString());
                            ClayAPI.getInstance().getClaysSQL().removeClays(player, 2500);
                            player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du hast dir erfolgreich den ClayPass gekauft!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du besitzt zu wenig Clays!");
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                        }
                    } else {
                        player.closeInventory();
                        player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du besitzt den ClayPass bereits!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case 11:
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    break;

            }

        }

    }

}
