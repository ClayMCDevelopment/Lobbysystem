package eu.claymc.lobbysystem.items;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.enums.ItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ProfileItem implements Listener {

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemEnum.PROFILE_HEAD.getItemStack().getItemMeta().getDisplayName())) {

            final Inventory inventory = Bukkit.createInventory(null, 9 * 5, "§6•§e● Profil");
            final ItemStack stained_glass_paine = new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack();

            for (int i = 0; i < 45; i++) {
                inventory.setItem(i, stained_glass_paine);
            }

            inventory.setItem(4, new ItemBuilder(Material.SKULL_ITEM).setDisplayName("§6•§e● " + player.getName()).toItemStack());
            inventory.setItem(19, new ItemBuilder(Material.GOLDEN_CARROT).setLore("§8§m------------------------",
                    "§8•§7● Verwalte deine Freunde", "§8§m------------------------").setDisplayName("§6•§e● Freunde").toItemStack());
            inventory.setItem(22, new ItemBuilder(Material.ARMOR_STAND).setLore("§8§m------------------------",
                    "§8•§7● ", "§8§m------------------------").setDisplayName("§6•§e● Stats").toItemStack());
            inventory.setItem(25, new ItemBuilder(Material.GOLD_HELMET).setLore("§8§m------------------------",
                    "§8•§7● Verwalte deinen Clan", "§8§m------------------------").setDisplayName("§6•§e● Clan").toItemStack());
            inventory.setItem(29, new ItemBuilder(Material.NETHER_STAR).setLore("§8§m------------------------",
                    "§8•§7● Verwalte deine Party", "§8§m------------------------").setDisplayName("§6•§e● Party").toItemStack());
            inventory.setItem(33, new ItemBuilder(Material.BARRIER).setDisplayName("§4•§c● Kommt bald...").toItemStack());

            player.openInventory(inventory);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

        }

    }

}