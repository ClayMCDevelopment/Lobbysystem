package eu.claymc.lobbysystem.utils;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutBed;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity.PacketPlayOutEntityLook;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;

public class NPC implements Serializable {

    private static final long serialVersionUID = -672963264444438574L;
    private int entityID;
    private Location location;
    private GameProfile gameprofile;
    private Float health = 20F;

    public NPC(String name, Location location) {
        entityID = (int) Math.ceil(Math.random() * 1000) + 2000;
        gameprofile = new GameProfile(UUID.randomUUID(), name);
        this.location = location.clone();
    }

    public NPC(String name, Integer entityID, UUID uuid, Location location) {
        this.entityID = entityID;
        gameprofile = new GameProfile(uuid, name);
        this.location = location.clone();
    }

    public int getEntityID() {
        return entityID;
    }

    public String getName() {
        return gameprofile.getName();
    }

    public Float getHealth() {
        return health;
    }

    public void setHealth(Float health) {
        this.health = health;
    }

    public Location getLocation() {
        return location;
    }

    public void setSkin(String name) {
        Gson gson = new Gson();
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        String json = getStringFromURL(url);
        String uuid = gson.fromJson(json, JsonObject.class).get("id").getAsString();

        url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false";
        json = getStringFromURL(url);
        JsonObject mainObject = gson.fromJson(json, JsonObject.class);
        JsonObject jObject = mainObject.get("properties").getAsJsonArray().get(0).getAsJsonObject();
        String value = jObject.get("value").getAsString();
        String signatur = jObject.get("signature").getAsString();

        gameprofile.getProperties().put("textures", new Property("textures", value, signatur));
    }

    public void animation(int animation) {
        PacketPlayOutAnimation packet = new PacketPlayOutAnimation();
        setValue(packet, "a", entityID);
        setValue(packet, "b", (byte) animation);
        sendPacket(packet);
    }

    public void status(int status) {
        PacketPlayOutEntityStatus packet = new PacketPlayOutEntityStatus();
        setValue(packet, "a", entityID);
        setValue(packet, "b", (byte) status);
        sendPacket(packet);
    }

    public void equip(Slot slot, ItemStack itemstack) {
        PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment();
        setValue(packet, "a", entityID);
        setValue(packet, "b", slot.getSlot());
        setValue(packet, "c", itemstack);
        sendPacket(packet);
    }

    @SuppressWarnings("deprecation")
    public void sleep(boolean state) {
        if (state) {
            Location bedLocation = new Location(location.getWorld(), 1, 1, 1);
            PacketPlayOutBed packet = new PacketPlayOutBed();
            setValue(packet, "a", entityID);
            setValue(packet, "b", new BlockPosition(bedLocation.getX(), bedLocation.getY(), bedLocation.getZ()));

            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendBlockChange(bedLocation, Material.BED_BLOCK, (byte) 0);
            }

            sendPacket(packet);
            teleport(location.clone().add(0, 0.3, 0));
        } else {
            animation(2);
            teleport(location.clone().subtract(0, 0.3, 0));
        }
    }

    public void spawn() {
        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();
        setValue(packet, "a", entityID);
        setValue(packet, "b", gameprofile.getId());
        setValue(packet, "c", getFixLocation(location.getX()));
        setValue(packet, "d", getFixLocation(location.getY()));
        setValue(packet, "e", getFixLocation(location.getZ()));
        setValue(packet, "f", getFixRotation(location.getYaw()));
        setValue(packet, "g", getFixRotation(location.getPitch()));
        setValue(packet, "h", 0);
        DataWatcher w = new DataWatcher(null);
        w.a(6, health);
        addToTablist();
        w.a(10, (byte) 127);
        setValue(packet, "i", w);
        sendPacket(packet);
        headRotation(location.getYaw(), location.getPitch());
    }

    public void spawn(Player player) {
        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();
        setValue(packet, "a", entityID);
        setValue(packet, "b", gameprofile.getId());
        setValue(packet, "c", getFixLocation(location.getX()));
        setValue(packet, "d", getFixLocation(location.getY()));
        setValue(packet, "e", getFixLocation(location.getZ()));
        setValue(packet, "f", getFixRotation(location.getYaw()));
        setValue(packet, "g", getFixRotation(location.getPitch()));
        setValue(packet, "h", 0);
        DataWatcher w = new DataWatcher(null);
        w.a(6, health);
        addToTablist();
        w.a(10, (byte) 127);
        setValue(packet, "i", w);
        sendPacket(packet, player);
        headRotation(location.getYaw(), location.getPitch());
    }

    public void teleport(Location location) {
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport();
        setValue(packet, "a", entityID);
        setValue(packet, "b", getFixLocation(location.getX()));
        setValue(packet, "c", getFixLocation(location.getY()));
        setValue(packet, "d", getFixLocation(location.getZ()));
        setValue(packet, "e", getFixRotation(location.getYaw()));
        setValue(packet, "f", getFixRotation(location.getPitch()));

        sendPacket(packet);
        headRotation(location.getYaw(), location.getPitch());
        this.location = location.clone();
    }

    public void headRotation(float yaw, float pitch) {
        PacketPlayOutEntityLook packet = new PacketPlayOutEntityLook(entityID, getFixRotation(yaw), getFixRotation(pitch), true);
        PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();
        setValue(packetHead, "a", entityID);
        setValue(packetHead, "b", getFixRotation(yaw));

        sendPacket(packet);
        sendPacket(packetHead);

        this.location.setYaw(yaw);
        this.location.setPitch(pitch);
    }

    public void headRotation(Location location) {
        headRotation(location.getYaw(), location.getPitch());
    }

    public void destroy() {
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { entityID });
        removeFromTablist();
        sendPacket(packet);
    }

    public void destroy(Player player) {
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { entityID });
        removeFromTablist();
        sendPacket(packet, player);
    }

    public void addToTablist() {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameprofile.getName())[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        players.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        setValue(packet, "b", players);
        sendPacket(packet);
    }

    public void removeFromTablist() {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameprofile.getName())[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        players.add(data);
        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet);
    }

    public void removeFromTablist(Player player) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameprofile.getName())[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        players.add(data);
        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet, player);
    }

    private int getFixLocation(double pos) {
        return (int) MathHelper.floor(pos * 32.0D);
    }

    private byte getFixRotation(float yawpitch) {
        return (byte) ((int) (yawpitch * 256.0F / 360.0F));
    }

    private void setValue(Object obj, String name, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {}
    }

    private Object getValue(Object obj, String name) {
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {}
        return null;
    }

    private void sendPacket(Packet<?> packet, Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    private void sendPacket(Packet<?> packet) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(packet, player);
        }
    }

    private String getStringFromURL(String url) {
        String text = "";
        try {
            Scanner scanner = new Scanner(new URL(url).openStream());
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                while (line.startsWith(" ")) {
                    line = line.substring(1);
                }
                text = text + line;
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public HashMap<String, Object> encode() {
        HashMap<String, Object> map = new HashMap<>();

        // LOC
        map.put("X", location.getX());
        map.put("Y", location.getY());
        map.put("Z", location.getZ());
        map.put("Pitch", location.getPitch());
        map.put("Yaw", location.getYaw());
        map.put("World", location.getWorld().getName());

        // DATA
        map.put("name", gameprofile.getName());
        map.put("entityID", entityID);
        map.put("UUID", gameprofile.getId());
        map.put("health", health);

        // GAMEPROFILE
        String value = "";
        String signatur = "";
        for (Property property : gameprofile.getProperties().get("textures")) {
            value = property.getValue();
            signatur = property.getSignature();
        }
        map.put("value", value);
        map.put("signatur", signatur);

        return map;
    }

    public static NPC decode(HashMap<String, Object> map) {
        String name = (String) map.get("name");
        Integer entityID = (Integer) map.get("entityID");
        UUID uuid = (UUID) map.get("UUID");

        World world = Bukkit.getWorld((String) map.get("World"));
        Double x = (Double) map.get("X");
        Double y = (Double) map.get("Y");
        Double z = (Double) map.get("Z");
        Float pitch = (Float) map.get("Pitch");
        Float yaw = (Float) map.get("Yaw");
        Location location = new Location(world, x, y, z, yaw, pitch);

        NPC npc = new NPC(name, entityID, uuid, location);
        npc.health = (Float) map.get("health");
        String value = (String) map.get("value");
        String signatur = (String) map.get("signatur");
        npc.gameprofile.getProperties().put("textures", new Property("textures", value, signatur));
        return npc;
    }

    public String toString() {
        return encode().toString();
    }

    public enum Slot {
        HAND(0), HELMET(4), CHESTPLATE(3), LEGGINGS(2), BOOTS(1);

        private Integer slot;

        private Slot(Integer slot) {
            this.slot = slot;
        }

        public Integer getSlot() {
            return slot;
        }
    }
}
