package uk.co.jacekk.bukkit.SkylandsPlus;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;

public class SkylandsPlusLogger {
	
	private SkylandsPlus plugin;
	private Logger logger;
	
	public SkylandsPlusLogger(SkylandsPlus plugin){
		this.plugin = plugin;
		this.logger = Logger.getLogger("Minecraft");
	}
	
	private String buildString(String msg){
		PluginDescriptionFile pdFile = plugin.getDescription();
		
		return pdFile.getName() + " " + pdFile.getVersion() + ": " + msg;
	}
	
	public void info(String msg){
		this.logger.info(this.buildString(msg));
	}
	
	public void warn(String msg){
		this.logger.warning(this.buildString(msg));
	}
	
}
