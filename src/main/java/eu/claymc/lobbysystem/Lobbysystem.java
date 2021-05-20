package eu.claymc.lobbysystem;

import eu.claymc.lobbysystem.listener.ConnectionListener;
import eu.claymc.lobbysystem.listener.ProtectionListener;
import eu.claymc.lobbysystem.manager.ParticleManager;
import eu.claymc.lobbysystem.scheduler.ParticleScheduler;
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

    }

    public void schedule() {
        getServer().getScheduler().runTaskTimerAsynchronously(this, new ParticleScheduler(), 40, 40);
    }

    public static Lobbysystem getInstance() {
        return instance;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }
}
