package uk.co.jacekk.bukkit.SkylandsPlus.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import uk.co.jacekk.bukkit.SkylandsPlus.generator.ChunkGeneratorOld;

public class MobSpawnListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		if (event.getLocation().getWorld().getGenerator() instanceof ChunkGeneratorOld && event.getSpawnReason() == SpawnReason.NATURAL){
			int total = 0;
			
			for (Entity entity : event.getLocation().getChunk().getEntities()){
				if (entity.getClass().equals(event.getEntity().getClass())){
					++total;
					
					if (total > 3){
						event.setCancelled(true);
						return;
					}
				}
			}
		}
	}
	
}
