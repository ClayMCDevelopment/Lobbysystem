package eu.claymc.lobbysystem.enums;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum LocationEnum {

    SPAWN(new Location(Bukkit.getWorld("world"), 7.4, 71, 73.4, 134.1f, 0.0f)),
    KITPVP(new Location(Bukkit.getWorld("world"), 7.4, 71, 73.4, 134.1f, 0.0f)),
    UHC(new Location(Bukkit.getWorld("world"), 7.4, 71, 73.4, 134.1f, 0.0f)),
    CLAYJUMP(new Location(Bukkit.getWorld("world"), 7.4, 71, 73.4, 134.1f, 0.0f)),
    TTT(new Location(Bukkit.getWorld("world"), -66.5, 56, 16.4, 90.2f, 0.0f)),
    NPC_SHOP(new Location(Bukkit.getWorld("world"), -57.5, 51, 44.5)),
    SHOP(new Location(Bukkit.getWorld("world"), -53.5, 50, 48.5, 134.5f, -7.8f))
    ;

    LocationEnum(Location location) {
        this.location = location;
    }

    private final Location location;

    public Location getLocation() {
        return location;
    }

}
