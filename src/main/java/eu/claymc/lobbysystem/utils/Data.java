package eu.claymc.lobbysystem.utils;

import eu.claymc.api.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Data {

    public ArrayList<Player> hide = new ArrayList<>();

    /*
    public static void sendActionbar(Player player, String message) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chat, (byte) 2);
        connection.sendPacket(packetPlayOutChat);
    }
     */

    public void setPanes(Inventory inventory, int size) {

        final ItemStack stained_glass_paine = new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName(" ").setDurability((short) 7).toItemStack();

        for(int i = 0; i < size; i++) {

            inventory.setItem(i, stained_glass_paine);

        }

    }

}
