package uk.co.jacekk.bukkit.baseplugin.config;

public class PluginConfigKey
{
  private String key;
  private Object defaultValue;
  private boolean dynamic;
  
  public PluginConfigKey(String key, Object defaultValue, boolean dynamic)
  {
    this.key = key;
    this.defaultValue = defaultValue;
    this.dynamic = dynamic;
  }
  
  public PluginConfigKey(String key, Object defaultValue)
  {
    this(key, defaultValue, false);
  }
  
  public String getKey()
  {
    return this.key;
  }
  
  public Object getDefault()
  {
    return this.defaultValue;
  }
  
  public boolean isDynamic()
  {
    return this.dynamic;
  }
}
