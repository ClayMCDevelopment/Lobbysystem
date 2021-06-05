package eu.claymc.lobbysystem.manager;

import eu.claymc.api.ClayAPI;
import eu.claymc.api.clays.ClaysSQL;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.CloudPlayer;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.group.IPermissionGroup;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import kotlin.Unit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ScoreboardManager {

    private static HashMap<Scoreboard, Player> board = new HashMap<>();

    private static Integer animationCount;
    private final String[] animation = new String[]{
            "", "§6•", "§6•§e● ", "§6•§e● C", "§6•§e● Cl", "§6•§e● Cla", "§6•§e● Clay", "§6•§e● ClayM", "§6•§e● ClayMC",
            "§6•§e● ClayMC.", "§6•§e● ClayMC.e", "§6•§e● ClayMC.eu", "§6•§e● ClayMC.eu", "§6•§e● ClayMC.e", "§6•§e● ClayMC.",
            "§6•§e● ClayMC", "§6•§e● ClayMC §8▎", "§6•§e● ClayMC §8▎ §7L", "§6•§e● ClayMC §8▎ §7Lo", "§6•§e● ClayMC §8▎ §7Lob",
            "§6•§e● ClayMC §8▎ §7Lobb", "§6•§e● ClayMC §8▎ §7Lobby", "§6•§e● ClayMC §8▎ §7Lobby"
    };

    public void setScoreboard(final Player player) {
        final Scoreboard scoreboard = player.getScoreboard();
        final Objective objective = scoreboard.registerNewObjective("osne", "trew");
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
        ICloudPlayer cloudPlayer = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());

        String servername = cloudPlayer.getConnectedServer().getName();

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6•§e● ClayMC §8▎ §7Lobby");

        objective.getScore(" §8§m----------------").setScore(15);
        objective.getScore(" §8•§7● §7Server").setScore(14);
        objective.getScore(" §8➜ §e" + servername).setScore(13);
        objective.getScore("§1").setScore(12);
        objective.getScore(" §8•§7● §7Rang").setScore(11);

        if(permissionPlayer.hasPermissionGroup("Owner")) {
            objective.getScore(" §8➜ §4§lOwner").setScore(10);
        } else if(permissionPlayer.hasPermissionGroup("Admin")) {
            objective.getScore(" §8➜ §4Administrator").setScore(10);
        } else if(permissionPlayer.hasPermissionGroup("SrDeveloper")) {
            objective.getScore(" §8➜ §bSrDeveloper").setScore(10);
        } else if(permissionPlayer.hasPermissionGroup("Developer")) {
            objective.getScore(" §8➜ §bDeveloper").setScore(10);
        } else if(permissionPlayer.hasPermissionGroup("SrModerator")) {
            objective.getScore(" §8➜ §cSrModerator").setScore(10);
        } else if(permissionPlayer.hasPermissionGroup("Moderator")) {
            objective.getScore(" §8➜ §cModerator").setScore(10);
        } else if(permissionPlayer.hasPermissionGroup("Supporter")) {
            objective.getScore(" §8➜ §3Supporter").setScore(10);
        } else if(permissionPlayer.hasPermissionGroup("Clayer")) {
            objective.getScore(" §8➜ §eClayer").setScore(10);
        }

        objective.getScore("§2").setScore(9);
        objective.getScore(" §8•§7● §7Clays").setScore(8);

        Team team1 = scoreboard.registerNewTeam("x7");
        team1.setPrefix("§7");
        team1.setSuffix(" §8➜ §e" + ClayAPI.getInstance().getClaysSQL().getClays(player));
        team1.addEntry("§6");
        objective.getScore("§6").setScore(7);

        objective.getScore("§9").setScore(6);
        objective.getScore(" §8•§7● §7Online").setScore(5);

        Team team = scoreboard.registerNewTeam("x4");
        team.setPrefix("§8");
        team.setSuffix(" §8➜ §e" + player.getServer().getOnlinePlayers().size());
        team.addEntry("§3");
        objective.getScore("§3").setScore(4);

        objective.getScore(" §4").setScore(3);
        objective.getScore(" §8•§7● §7Discord").setScore(2);
        objective.getScore(" §8➜ §ddc.ClayMC.eu").setScore(1);
        objective.getScore(" §8").setScore(0);

        player.setScoreboard(scoreboard);
        board.put(scoreboard, player);

    }

    public void startScoreboardAnimation() {
        animationCount = 0;
        Bukkit.getScheduler().runTaskTimerAsynchronously(Lobbysystem.getInstance(), () -> {
            if (Bukkit.getOnlinePlayers().size() > 0) {

                Bukkit.getOnlinePlayers().forEach(current -> {
                    if (current.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) return;
                    current.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationCount]);
                });
                animationCount++;

                if (animationCount == animation.length) {
                    animationCount = 0;
                }
            }
        }, 80, 10);
    }

    public void update() {
        try {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Scoreboard scoreboard : board.keySet()) {
                        Player player = board.get(scoreboard);
                        scoreboard.getTeam("x4").setSuffix(" §8➜ §e" + player.getServer().getOnlinePlayers().size());
                        scoreboard.getTeam("x7").setSuffix(" §8➜ §e" + ClayAPI.getInstance().getClaysSQL().getClays(player));
                    }
                }
            }.runTaskTimer(Lobbysystem.getInstance(), 0, 20);
        }catch (Exception e) {}

    }

}
