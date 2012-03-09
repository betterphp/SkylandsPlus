package uk.co.jacekk.bukkit.SkylandsPlus.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

import uk.co.jacekk.bukkit.SkylandsPlus.generator.ChunkGenerator;

public class PhysicsListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onBlockPhysics(BlockPhysicsEvent event){
		Material changed = event.getChangedType();
		
		if (changed == Material.SAND || changed == Material.GRAVEL){
			if (event.getBlock().getWorld().getGenerator() instanceof ChunkGenerator){
				event.setCancelled(true);
			}
		}
	}
	
}
