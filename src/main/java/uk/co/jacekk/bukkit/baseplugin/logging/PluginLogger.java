package uk.co.jacekk.bukkit.baseplugin.logging;

import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class PluginLogger
{
  private Plugin plugin;
  private Logger logger;
  
  public PluginLogger(Plugin plugin)
  {
    this.plugin = plugin;
    this.logger = Logger.getLogger("Minecraft");
  }
  
  private String buildString(String msg)
  {
    PluginDescriptionFile pdf = this.plugin.getDescription();
    return "[" + pdf.getName() + " v" + pdf.getVersion() + "]: " + msg;
  }
  
  public void info(String msg)
  {
    this.logger.info(buildString(msg));
  }
  
  public void warn(String msg)
  {
    this.logger.warning(buildString(msg));
  }
  
  public void fatal(String msg)
  {
    this.logger.severe(buildString(msg));
  }
}
