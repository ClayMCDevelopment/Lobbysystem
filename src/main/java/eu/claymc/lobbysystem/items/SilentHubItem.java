package eu.claymc.lobbysystem.items;

import eu.claymc.api.ClayAPI;
import eu.claymc.lobbysystem.enums.ItemEnum;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.service.ICloudService;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SilentHubItem implements Listener {

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();
        final ICloudPlayer cloudPlayer = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());
        final IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
        final ICloudService iCloudService = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("SilentHub-1");

        if (event.getItem().getItemMeta().getDisplayName().equals(ItemEnum.SILENT_HUB.getItemStack().getItemMeta().getDisplayName())) {

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if (player.isSneaking()) {

                    cloudPlayer.connect(iCloudService);
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du bist nun auf der SilentHub!");


                }

            }

        }

    }

}