package uk.co.jacekk.bukkit.baseplugin.update;

import java.net.MalformedURLException;
import java.net.URL;
import org.bukkit.plugin.Plugin;

public abstract class UpdateChecker
{
  protected Plugin plugin;
  protected URL filesFeed;
  protected String version;
  protected String link;
  protected String jarLink;
  
  protected UpdateChecker(Plugin plugin, String url)
  {
    this.plugin = plugin;
    try
    {
      this.filesFeed = new URL(url);
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public String getLink()
  {
    return this.link;
  }
  
  public String getJarLink()
  {
    return this.jarLink;
  }
  
  public abstract boolean updateNeeded();
}
