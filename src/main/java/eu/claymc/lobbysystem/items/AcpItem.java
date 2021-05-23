package eu.claymc.lobbysystem.items;

import eu.claymc.api.ClayAPI;
import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AcpItem implements Listener {


    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();
        final IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());


        if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemEnum.ADMIN_GUI.getItemStack().getItemMeta().getDisplayName())) {

            if (player.isSneaking()) {

                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                    if (permissionPlayer.hasPermissionGroup("Owner") || permissionPlayer.hasPermissionGroup("Admin")) {

                        final Inventory inventory = Bukkit.createInventory(null, 9 * 3, ItemEnum.ADMIN_GUI.getInventoryName());
                        final ItemStack stained_glass_paine = new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack();

                        for (int i = 0; i < 27; i++) {
                            inventory.setItem(i, stained_glass_paine);
                        }

                        inventory.setItem(11, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("§6•§e● Servers").toItemStack());
                        inventory.setItem(15, new ItemBuilder(Material.REDSTONE_COMPARATOR).setDisplayName("§6•§e● Proxy-Settings").toItemStack());

                        player.openInventory(inventory);
                        player.playSound(player.getPlayer().getLocation(), Sound.LEVEL_UP, 10, 10);

                    } else
                        player.sendMessage(ClayAPI.getInstance().getPREFIX() + "§7Du kannst diese Funktion nicht nutzen!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                }

            }

        }

    }


}
