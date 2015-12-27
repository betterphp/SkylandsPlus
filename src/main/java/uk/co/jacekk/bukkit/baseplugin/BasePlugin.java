package uk.co.jacekk.bukkit.baseplugin;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import uk.co.jacekk.bukkit.baseplugin.command.CommandManager;
import uk.co.jacekk.bukkit.baseplugin.config.PluginConfig;
import uk.co.jacekk.bukkit.baseplugin.conversation.ConversationManager;
import uk.co.jacekk.bukkit.baseplugin.logging.PluginLogger;
import uk.co.jacekk.bukkit.baseplugin.permissions.PermissionManager;

public abstract class BasePlugin extends JavaPlugin
{
	public static final String VERSION = "13";
	//private static final String PACKAGE_NAME = "13";
	public PluginDescriptionFile description;
	public PluginLogger log;
	protected File baseDir;
	protected String baseDirPath;
	public Server server;
	public PluginManager pluginManager;
	public PermissionManager permissionManager;
	public CommandManager commandManager;
	public ConversationManager conversationManager;
	public BukkitScheduler scheduler;
	public PluginConfig config;
	public String displayName;
	
	public void onEnable(boolean createFolder)
	{
		String packageName = new String("uk.co.jacekk.bukkit.baseplugin");
		
		if (!BasePlugin.class.getName().equals(packageName + ".BasePlugin"))
		{
			throw new PackageNameException(BasePlugin.class.getName());
		}
		this.description = getDescription();
		this.log = new PluginLogger(this);
		
		this.baseDir = getDataFolder();
		this.baseDirPath = this.baseDir.getAbsolutePath();
		
		this.server = getServer();
		this.pluginManager = this.server.getPluginManager();
		this.permissionManager = new PermissionManager(this);
		this.commandManager = new CommandManager(this);
		this.conversationManager = new ConversationManager(this);
		this.scheduler = this.server.getScheduler();
		
		if ((createFolder) && (!this.baseDir.exists()))
		{
			this.baseDir.mkdirs();
		}
		this.displayName = this.description.getName();
		
		this.pluginManager.registerEvents(this.conversationManager, this);
	}
  
	public String formatMessage(String message, boolean colour, boolean version)
	{
	  	StringBuilder line = new StringBuilder();
	  	
	  	if (colour)
	  	{
	  		line.append(ChatColor.BLUE);
	  	}
	  	
	  	line.append("[");
	  	line.append(getDisplayName());
	  	
	  	if (version)
	  	{
	  		line.append(" v");
	  		line.append(this.description.getVersion());
	  	}
	  	
	  	line.append("] ");
	  	line.append(ChatColor.RESET);
	  	line.append(message);
	  	
	  	return line.toString();
	}
  
  	public String formatMessage(String message, boolean colour)
  	{
	  	return formatMessage(message, colour, !colour);
  	}
  
  	public String formatMessage(String message)
  	{
	  	return formatMessage(message, true, false);
  	}
  
  	public File getBaseDir()
  	{
	  	return this.baseDir;
  	}
  
  	public String getBaseDirPath()
  	{
	  	return this.baseDirPath;
  	}
  
  	public String getDisplayName()
  	{
	  	return this.displayName;
  	}
  
  	public void setDisplayName(String displayName)
  	{
	  this.displayName = displayName;
  	}
}