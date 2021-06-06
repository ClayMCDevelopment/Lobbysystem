package eu.claymc.lobbysystem.listener;

import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void handle(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());

        if(permissionPlayer.hasPermissionGroup("Owner")) {
            event.setFormat("§4§lOwner §8● §4" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Admin")) {
            event.setFormat("§4Admin §8● §4" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("SrDeveloper")) {
            event.setFormat("§bSrDeveloper §8● §b" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Developer")) {
            event.setFormat("§bDeveloper §8● §b" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("SrModerator")) {
            event.setFormat("§9SrModerator §8● §9" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Moderator")) {
            event.setFormat("§9Moderator §8● §9" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Content")) {
            event.setFormat("§bContent §8● §b" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Supporter")) {
            event.setFormat("§eSupporter §8● §e" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("SrBuilder")) {
            event.setFormat("§2SrBuilder §8● §2" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Builder")) {
            event.setFormat("§2Builder §8● §2" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("YouTuber")) {
            event.setFormat("§5YouTuber §8● §5" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Legende")) {
            event.setFormat("§dLegend §8● §d" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Champ")) {
            event.setFormat("§cChamp §8● §c" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Cast")) {
            event.setFormat("§3Cast §8● §3" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("Vibe")) {
            event.setFormat("§6Vibe §8● §6" + player.getName() + " §8» §7" + event.getMessage());
        } else if(permissionPlayer.hasPermissionGroup("default")) {
            event.setFormat("§eClayer §8● §e" + player.getName() + " §8» §7" + event.getMessage());
        }


    }

}
