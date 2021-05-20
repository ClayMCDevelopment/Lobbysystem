package eu.claymc.lobbysystem.scheduler;

import eu.claymc.lobbysystem.Lobbysystem;
import eu.claymc.lobbysystem.enums.LocationEnum;
import eu.thesimplecloud.api.CloudAPI;
import org.bukkit.Bukkit;

public class ParticleScheduler implements Runnable {

    @Override
    public void run() {
        /*
        ClayAPI.getInstance().bukkit().getRunTasks().runDelayAsync(() -> ClayAPI.getInstance().getClayerPool().getBukkiClayerPool().forEach((uuid, clayer) -> {
            Lobbysystem.getInstance().getParticleManager().spawnParticle(clayer.getPlayer(), LocationEnum.SPAWN.getLocation());
        }), 2);
         */
    }

}
