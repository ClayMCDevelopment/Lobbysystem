package eu.claymc.lobbysystem.listener;

import eu.claymc.lobbysystem.enums.LocationEnum;
import eu.claymc.lobbysystem.manager.ItemManager;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler

    public void onBuckitFill(PlayerBucketFillEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBuckletEmpty(PlayerBucketEmptyEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDamageEvent(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerTrample(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL) { e.setCancelled(true);} }

    @EventHandler
    public void onEntityTrample(EntityInteractEvent e) {
        if (e.getEntityType() != EntityType.PLAYER && e.getBlock().getType() == Material.SOIL) { e.setCancelled(true); } }

    @EventHandler
    public void onBlockForm(final BlockFormEvent event) { event.setCancelled(true);}

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onMoveEvent(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getY() <= 0) {
            p.teleport(LocationEnum.SPAWN.getLocation());
            p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 61, 0);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 10000));
            p.removePotionEffect(PotionEffectType.BLINDNESS);
            return;
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();

            try {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL && p.getFallDistance() >= 10.0F) {
                    for (int i = 0; i != 15; ++i) {
                        p.playEffect(p.getLocation(), Effect.CLOUD, 10);
                    }
                    //ClayerUtil.getBloodBuilder().playEffect(((Player) event.getEntity()));
                    p.playSound(p.getLocation(), Sound.BLAZE_HIT, 1.0F, 1.0F);
                }
            } catch (Exception var4) {
            }
        }


    }

    @EventHandler
    public void onArmorestandPlayer(final PlayerArmorStandManipulateEvent event) {

        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

    }


    @EventHandler
    public void onBreak(final BlockBreakEvent event) {

        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {

        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    /*@EventHandler
    public void onInventoryClick(PlayerInteractEvent event) {
        if (!LobbyPlugin.getPlugin().getBuild().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }*/

    @EventHandler
    public void onDrop(final PlayerDropItemEvent event) {
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPickup(final PlayerPickupItemEvent event) {
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onEditBook(final PlayerEditBookEvent event) {
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void handleSneek(final PlayerToggleSneakEvent event) {
        final Player player = event.getPlayer();

        if(player.isSneaking()) {

            ItemManager.setItems(player);

        } else {

            ItemManager.setSneekItems(player);

        }

    }

}
