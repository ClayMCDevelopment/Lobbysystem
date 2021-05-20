package eu.claymc.lobbysystem;

import eu.claymc.lobbysystem.items.*;
import eu.claymc.lobbysystem.listener.ConnectionListener;
import eu.claymc.lobbysystem.listener.ProtectionListener;
import eu.claymc.lobbysystem.manager.ParticleManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobbysystem extends JavaPlugin {

    public static Lobbysystem instance;

    private ParticleManager particleManager;

    @Override
    public void onEnable() {
        instance = this;
        init();

        this.particleManager = new ParticleManager();

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

    }

    public static Lobbysystem getInstance() {
        return instance;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }
}
