package uk.co.jacekk.bukkit.SkylandsPlus.listeners;

import java.lang.reflect.Field;

import net.minecraft.server.WorldServer;
import net.minecraft.server.WorldType;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.WorldInitEvent;

import uk.co.jacekk.bukkit.SkylandsPlus.SkylandsPlus;
import uk.co.jacekk.bukkit.SkylandsPlus.generation.ChunkGenerator;
import uk.co.jacekk.bukkit.baseplugin.BaseListener;

public class WorldListener extends BaseListener<SkylandsPlus> {
	
	public WorldListener(SkylandsPlus plugin){
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onWorldInit(WorldInitEvent event){
		World world = event.getWorld();
		
		if (world.getGenerator() instanceof ChunkGenerator && world.getEnvironment() == Environment.NORMAL){
			WorldServer worldServer = ((CraftWorld) world).getHandle();
			
			try{
				Class<?> worldData = worldServer.worldData.getClass();
				
				Field type = worldData.getDeclaredField("type");
				type.setAccessible(true);
				
				type.set(worldServer.worldData, WorldType.NORMAL);
				
				plugin.log.info("The world type of '" + world.getName() + "' has been set to to normal.");
			}catch (Exception e){
				plugin.log.info("Could not change the world type of '" + world.getName() + "'.");
				e.printStackTrace();
			}
		}
	}
	
}
