package eu.claymc.lobbysystem.items;

import eu.claymc.lobbysystem.Lobbysystem;
import org.bukkit.*;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class GrapplingHookItem implements Listener {

    @EventHandler
    public void handle(final PlayerFishEvent playerFishEvent) {
        final Player player = playerFishEvent.getPlayer();
        final Fish hook = playerFishEvent.getHook();

        if (player.getGameMode() == GameMode.SURVIVAL) {
            if (((playerFishEvent.getState().equals(PlayerFishEvent.State.IN_GROUND)) || (playerFishEvent.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) || (playerFishEvent.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT))) && (playerFishEvent.getPlayer().getWorld().getBlockAt(hook.getLocation().getBlockX(), hook.getLocation().getBlockY() - 1, hook.getLocation().getBlockZ()).getType() != Material.AIR)) {
                if (playerFishEvent.getPlayer().getWorld().getBlockAt(hook.getLocation().getBlockX(), hook.getLocation().getBlockY() - 1, hook.getLocation().getBlockZ()).getType() != Material.STATIONARY_WATER) {
                    playerFishEvent.setCancelled(false);
                    final Location playerLocation = player.getLocation();
                    final Location hookLocation = playerFishEvent.getHook().getLocation();

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        Lobbysystem.getInstance().getParticleManager().spawnParticle(all, hookLocation);
                    }

                    playerLocation.setY(playerLocation.getY() + 0.5D);
                    player.teleport(playerLocation);

                    double d = hookLocation.distance(playerLocation);

                    Vector v = player.getVelocity();
                    v.setX((1.0D + 0.07D * d) * (hookLocation.getX() - playerLocation.getX()) / d);
                    v.setY((1.0D + 0.03D * d) * (hookLocation.getY() - playerLocation.getY()) / d - 0.5D * -0.08D * d);
                    v.setZ((1.0D + 0.07D * d) * (hookLocation.getZ() - playerLocation.getZ()) / d);
                    player.setVelocity(v);
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 2.0F, 2.0F);
                }
            }
        }

    }

}
