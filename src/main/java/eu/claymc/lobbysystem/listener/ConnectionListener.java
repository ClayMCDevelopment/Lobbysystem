package eu.claymc.lobbysystem.listener;

import eu.claymc.lobbysystem.enums.LocationEnum;
import eu.claymc.lobbysystem.manager.ItemManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

    }

    @EventHandler
    public void handleQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

}
