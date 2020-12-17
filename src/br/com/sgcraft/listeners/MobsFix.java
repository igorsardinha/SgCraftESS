package br.com.sgcraft.listeners;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class MobsFix implements Listener {

    @EventHandler
    public void onSpawn(final CreatureSpawnEvent e) {
        if (e.getEntityType() == EntityType.SLIME) {
            final Slime slime = (Slime)e.getEntity();
            slime.setSize(1);
        }
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onEntitySpawnEvent(final EntitySpawnEvent event) {
        final Entity ent = event.getEntity();
        if (ent.getType() == EntityType.ZOMBIE && ((Zombie)ent).isBaby()) {
            ((Zombie)ent).setBaby(false);
        }
        else if (ent.getType() == EntityType.PIG_ZOMBIE && ((PigZombie)ent).isBaby()) {
            ((PigZombie)ent).setBaby(false);
        }
    }


    
	@EventHandler
    public void onEntityTarget(final EntityTargetEvent event) {
        if (event.getEntity() instanceof Creeper) {
            final Creeper creeper = (Creeper)event.getEntity();
            if (creeper.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof Spider) {
            final Spider spider = (Spider)event.getEntity();
            if (spider.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof Skeleton) {
            final Skeleton skeleton = (Skeleton)event.getEntity();
            if (skeleton.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof Enderman) {
            final Enderman enderman = (Enderman)event.getEntity();
            if (enderman.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof Zombie) {
            final Zombie zombie = (Zombie)event.getEntity();
            if (zombie.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof IronGolem) {
            final IronGolem irongolem = (IronGolem)event.getEntity();
            if (irongolem.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof PigZombie) {
            final PigZombie pigman = (PigZombie)event.getEntity();
            if (pigman.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof Slime) {
            final Slime slime = (Slime)event.getEntity();
            if (slime.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof Endermite) {
            final Endermite endermite = (Endermite)event.getEntity();
            if (endermite.getLastDamageCause() == null) {
                event.setCancelled(true);
            }
        }
        if(event.getEntity() instanceof IronGolem){
            IronGolem ig = (IronGolem)event.getEntity();
            ig.setTarget(null);
        }
    }
}
