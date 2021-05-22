package eu.claymc.lobbysystem.listener;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.LocationEnum;
import eu.claymc.lobbysystem.manager.ItemManager;
import eu.claymc.lobbysystem.utils.Base64;
import eu.claymc.lobbysystem.utils.NPC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class ConnectionListener implements Listener {

    @EventHandler
    public void handleJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        event.setJoinMessage(null);
        player.teleport(LocationEnum.SPAWN.getLocation());
        ItemManager.setItems(player);

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.setLevel(2021);

        Lobbysystem.getInstance().getData().hide.add(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getData().sendActionbar(player, "§8•§7● Freunde §8➜ §e0 §8▎ §7Clan §8➜ §cKein Clan §7●§8•");
            }
        }.runTaskTimer(Lobbysystem.getInstance(), 0, 20);

        Lobbysystem.getInstance().getData().spawnArmorstands();

        Lobbysystem.getInstance().getScoreboardManager().setScoreboard(player);

    }

    @EventHandler
    public void handleQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Lobbysystem.getInstance().getData().getArmorStand().remove();

    }

}
