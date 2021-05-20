package eu.claymc.lobbysystem.manager;

import eu.claymc.lobbysystem.enums.ItemEnum;
import org.bukkit.entity.Player;

public class ItemManager {

    public static void setItems(Player player) {

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.getInventory().setItem(0, ItemEnum.NAVIGATOR.getItemStack());
        player.getInventory().setItem(1, ItemEnum.PLAYER_HIDER.getItemStack());
        player.getInventory().setItem(4, ItemEnum.GRAPPLING_HOOK.getItemStack());
        player.getInventory().setItem(7, ItemEnum.LOBBY_SWITCHER.getItemStack());
        player.getInventory().setItem(8, ItemEnum.PROFILE_HEAD.getItemStack());

    }

    public static void setSneekItems(Player player) {

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.getInventory().setItem(1, ItemEnum.REPORT_GUI.getItemStack());
        player.getInventory().setItem(4, ItemEnum.ADMIN_GUI.getItemStack());
        player.getInventory().setItem(7, ItemEnum.SILENT_HUB.getItemStack());

    }

}
