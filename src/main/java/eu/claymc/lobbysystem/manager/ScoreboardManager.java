package eu.claymc.lobbysystem.manager;

import eu.claymc.api.ClayAPI;
import eu.claymc.lobbysystem.Lobbysystem;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.concurrent.ExecutionException;

public class ScoreboardManager {

    private static Integer animationCount;
    private final String[] animation = new String[]{
            "", "§6•", "§6•§e● ", "§6•§e● C", "§6•§e● Cl", "§6•§e● Cla", "§6•§e● Clay", "§6•§e● ClayM", "§6•§e● ClayMC",
            "§6•§e● ClayMC.", "§6•§e● ClayMC.e", "§6•§e● ClayMC.eu", "§6•§e● ClayMC.eu", "§6•§e● ClayMC.e", "§6•§e● ClayMC.",
            "§6•§e● ClayMC", "§6•§e● ClayMC §8▎", "§6•§e● ClayMC §8▎ §7L", "§6•§e● ClayMC §8▎ §7Lo", "§6•§e● ClayMC §8▎ §7Lob",
            "§6•§e● ClayMC §8▎ §7Lobb", "§6•§e● ClayMC §8▎ §7Lobby", "§6•§e● ClayMC §8▎ §7Lobby"
    };

    public void setScoreboard(final Player player) throws ExecutionException, InterruptedException {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("osne", "trew");
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
        ICloudPlayer cloudPlayer = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());

        String servername = cloudPlayer.getConnectedServer().getName();

        addScoreboardTeam(scoreboard, "0Owner", "§4§lOwner §8● §4");
        addScoreboardTeam(scoreboard, "1Admin", "§4Admin §8● §4");
        addScoreboardTeam(scoreboard, "2SrDeveloper", "§bSrDev §8● §b");
        addScoreboardTeam(scoreboard, "3Developer", "§bDev §8● §b");
        addScoreboardTeam(scoreboard, "4Content", "§bCon §8● §b");
        addScoreboardTeam(scoreboard, "5SrModerator", "§9SrMod §8● §9");
        addScoreboardTeam(scoreboard, "6Moderator", "§9Mod §8● §9");
        addScoreboardTeam(scoreboard, "7Supporter", "§eSup §8● §e");
        addScoreboardTeam(scoreboard, "8SrBuilder", "§2SrBuild §8● §2");
        addScoreboardTeam(scoreboard, "9Builder", "§2Build §8● §2");
        addScoreboardTeam(scoreboard, "99Clayer", "§e");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6•§e● ClayMC §8▎ §7Lobby");

        objective.getScore(" §8§m----------------").setScore(15);
        objective.getScore(" §8•§7● §7Server").setScore(14);
        objective.getScore(" §8➜ §e" + servername).setScore(13);
        objective.getScore("§1").setScore(12);
        objective.getScore(" §8•§7● §7Rang").setScore(11);

        if (permissionPlayer.hasPermissionGroup("Owner")) {
            objective.getScore(" §8➜ §4§lOwner").setScore(10);
            scoreboard.getTeam("0Owner").addEntry(player.getName());
        } else if (permissionPlayer.hasPermissionGroup("Admin")) {
            objective.getScore(" §8➜ §4Administrator").setScore(10);
            scoreboard.getTeam("1Admin").addEntry(player.getName());
        } else if (permissionPlayer.hasPermissionGroup("SrDeveloper")) {
            objective.getScore(" §8➜ §bSrDeveloper").setScore(10);
            scoreboard.getTeam("2SrDeveloper").addEntry(player.getName());
        } else if (permissionPlayer.hasPermissionGroup("Developer")) {
            objective.getScore(" §8➜ §bDeveloper").setScore(10);
            scoreboard.getTeam("3Developer").addEntry(player.getName());
        } else if (permissionPlayer.hasPermissionGroup("SrModerator")) {
            objective.getScore(" §8➜ §cSrModerator").setScore(10);
            scoreboard.getTeam("5SrModerator").addEntry(player.getName());
        } else if (permissionPlayer.hasPermissionGroup("Moderator")) {
            objective.getScore(" §8➜ §cModerator").setScore(10);
            scoreboard.getTeam("6Moderator").addEntry(player.getName());
        } else if (permissionPlayer.hasPermissionGroup("Supporter")) {
            objective.getScore(" §8➜ §3Supporter").setScore(10);
            scoreboard.getTeam("7Supporter").addEntry(player.getName());
        } else if (permissionPlayer.hasPermissionGroup("Clayer")) {
            objective.getScore(" §8➜ §eClayer").setScore(10);
            scoreboard.getTeam("9Builder").addEntry(player.getName());
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
        IPermissionPlayer orginalPermissionPlayer = permissionPlayer;
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            scoreboard = player.getScoreboard();
            permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(onlinePlayer.getUniqueId());

            if (permissionPlayer.hasPermissionGroup("Owner")) {
                scoreboard.getTeam("0Owner").addEntry(onlinePlayer.getName());
            } else if (permissionPlayer.hasPermissionGroup("Admin")) {
                scoreboard.getTeam("1Admin").addEntry(onlinePlayer.getName());
            } else if (permissionPlayer.hasPermissionGroup("SrDeveloper")) {
                scoreboard.getTeam("2SrDeveloper").addEntry(onlinePlayer.getName());
            } else if (permissionPlayer.hasPermissionGroup("Developer")) {
                scoreboard.getTeam("3Developer").addEntry(onlinePlayer.getName());
            } else if (permissionPlayer.hasPermissionGroup("SrModerator")) {
                scoreboard.getTeam("5SrModerator").addEntry(onlinePlayer.getName());
            } else if (permissionPlayer.hasPermissionGroup("Moderator")) {
                scoreboard.getTeam("6Moderator").addEntry(onlinePlayer.getName());
            } else if (permissionPlayer.hasPermissionGroup("Supporter")) {
                scoreboard.getTeam("7Supporter").addEntry(onlinePlayer.getName());
            } else if (permissionPlayer.hasPermissionGroup("Clayer")) {
                scoreboard.getTeam("9Builder").addEntry(onlinePlayer.getName());
            }

            scoreboard = onlinePlayer.getScoreboard();
            if (orginalPermissionPlayer.hasPermissionGroup("Owner")) {
                scoreboard.getTeam("0Owner").addEntry(player.getName());
            } else if (orginalPermissionPlayer.hasPermissionGroup("Admin")) {
                scoreboard.getTeam("1Admin").addEntry(player.getName());
            } else if (orginalPermissionPlayer.hasPermissionGroup("SrDeveloper")) {
                scoreboard.getTeam("2SrDeveloper").addEntry(player.getName());
            } else if (orginalPermissionPlayer.hasPermissionGroup("Developer")) {
                scoreboard.getTeam("3Developer").addEntry(player.getName());
            } else if (orginalPermissionPlayer.hasPermissionGroup("SrModerator")) {
                scoreboard.getTeam("5SrModerator").addEntry(player.getName());
            } else if (orginalPermissionPlayer.hasPermissionGroup("Moderator")) {
                scoreboard.getTeam("6Moderator").addEntry(player.getName());
            } else if (orginalPermissionPlayer.hasPermissionGroup("Supporter")) {
                scoreboard.getTeam("7Supporter").addEntry(player.getName());
            } else if (orginalPermissionPlayer.hasPermissionGroup("Clayer")) {
                scoreboard.getTeam("9Builder").addEntry(player.getName());
            }

            onlinePlayer.setScoreboard(scoreboard);


        }

        player.setScoreboard(scoreboard);

    }

    private Team addScoreboardTeam(Scoreboard scoreboard, String teamname, String prefix) {
        Team team = scoreboard.registerNewTeam(teamname);
        team.setPrefix(prefix);
        return team;
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
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getScoreboard() != null) {
                            Scoreboard scoreboard = player.getScoreboard();
                            scoreboard.getTeam("x4").setSuffix(" §8➜ §e" + player.getServer().getOnlinePlayers().size());
                            scoreboard.getTeam("x7").setSuffix(" §8➜ §e" + ClayAPI.getInstance().getClaysSQL().getClays(player));
                        }
                    }
                }
            }.runTaskTimer(Lobbysystem.getInstance(), 0, 20);
        } catch (Exception e) {
        }

    }

}
