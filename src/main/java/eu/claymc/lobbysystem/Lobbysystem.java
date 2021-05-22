package eu.claymc.lobbysystem;

import eu.claymc.lobbysystem.items.*;
import eu.claymc.lobbysystem.items.click.AcpClickListener;
import eu.claymc.lobbysystem.items.click.NavigatorClickListener;
import eu.claymc.lobbysystem.listener.ConnectionListener;
import eu.claymc.lobbysystem.listener.ProtectionListener;
import eu.claymc.lobbysystem.manager.ParticleManager;
import eu.claymc.lobbysystem.manager.ScoreboardManager;
import eu.claymc.lobbysystem.utils.Data;
import eu.claymc.lobbysystem.utils.SkullBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobbysystem extends JavaPlugin {

    public static Lobbysystem instance;

    private ParticleManager particleManager;
    private Data data;
    private SkullBuilder skullBuilder;
    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        instance = this;
        init();

        this.particleManager = new ParticleManager();
        this.data = new Data();
        this.scoreboardManager = new ScoreboardManager();
        this.scoreboardManager.startScoreboardAnimation();

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
}
