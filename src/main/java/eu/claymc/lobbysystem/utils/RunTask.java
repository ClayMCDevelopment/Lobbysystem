package eu.claymc.lobbysystem.utils;

import eu.claymc.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;

public class RunTask {

    public RunTask() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobbysystem.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(players -> {
            players.setLevel(Bukkit.getOnlinePlayers().size());
            players.setExp((float) Bukkit.getOnlinePlayers().size() / (float) Bukkit.getMaxPlayers());
        }), 20, 20);

    }

}
