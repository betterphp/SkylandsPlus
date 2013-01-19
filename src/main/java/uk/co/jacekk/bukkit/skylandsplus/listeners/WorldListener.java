package uk.co.jacekk.bukkit.skylandsplus.listeners;

import net.minecraft.server.v1_4_R1.WorldData;
import net.minecraft.server.v1_4_R1.WorldServer;
import net.minecraft.server.v1_4_R1.WorldType;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.craftbukkit.v1_4_R1.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.WorldInitEvent;

import uk.co.jacekk.bukkit.baseplugin.v8.event.BaseListener;
import uk.co.jacekk.bukkit.baseplugin.v8.util.ReflectionUtils;
import uk.co.jacekk.bukkit.skylandsplus.SkylandsPlus;
import uk.co.jacekk.bukkit.skylandsplus.generation.ChunkGenerator;

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
				ReflectionUtils.setFieldValue(WorldData.class, "type", worldServer.worldData, WorldType.NORMAL);
				
				plugin.log.info("The world type of '" + world.getName() + "' has been set to to normal.");
			}catch (Exception e){
				plugin.log.info("Could not change the world type of '" + world.getName() + "'.");
				e.printStackTrace();
			}
		}
	}
	
}