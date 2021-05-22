package eu.claymc.lobbysystem.manager;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class ParticleManager {

    final Random random = new Random();

    public void spawnParticle(final Location location) {
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * 1);
            particleLoc.setZ(location.getZ() + Math.sin(d) * 1);
            Bukkit.getWorld("world").playEffect(particleLoc, Effect.SPELL, 1);
        }
        Bukkit.getWorld("world").playEffect(location, Effect.ENDER_SIGNAL, 1);
    }

    public void spawnParticle(final Player player, final Location location, Effect effect1, Effect effect2) {
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * 1);
            particleLoc.setZ(location.getZ() + Math.sin(d) * 1);
            player.playEffect(particleLoc, effect1, 1);
        }
        player.playEffect(location, effect2, 1);
    }

    public void spawnParticle(final Player player, final Location location) {
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * 1);
            particleLoc.setZ(location.getZ() + Math.sin(d) * 1);
            player.playEffect(particleLoc, Effect.SPELL, 1);
        }
        player.playEffect(location, Effect.ENDER_SIGNAL, 1);
    }

}
