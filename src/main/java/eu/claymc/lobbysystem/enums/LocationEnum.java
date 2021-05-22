package eu.claymc.lobbysystem.enums;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum LocationEnum {

    SPAWN(new Location(Bukkit.getWorld("world"), 7.4, 71, 73.4, 134.1f, 0.0f)),
    TTT(new Location(Bukkit.getWorld("world"), -66.5, 56, 16.4, 90.2f, 0.0f)),
    NPC(new Location(Bukkit.getWorld("world"), 7.5, 70.75, 65.5, 90, 0))
    ;

    LocationEnum(Location location) {
        this.location = location;
    }

    private final Location location;

    public Location getLocation() {
        return location;
    }

}
