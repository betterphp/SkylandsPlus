package uk.co.jacekk.bukkit.SkylandsPlus.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPhysicsEvent;

import uk.co.jacekk.bukkit.SkylandsPlus.SkylandsPlus;
import uk.co.jacekk.bukkit.SkylandsPlus.generation.ChunkGenerator;
import uk.co.jacekk.bukkit.baseplugin.event.BaseListener;

public class PhysicsListener extends BaseListener<SkylandsPlus> {
	
	public PhysicsListener(SkylandsPlus plugin){
		super(plugin);
	}
	
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
