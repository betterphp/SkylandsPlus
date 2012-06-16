package uk.co.jacekk.bukkit.SkylandsPlus;

import java.io.File;

import org.bukkit.generator.ChunkGenerator;

import uk.co.jacekk.bukkit.SkylandsPlus.listeners.MobSpawnListener;
import uk.co.jacekk.bukkit.SkylandsPlus.listeners.PhysicsListener;
import uk.co.jacekk.bukkit.SkylandsPlus.listeners.WorldListener;
import uk.co.jacekk.bukkit.baseplugin.BasePlugin;
import uk.co.jacekk.bukkit.baseplugin.config.PluginConfig;

public class SkylandsPlus extends BasePlugin {
	
	public void onEnable(){
		super.onEnable(true);
		
		this.config = new PluginConfig(new File(this.baseDirPath + File.separator + "config.yml"), Config.values(), this.log);
		
		if (this.config.getBoolean(Config.PREVENT_SAND_FALLING)){
			this.pluginManager.registerEvents(new PhysicsListener(this), this);
		}
		
		if (this.config.getBoolean(Config.RESTRICT_MOB_SPAWNING)){
			this.pluginManager.registerEvents(new MobSpawnListener(this), this);
		}
		
		this.pluginManager.registerEvents(new WorldListener(this), this);
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new uk.co.jacekk.bukkit.SkylandsPlus.generation.ChunkGenerator();
	}
	
}
