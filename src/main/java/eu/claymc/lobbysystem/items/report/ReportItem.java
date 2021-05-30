package eu.claymc.lobbysystem.items.report;

import eu.claymc.api.ClayAPI;
import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.claymc.lobbysystem.items.report.sql.ReportSQL;
import eu.claymc.lobbysystem.utils.Base64;
import eu.claymc.lobbysystem.utils.SkullBuilder;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportItem implements Listener {

    public HashMap<Player, Integer> pages = new HashMap<>();
    HashMap<Player, String> selectedUser = new HashMap<>();

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();
        final IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());

        if(event.getItem().getItemMeta().getDisplayName().equals(ItemEnum.REPORT_GUI.getItemStack().getItemMeta().getDisplayName())) {

            if (player.isSneaking()) {

                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                    buildInventory(player);

                }

            }

        }

    }

    public void buildInventory(Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 36, ItemEnum.REPORT_GUI.getInventoryName());
        ArrayList<ItemStack> skulls = new ArrayList<>();

        for(int i = 0; i < Lobbysystem.getInstance().getReportSQL().getReportedPlayers().size(); i++) {
            skulls.add(new SkullBuilder("§6•§e●" + Lobbysystem.getInstance().getReportSQL().getReportedPlayers().get(i),
                    Lobbysystem.getInstance().getReportSQL().getReportedPlayers().get(i), 1,
                    "§6•§e● Grund: §7" + Lobbysystem.getInstance().getReportSQL().getReportedPlayers().get(i),
                    "§6•§e● Von: §7" + Lobbysystem.getInstance().getReportSQL().getReportedPlayers().get(i), "").buildSkull());
        }

        /*
        int page = (this.pages.get(player)).intValue();
        int entriesPerPage = 18;
        int startIndex = (page - 1) * entriesPerPage;
        int endIndex = startIndex + entriesPerPage;
        if (endIndex > skulls.size())
            endIndex = skulls.size();
        for (ItemStack is : skulls.subList(startIndex, endIndex)) {
            inventory.addItem(new ItemStack[] { is });
        }
         */
        for (int j = 18; j < 27; j++)
            inventory.setItem(j, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 7).setDisplayName(" ").toItemStack());
        inventory.setItem(35, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/9c9ec71c1068ec6e03d2c9287f9da9193639f3a635e2fbd5d87c2fabe6499")).setDisplayName("§6•§e● Nächste Seite").toItemStack());
        inventory.setItem(34, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/816ea34a6a6ec5c051e6932f1c471b7012b298d38d179f1b487c413f51959cd4")).setDisplayName("§6•§e● Vorherige Seite").toItemStack());

        player.openInventory(inventory);

    }

    @EventHandler
    public void handle(final InventoryClickEvent event) {

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        final Player player = (Player) event.getWhoClicked();
        final ICloudPlayer cloudPlayer = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());

        if(event.getInventory().getName().equals(ItemEnum.REPORT_GUI.getInventoryName())) {

            if(event.getCurrentItem().getType() == Material.SKULL_ITEM) {

                buildPlayerManager(player, event.getCurrentItem().getItemMeta().getDisplayName().replace("§6•§e● ", ""));

            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6•§e● Vorherige Seite")) {

                if(((Integer) this.pages.get(player)).intValue() != 1) {
                    this.pages.put(player, Integer.valueOf((this.pages.get(player)).intValue() - 1));
                    buildInventory(player);
                }

            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6•§e● Nächste Seite")) {

                this.pages.put(player, Integer.valueOf((this.pages.get(player)).intValue() + 1));
                buildInventory(player);

            }

        } else if(event.getInventory().getName().equals("§6•§e● " + this.selectedUser.get(player))) {



        }

    }

    public void buildPlayerManager(Player player, String requestedPlayer) {
        this.selectedUser.put(player, requestedPlayer);
        final Inventory inventory = Bukkit.createInventory(null, 27, "§6•§e● " + requestedPlayer);

        inventory.setItem(11, new ItemBuilder(Material.ENDER_PEARL).setDisplayName("§6•§e● Nachspringen").toItemStack());
        inventory.setItem(13, new SkullBuilder("§6•§e● " + this.selectedUser.get(player), this.selectedUser.get(player), 1, "§6•§e● Grund: §7" + Lobbysystem.getInstance().getReportSQL().getReason(this.selectedUser.get(player)), "§6•§e● Von: §7" + Lobbysystem.getInstance().getReportSQL().getFrom(this.selectedUser.get(player)), "").buildSkull());
        inventory.setItem(15, new ItemBuilder(Material.BARRIER).setDisplayName("§6•§e● Report löschen").toItemStack());

        player.openInventory(inventory);
    }

}
