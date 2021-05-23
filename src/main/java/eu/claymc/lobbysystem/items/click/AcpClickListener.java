package eu.claymc.lobbysystem.items.click;

import eu.claymc.api.ClayAPI;
import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.servicegroup.grouptype.ICloudProxyGroup;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class AcpClickListener implements Listener {

    @EventHandler
    public void handle(final InventoryClickEvent event) {

        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        final Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equals(ItemEnum.ADMIN_GUI.getInventoryName())) {

            switch (event.getSlot()) {

                case 11:
                    Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§6•§e● Servers");
                    Lobbysystem.getInstance().getData().setPanes(inventory, 9 * 3);
                    inventory.setItem(10, new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.RED).setDisplayName("§6•§e● TTT").toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.IRON_BOOTS).setDisplayName("§6•§e● ClayJump").toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§6•§e● KitPVP").toItemStack());
                    inventory.setItem(16, new ItemBuilder(Material.GOLDEN_APPLE).setDisplayName("§6•§e● UHC").toItemStack());
                    player.openInventory(inventory);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;
                case 15:
                    Inventory inventory1 = Bukkit.createInventory(null, 9 * 3, "§6•§e● Proxy-Settings");
                    Lobbysystem.getInstance().getData().setPanes(inventory1, 9 * 3);
                    inventory1.setItem(11, new ItemBuilder(Material.BARRIER).setDisplayName("§6•§e● Wartungsarbeiten").toItemStack());
                    inventory1.setItem(15, new ItemBuilder(Material.GOLD_PICKAXE).setDisplayName("§6•§e● Spielerzahl ändern").toItemStack());
                    player.openInventory(inventory1);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;

            }

        } else if(event.getInventory().getName().equalsIgnoreCase("§6•§e● Proxy-Settings")) {

            switch (event.getSlot()) {
                case 11:
                    final Inventory inventory1 = Bukkit.createInventory(null, 9 * 3, "§6•§e● Wartungsarbeiten");
                    Lobbysystem.getInstance().getData().setPanes(inventory1, 9 * 3);

                    inventory1.setItem(11, new ItemBuilder(Material.INK_SACK).setDurability((short) 10).setDisplayName("§6•§e● An").toItemStack());
                    inventory1.setItem(15, new ItemBuilder(Material.INK_SACK).setDurability((short) 1).setDisplayName("§6•§e● Aus").toItemStack());

                    player.openInventory(inventory1);
                    break;
                case 15:
                    final Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§6•§e● Spielerzahl ändern");
                    Lobbysystem.getInstance().getData().setPanes(inventory, 9 * 3);

                    inventory.setItem(10, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("§6•§e● 25").toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("§6•§e● 50").toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("§6•§e● 75").toItemStack());
                    inventory.setItem(16, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("§6•§e● 100").toItemStack());

                    player.openInventory(inventory);
                    break;
            }

        } else if(event.getInventory().getName().equalsIgnoreCase("§6•§e● Wartungsarbeiten")) {

            final ICloudProxyGroup iCloudProxyGroup = CloudAPI.getInstance().getCloudServiceGroupManager().getProxyGroupByName("Proxy");

            switch (event.getSlot()) {
                case 11:
                    iCloudProxyGroup.setMaintenance(true);
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du hast die Wartungsarbeiten erfolgreich geändert!");
                    break;
                case 15:
                    iCloudProxyGroup.setMaintenance(false);
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du hast die Wartungsarbeiten erfolgreich geändert!");
                    break;
            }

        } else if(event.getInventory().getName().equalsIgnoreCase("§6•§e● Spielerzahl ändern")) {

            final ICloudProxyGroup iCloudProxyGroup = CloudAPI.getInstance().getCloudServiceGroupManager().getProxyGroupByName("Proxy");

            switch (event.getSlot()) {
                case 10:
                    iCloudProxyGroup.setMaxPlayers(25);
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du hast die Spielerzahl erfolgreich geändert!");
                    break;
                case 12:
                    iCloudProxyGroup.setMaxPlayers(50);
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du hast die Spielerzahl erfolgreich geändert!");
                    break;
                case 14:
                    iCloudProxyGroup.setMaxPlayers(75);
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du hast die Spielerzahl erfolgreich geändert!");
                    break;
                case 16:
                    iCloudProxyGroup.setMaxPlayers(100);
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du hast die Spielerzahl erfolgreich geändert!");
                    break;
            }

        }

    }

}
