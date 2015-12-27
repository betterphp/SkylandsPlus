package uk.co.jacekk.bukkit.baseplugin.config;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import uk.co.jacekk.bukkit.baseplugin.logging.PluginLogger;

public class PluginConfig
{
  private File configFile;
  private YamlConfiguration config;
  private PluginConfigKey[] configDefaults;
  private PluginLogger log;
  
  public PluginConfig(File configFile, PluginConfigKey[] configDefaults, PluginLogger log)
  {
    this.configFile = configFile;
    this.config = new YamlConfiguration();
    this.configDefaults = configDefaults;
    this.log = log;
    
    updateFile();
  }
  
  public PluginConfig(File configFile, Class<?> configHolder, PluginLogger log)
  {
    ArrayList<PluginConfigKey> config = new ArrayList<PluginConfigKey>();
    for (Field field : configHolder.getDeclaredFields()) {
      if (field.getType().equals(PluginConfigKey.class)) {
        try
        {
          config.add((PluginConfigKey)field.get(null));
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    this.configFile = configFile;
    this.config = new YamlConfiguration();
    this.configDefaults = ((PluginConfigKey[])config.toArray(new PluginConfigKey[0]));
    this.log = log;
    
    updateFile();
  }
  
  private void updateFile()
  {
    if (this.configFile.exists()) {
      reload();
    }
    boolean updateNeeded = false;
    for (PluginConfigKey entry : this.configDefaults)
    {
      String key = entry.getKey();
      if (!this.config.contains(key))
      {
        this.config.set(key, entry.getDefault());
        
        updateNeeded = true;
      }
    }
    if (updateNeeded) {
      try
      {
        this.config.save(this.configFile);
        this.log.info("The " + this.configFile.getName() + " file has been updated.");
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void reload()
  {
    try
    {
      this.config.load(this.configFile);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public boolean containsKey(PluginConfigKey configKey)
  {
    return ArrayUtils.contains(this.configDefaults, configKey);
  }
  
  public int getInt(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return 0;
    }
    return this.config.getInt(configKey.getKey(), ((Integer)configKey.getDefault()).intValue());
  }
  
  @SuppressWarnings("unchecked")
public List<Integer> getIntList(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return new ArrayList<Integer>();
    }
    if (!this.config.contains(configKey.getKey())) {
      return (List<Integer>)configKey.getDefault();
    }
    return this.config.getIntegerList(configKey.getKey());
  }
  
  public double getDouble(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return 0.0D;
    }
    return this.config.getDouble(configKey.getKey(), ((Double)configKey.getDefault()).doubleValue());
  }
  
  @SuppressWarnings("unchecked")
public List<Double> getDoubleList(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return new ArrayList<Double>();
    }
    if (!this.config.contains(configKey.getKey())) {
      return (List<Double>)configKey.getDefault();
    }
    return this.config.getDoubleList(configKey.getKey());
  }
  
  public long getLong(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return 0L;
    }
    return this.config.getLong(configKey.getKey(), ((Long)configKey.getDefault()).longValue());
  }
  
  @SuppressWarnings("unchecked")
public List<Long> getLongList(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return new ArrayList<Long>();
    }
    if (!this.config.contains(configKey.getKey())) {
      return (List<Long>)configKey.getDefault();
    }
    return this.config.getLongList(configKey.getKey());
  }
  
  public boolean getBoolean(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return false;
    }
    return this.config.getBoolean(configKey.getKey(), ((Boolean)configKey.getDefault()).booleanValue());
  }
  
  public String getString(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return "";
    }
    return this.config.getString(configKey.getKey(), (String)configKey.getDefault());
  }
  
  @SuppressWarnings("unchecked")
public List<String> getStringList(PluginConfigKey configKey)
  {
    if ((!configKey.isDynamic()) && (!containsKey(configKey))) {
      return new ArrayList<String>();
    }
    if (!this.config.contains(configKey.getKey())) {
      return (List<String>)configKey.getDefault();
    }
    return this.config.getStringList(configKey.getKey());
  }
  
  public void set(PluginConfigKey configKey, Object value)
  {
    if (containsKey(configKey))
    {
      this.config.set(configKey.getKey(), value);
      try
      {
        this.config.save(this.configFile);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
