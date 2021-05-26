package eu.claymc.lobbysystem.items.click;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.claymc.lobbysystem.utils.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ProfileClickListener implements Listener {

    @EventHandler
    public void handle(final InventoryClickEvent event) {

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        final Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equals(ItemEnum.PROFILE_HEAD.getInventoryName())) {

            switch (event.getSlot()) {

                case 19:
                    final Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§6•§e● Freunde");

                    inventory.setItem(53, new ItemBuilder(Material.COMMAND).setDisplayName("§6•§e● Einstellungen").toItemStack());
                    inventory.setItem(52, new ItemBuilder(Material.BARRIER).setDisplayName("§6•§e● Alle Freunde löschen").toItemStack());
                    inventory.setItem(49, new ItemBuilder(Material.FIREWORK).setDisplayName("§6•§e● Freunde nachspringen").toItemStack());
                    inventory.setItem(46, new ItemBuilder(Material.GOLD_HELMET).setDisplayName("§6•§e● Beste Freunde").toItemStack());
                    inventory.setItem(45, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/816ea34a6a6ec5c051e6932f1c471b7012b298d38d179f1b487c413f51959cd4")).setDisplayName("§6•§e● Zurück").toItemStack());

                    inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());

                    player.openInventory(inventory);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;

                case 25:
                    final Inventory inventory1 = Bukkit.createInventory(null, 9 * 6, "§6•§e● Clan");

                    inventory1.setItem(53, new ItemBuilder(Material.COMMAND).setDisplayName("§6•§e● Einstellungen").toItemStack());
                    inventory1.setItem(52, new ItemBuilder(Material.BARRIER).setDisplayName("§6•§e● Clan verlassen").toItemStack());
                    inventory1.setItem(45, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/816ea34a6a6ec5c051e6932f1c471b7012b298d38d179f1b487c413f51959cd4")).setDisplayName("§6•§e● Zurück").toItemStack());
                    inventory1.setItem(8, new ItemBuilder(Material.DIAMOND_HELMET).setDisplayName("§6•§e● Clan-Leader").toItemStack());
                    inventory1.setItem(17, new ItemBuilder(Material.IRON_HELMET).setDisplayName("§6•§e● Clan-Moderatoren").toItemStack());
                    inventory1.setItem(26, new ItemBuilder(Material.GOLD_HELMET).setDisplayName("§6•§e● Clan-Mitglieder").toItemStack());
                    inventory1.setItem(35, new ItemBuilder(Material.GOLD_HELMET).setDisplayName("§6•§e● Clan-Mitglieder").toItemStack());

                    inventory1.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory1.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());

                    player.openInventory(inventory1);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;

                case 29:
                    final Inventory inventory2 = Bukkit.createInventory(null, 9, "§6•§e● Party");

                    inventory2.setItem(4, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3edd20be93520949e6ce789dc4f43efaeb28c717ee6bfcbbe02780142f716")).setDisplayName("§6•§e● Party erstellen").toItemStack());

                    player.openInventory(inventory2);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;

                case 33:
                    final Inventory inventory3 = Bukkit.createInventory(null, 9 * 6, "§6•§e● Herausforderungen");

                    inventory3.setItem(53, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/9c9ec71c1068ec6e03d2c9287f9da9193639f3a635e2fbd5d87c2fabe6499")).setDisplayName("§6•§e● Nächste Seite").toItemStack());
                    inventory3.setItem(52, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/816ea34a6a6ec5c051e6932f1c471b7012b298d38d179f1b487c413f51959cd4")).setDisplayName("§6•§e● Vorherige Seite").toItemStack());
                    inventory3.setItem(45, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/816ea34a6a6ec5c051e6932f1c471b7012b298d38d179f1b487c413f51959cd4")).setDisplayName("§6•§e● Zurück").toItemStack());
                    inventory3.setItem(49, new ItemBuilder(Material.BARRIER).setDisplayName("§6•§e● Herausforderungen zurücksetzen").toItemStack());

                    inventory3.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());
                    inventory3.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack());

                    player.openInventory(inventory3);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;

            }

        }

    }

}
