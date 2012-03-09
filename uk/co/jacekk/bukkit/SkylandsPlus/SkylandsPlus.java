package uk.co.jacekk.bukkit.SkylandsPlus;

import java.io.File;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.jacekk.bukkit.SkylandsPlus.listeners.MobSpawnListener;
import uk.co.jacekk.bukkit.SkylandsPlus.listeners.PhysicsListener;
import uk.co.jacekk.bukkit.SkylandsPlus.listeners.WorldListener;

public class SkylandsPlus extends JavaPlugin {
	
	public SkylandsPlusLogger log;
	public SkylandsPlusConfig config;
	
	public void onEnable(){
		String pluginFolder = this.getDataFolder().getAbsolutePath();
		
		(new File(pluginFolder)).mkdirs();
		
		this.log = new SkylandsPlusLogger(this);
		this.config = new SkylandsPlusConfig(new File(pluginFolder + File.separator + "config.yml"), this);
		
		PluginManager manager = this.getServer().getPluginManager();
		
		if (this.config.getBoolean("prevent-sand-falling")){
			manager.registerEvents(new PhysicsListener(), this);
		}
		
		if (this.config.getBoolean("restrict-mob-spawning")){
			manager.registerEvents(new MobSpawnListener(), this);
		}
		
		manager.registerEvents(new WorldListener(this), this);
		
		this.log.info("Enabled.");
	}
	
	public void onDisable(){
		this.log.info("Disabled.");
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new uk.co.jacekk.bukkit.SkylandsPlus.generator.ChunkGenerator();
	}
	
}
