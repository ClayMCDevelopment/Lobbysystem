package eu.claymc.lobbysystem.utils;

import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.LocationEnum;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;

public class Data {

    static double i = 0;
    private ArmorStand armorStand;

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

        for (int i = 0; i < size; i++) {

            inventory.setItem(i, stained_glass_paine);

        }

    }

    public void sendActionbar(Player p, String msg) {
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chat, (byte) 2);
        connection.sendPacket(packetPlayOutChat);
    }

    public void spawnArmorstands() {

        armorStand = LocationEnum.NPC_SHOP.getLocation().getWorld().spawn(LocationEnum.NPC_SHOP.getLocation(), ArmorStand.class);
        armorStand.setBasePlate(false);
        armorStand.setHelmet(new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/c172d0a0d6969216b7f0b2f99adb409945c5de9b0831ff5ef064ba5f3835e696")).toItemStack());
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomName("§6•§e● Shop");
        armorStand.setCustomNameVisible(true);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (i == 360) i = 0;
                if (i < 360) {
                    i += 1;
                    armorStand.setHeadPose(new EulerAngle(0, Math.toRadians(i), 0));
                }

            }
        }.runTaskTimer(Lobbysystem.getInstance(), 0, 1L);

    }

}
