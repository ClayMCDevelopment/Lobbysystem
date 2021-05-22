package eu.claymc.lobbysystem.manager;

import eu.claymc.api.ClayAPI;
import eu.claymc.api.clays.ClaysSQL;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class ScoreboardManager {

    private static HashMap<Scoreboard, Player> board = new HashMap<>();

    private static Integer animationCount;
    private final String[] animation = new String[]{
            "", "§6•", "§6•§e● ", "§6•§e● C", "§6•§e● Cl", "§6•§e● Cla", "§6•§e● Clay", "§6•§e● ClayM", "§6•§e● ClayMC",
            "§6•§e● ClayMC.", "§6•§e● ClayMC.e", "§6•§e● ClayMC.eu", "§6•§e● ClayMC.eu", "§6•§e● ClayMC.e", "§6•§e● ClayMC.",
            "§6•§e● ClayMC", "§6•§e● ClayMC §8▎", "§6•§e● ClayMC §8▎ §7L", "§6•§e● ClayMC §8▎ §7Lo", "§6•§e● ClayMC §8▎ §7Lob",
            "§6•§e● ClayMC §8▎ §7Lobb", "§6•§e● ClayMC §8▎ §7Lobby", "§6•§e● ClayMC §8▎ §7Lobby"
    };

    public void setScoreboard(Player player) {
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        final IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6•§e● ClayMC §8▎ §7Lobby");

        objective.getScore("§8§m----------------").setScore(14);
        objective.getScore("§8•§7● §7Server").setScore(13);
        objective.getScore("§8➜ §e" + player.getServer().getServerName()).setScore(12);
        objective.getScore("§1").setScore(11);
        objective.getScore("§8•§7● §7Rang").setScore(10);

        if(permissionPlayer.hasPermissionGroup("Owner")) {
            objective.getScore("§8➜ §4§lOwner").setScore(9);
        } else if(permissionPlayer.hasPermissionGroup("Admin")) {
            objective.getScore("§8➜ §4Administrator").setScore(9);
        } else if(permissionPlayer.hasPermissionGroup("SrDeveloper")) {
            objective.getScore("§8➜ §bSrDeveloper").setScore(9);
        } else if(permissionPlayer.hasPermissionGroup("Developer")) {
            objective.getScore("§8➜ §bDeveloper").setScore(9);
        } else if(permissionPlayer.hasPermissionGroup("SrModerator")) {
            objective.getScore("§8➜ §cSrModerator").setScore(9);
        } else if(permissionPlayer.hasPermissionGroup("Moderator")) {
            objective.getScore("§8➜ §cModerator").setScore(9);
        } else if(permissionPlayer.hasPermissionGroup("Supporter")) {
            objective.getScore("§8➜ §3Supporter").setScore(9);
        } else if(permissionPlayer.hasPermissionGroup("Clayer")) {
            objective.getScore("§8➜ §eClayer").setScore(9);
        }

        objective.getScore("§2").setScore(8);
        objective.getScore("§8•§7● §7Clays").setScore(7);
        objective.getScore("§8➜ §e" + ClayAPI.getInstance().getClaysSQL().getClays(player)).setScore(6);
        objective.getScore("§9").setScore(5);
        objective.getScore("§8•§7● §7Online").setScore(4);
        //objective.getScore("§8➜ §e" + CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-1").getOnlineCount()).setScore(3);

        Team team = scoreboard.registerNewTeam("x3");
        team.setPrefix("§8");
        team.setSuffix("§8➜ §e" + player.getServer().getOnlinePlayers().size());
        team.addEntry("§3");
        objective.getScore("§3").setScore(3);

        objective.getScore("§4").setScore(2);
        objective.getScore("§8•§7● §7Discord").setScore(1);
        objective.getScore("§8➜ §ddc.ClayMC.eu").setScore(0);

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
                        scoreboard.getTeam("x3").setSuffix("§8➜ §e" + player.getServer().getOnlinePlayers().size());
                    }
                }
            }.runTaskTimer(Lobbysystem.getInstance(), 0, 140);
        }catch (Exception e) {}

    }

}
