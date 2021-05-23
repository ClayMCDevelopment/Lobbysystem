package eu.claymc.lobbysystem.items.click;

import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.service.ICloudService;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LobbiesClickListener implements Listener {

    @EventHandler
    public void handle(final InventoryClickEvent event) {

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        final Player player = (Player) event.getWhoClicked();
        final ICloudPlayer cloudPlayer = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());
        ICloudService iCloudService = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName(event.getCurrentItem().getItemMeta().getDisplayName().replace("•●", " "));

        if(event.getInventory().getName().equals(ItemEnum.LOBBY_SWITCHER.getInventoryName())) {

            if(event.getCurrentItem().getType() == Material.POTION) {

                cloudPlayer.connect(iCloudService);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

            }

        }

    }

}
