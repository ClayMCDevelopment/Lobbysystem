package eu.claymc.lobbysystem.items;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.enums.ItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NavigatorItem implements Listener {

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if(event.getItem() == null) return;
        if(event.getItem().getItemMeta() == null) return;
        if(event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equals(ItemEnum.NAVIGATOR.getItemStack().getItemMeta().getDisplayName())) {

            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                final Inventory inventory = Bukkit.createInventory(null, 9 * 6, ItemEnum.NAVIGATOR.getInventoryName());

                final ItemStack stained_glass_paine = new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack();

                for(int i = 0; i < 54; i++) {
                    inventory.setItem(i, stained_glass_paine);
                }

                inventory.setItem(12, new ItemBuilder(Material.GOLD_NUGGET).setDisplayName("§6•§e● Shop").toItemStack());
                inventory.setItem(14, new ItemBuilder(Material.GHAST_TEAR).setDisplayName("§6•§e● Belohnungen").toItemStack());
                inventory.setItem(18, new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.RED).setDisplayName("§4•§c● TTT").toItemStack());
                inventory.setItem(26, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§5•§d● KitPVP").toItemStack());
                inventory.setItem(28, new ItemBuilder(Material.IRON_BOOTS).setDisplayName("§3•§b● ClayJump").toItemStack());
                inventory.setItem(34, new ItemBuilder(Material.GOLDEN_APPLE).setDisplayName("§2•§a● UHC").toItemStack());
                inventory.setItem(49, new ItemBuilder(Material.CLAY_BALL).setDisplayName("§6•§e● Spawn").toItemStack());

                player.openInventory(inventory);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

            }

        }

    }

}
