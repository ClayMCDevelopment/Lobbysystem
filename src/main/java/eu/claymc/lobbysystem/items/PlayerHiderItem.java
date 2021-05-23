package eu.claymc.lobbysystem.items;

import eu.claymc.api.ClayAPI;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.ItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerHiderItem implements Listener {

    @EventHandler
    public void handle(final PlayerInteractEvent event) {

        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;

        final Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemEnum.PLAYER_HIDER_NOT_HIDED.getItemStack().getItemMeta().getDisplayName())) {

                player.getInventory().setItem(1, ItemEnum.PLAYER_HIDER_HIDED.getItemStack());
                Lobbysystem.getInstance().getData().hide.add(player);
                Bukkit.getOnlinePlayers().forEach(player:: hidePlayer);
                player.closeInventory();
                player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du siehst nun keine Spieler!");
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

            } else if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemEnum.PLAYER_HIDER_HIDED.getItemStack().getItemMeta().getDisplayName())){

                player.getInventory().setItem(1, ItemEnum.PLAYER_HIDER_NOT_HIDED.getItemStack());
                Lobbysystem.getInstance().getData().hide.remove(player);
                Bukkit.getOnlinePlayers().forEach(player:: showPlayer);
                player.closeInventory();
                player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du siehst nun alle Spieler!");
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

            }

        }

    }

}
