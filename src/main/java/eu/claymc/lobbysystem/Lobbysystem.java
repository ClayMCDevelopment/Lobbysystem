package eu.claymc.lobbysystem;

import eu.claymc.api.ClayAPI;
import eu.claymc.lobbysystem.enums.LocationEnum;
import eu.claymc.lobbysystem.items.*;
import eu.claymc.lobbysystem.items.click.AcpClickListener;
import eu.claymc.lobbysystem.items.click.LobbiesClickListener;
import eu.claymc.lobbysystem.items.click.NavigatorClickListener;
import eu.claymc.lobbysystem.items.click.ProfileClickListener;
import eu.claymc.lobbysystem.items.report.ReportItem;
import eu.claymc.lobbysystem.items.report.sql.ReportSQL;
import eu.claymc.lobbysystem.items.shop.ShopClickListener;
import eu.claymc.lobbysystem.items.shop.ShopInteractListener;
import eu.claymc.lobbysystem.items.shop.sql.ShopSQL;
import eu.claymc.lobbysystem.listener.ConnectionListener;
import eu.claymc.lobbysystem.listener.ProtectionListener;
import eu.claymc.lobbysystem.manager.ParticleManager;
import eu.claymc.lobbysystem.manager.ScoreboardManager;
import eu.claymc.lobbysystem.utils.Data;
import eu.claymc.lobbysystem.utils.SkullBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Lobbysystem extends JavaPlugin {

    public static Lobbysystem instance;

    private ParticleManager particleManager;
    private Data data;
    private SkullBuilder skullBuilder;
    private ScoreboardManager scoreboardManager;
    private ShopSQL shopSQL;
    private ReportSQL reportSQL;

    @Override
    public void onEnable() {
        instance = this;
        init();
        schedule();
        createMySQLTable();

        this.particleManager = new ParticleManager();
        this.data = new Data();

        this.scoreboardManager = new ScoreboardManager();
        this.scoreboardManager.startScoreboardAnimation();
        this.scoreboardManager.update();

        this.shopSQL = new ShopSQL();
        this.reportSQL = new ReportSQL();

        Bukkit.getScheduler().scheduleSyncDelayedTask(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getData().spawnArmorstands();
            }
        }, 80);

    }

    @Override
    public void onDisable() {

    }

    public void init() {
        final PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ConnectionListener(), this);
        pluginManager.registerEvents(new ProtectionListener(), this);
        pluginManager.registerEvents(new NavigatorItem(), this);
        pluginManager.registerEvents(new GrapplingHookItem(), this);
        pluginManager.registerEvents(new AcpItem(), this);
        pluginManager.registerEvents(new PlayerHiderItem(), this);
        pluginManager.registerEvents(new LobbiesItem(), this);
        pluginManager.registerEvents(new ProfileItem(), this);
        pluginManager.registerEvents(new NavigatorClickListener(), this);
        pluginManager.registerEvents(new AcpClickListener(), this);
        pluginManager.registerEvents(new SilentHubItem(), this);
        pluginManager.registerEvents(new LobbiesClickListener(), this);
        pluginManager.registerEvents(new ShopInteractListener(), this);
        pluginManager.registerEvents(new ShopClickListener(), this);
        pluginManager.registerEvents(new ProfileClickListener(), this);
        pluginManager.registerEvents(new ReportItem(), this);

    }
    public void schedule() {

        new BukkitRunnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getParticleManager().spawnParticle(LocationEnum.SPAWN.getLocation());
            }
        }.runTaskTimer(Lobbysystem.getInstance(), 0, 35);

    }

    public void createMySQLTable() {
        ClayAPI.getInstance().getSqlAdapter().update("CREATE TABLE IF NOT EXISTS claypass (UUID VARCHAR(64), BUYED INT);");
    }

    public static Lobbysystem getInstance() {
        return instance;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }

    public Data getData() {
        return data;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public ShopSQL getShopSQL() {
        return shopSQL;
    }

    public ReportSQL getReportSQL() {
        return reportSQL;
    }
}
