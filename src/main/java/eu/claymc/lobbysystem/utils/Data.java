package eu.claymc.lobbysystem.utils;

import com.avaje.ebean.text.json.JsonElementBoolean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.claymc.api.ClayAPI;
import eu.claymc.api.builder.ItemBuilder;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.LocationEnum;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
import java.util.List;
import java.util.UUID;

public class Data {

    static double i = 0;

    public ArrayList<Player> hide = new ArrayList<>();

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

        ArmorStand armorStand = LocationEnum.NPC_SHOP.getLocation().getWorld().spawn(LocationEnum.NPC_SHOP.getLocation(), ArmorStand.class);
        armorStand.setBasePlate(false);
        armorStand.setHelmet(new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/c172d0a0d6969216b7f0b2f99adb409945c5de9b0831ff5ef064ba5f3835e696")).toItemStack());
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomName("§6•§e● Shop");
        armorStand.setCustomNameVisible(true);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (i == 360) i = 0;
                if (i < 360) {
                    i += 1;
                    armorStand.setHeadPose(new EulerAngle(0, Math.toRadians(i), 0));
                }
            }
        }, 1, 1);

    }

    public void sendServerBanner(Player player, String imageUrl) {
        JsonObject object = new JsonObject();
        object.addProperty("url", imageUrl); // Url of the image
        LabyModProtocol.sendLabyModMessage(player, "server_banner", object);
    }

    public void setSubtitle(Player receiver, UUID subtitlePlayer, String value ) {
        // List of all subtitles
        JsonArray array = new JsonArray();

        // Add subtitle
        JsonObject subtitle = new JsonObject();
        subtitle.addProperty( "uuid", subtitlePlayer.toString() );

        // Optional: Size of the subtitle
        subtitle.addProperty( "size", 0.8d ); // Range is 0.8 - 1.6 (1.6 is Minecraft default)

        // no value = remove the subtitle
        if(value != null)
            subtitle.addProperty( "value", value );

        // If you want to use the new text format in 1.16+
        // subtitle.add("raw_json_text", textObject );

        // You can set multiple subtitles in one packet
        array.add(subtitle);

        // Send to LabyMod using the API
        LabyModProtocol.sendLabyModMessage( receiver, "account_subtitle", array );
    }


    public void sendCurrentPlayingGamemode( Player player, boolean visible, String gamemodeName ) {
        JsonObject object = new JsonObject();
        object.addProperty( "show_gamemode", visible ); // Gamemode visible for everyone
        object.addProperty( "gamemode_name", gamemodeName ); // Name of the current playing gamemode

        // Send to LabyMod using the API
        LabyModProtocol.sendLabyModMessage( player, "server_gamemode", object );
    }

    public void sendCineScope( Player player, int coveragePercent, long duration ) {
        JsonObject object = new JsonObject();

        // Cinescope height (0% - 50%)
        object.addProperty( "coverage", coveragePercent );

        // Duration
        object.addProperty( "duration", duration );

        // Send to LabyMod using the API
        LabyModProtocol.sendLabyModMessage( player, "cinescopes", object );
    }

    public void playCinematic( Player player, List<Location> points, long duration ) {
        JsonObject cinematic = new JsonObject();

        // Add points
        JsonArray punkte = new JsonArray();
        for (Location location : points ) {
            // Add points
            JsonObject point = new JsonObject();
            point.addProperty( "x", location.getX() );
            point.addProperty( "y", location.getY() );
            point.addProperty( "z", location.getZ() );
            point.addProperty( "yaw", location.getYaw() );
            point.addProperty( "pitch", location.getPitch() );
            point.addProperty( "tilt", 0 );
            punkte.add(point);
        }
        cinematic.add( "points", punkte );

        /*
        // Clear the Minecraft Chat
        cinematic.add("clear_chat", true);
         */

        // Cinematic duration in ms
        cinematic.addProperty( "duration", duration );

        // Always teleport the player to the start point
        player.teleport( points.get(0) );

        // The player needs to fly for the cinematic
        player.setAllowFlight( true );

        // Play cinematic
        LabyModProtocol.sendLabyModMessage( player, "cinematic", cinematic );
    }

    public void cancelCinematic( Player player ) {
        // Cancel currently playing cinematic
        LabyModProtocol.sendLabyModMessage( player, "cinematic", new JsonObject() ); // Empty object
    }

}
