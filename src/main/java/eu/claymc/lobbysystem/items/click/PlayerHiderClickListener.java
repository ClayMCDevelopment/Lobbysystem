package eu.claymc.lobbysystem.items.click;

import eu.claymc.api.ClayAPI;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.ItemEnum;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerHiderClickListener implements Listener {

    @EventHandler
    public void handle(final InventoryClickEvent event) {

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        final Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals(ItemEnum.PLAYER_HIDER.getInventoryName())) {

            switch (event.getSlot()) {

                case 0:
                    hide(player, 1);
                    player.closeInventory();
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du siehst nun alle Spieler!");
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;
                case 2:
                    hide(player, 2);
                    player.closeInventory();
                    player.sendMessage(ClayAPI.getInstance().getPREFIX() + "Du siehst nun keine Spieler!");
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    break;

            }

        }

    }

    public void hide(Player player, int value) {

        switch (value) {

            case 1:
                for (Player all : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(all);
                    player.hidePlayer(Lobbysystem.getInstance().getData().hide.get(0).getPlayer());
                }
                break;
            case 2:
                break;
            case 3:
                for (Player all : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(all);
                    player.showPlayer(Lobbysystem.getInstance().getData().hide.get(0).getPlayer());
                }
                break;

        }

    }

}
