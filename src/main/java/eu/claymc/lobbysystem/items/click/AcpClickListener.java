package eu.claymc.lobbysystem.items.click;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.claymc.lobbysystem.enums.LocationEnum;
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
                    inventory1.setItem(11, new ItemBuilder(Material.SIGN).setDisplayName("§6•§e● MOTD ändern").toItemStack());
                    inventory1.setItem(15, new ItemBuilder(Material.GOLD_PICKAXE).setDisplayName("§6•§e● Spielerzahl ändern").toItemStack());
                    player.openInventory(inventory1);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;

            }

        }

    }

}
