package uk.co.jacekk.bukkit.SkylandsPlus.listeners;

import java.lang.reflect.Field;

import net.minecraft.server.WorldServer;
import net.minecraft.server.WorldType;

import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import uk.co.jacekk.bukkit.SkylandsPlus.SkylandsPlus;
import uk.co.jacekk.bukkit.SkylandsPlus.generation.ChunkGenerator;

public class WorldListener implements Listener {
	
	private SkylandsPlus plugin;
	
	public WorldListener(SkylandsPlus plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onWorldInit(WorldInitEvent event){
		World world = event.getWorld();
		
		if (world.getGenerator() instanceof ChunkGenerator){
			WorldServer worldServer = ((CraftWorld) world).getHandle();
			
			try{
				Class<?> worldData = worldServer.worldData.getClass();
				
				Field type = worldData.getDeclaredField("type");
				type.setAccessible(true);
				
				type.set(worldServer.worldData, WorldType.NORMAL);
				
				plugin.log.info("We have set the world type of '" + world.getName() + "' to normal (this is required for custom biomes to work).");
			}catch (Exception e){
				plugin.log.info("Could not change the world type of '" + world.getName() + "'.");
				e.printStackTrace();
			}
		}
	}
	
}
