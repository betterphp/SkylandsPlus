package uk.co.jacekk.bukkit.baseplugin.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataStore
{
  private File storageFile;
  private HashMap<String, String> data;
  private boolean caseSensitive;
  
  public DataStore(File file, boolean caseSensitive)
  {
    this.storageFile = file;
    
    this.data = new HashMap<String, String>();
    this.caseSensitive = caseSensitive;
    if (!this.storageFile.exists()) {
      try
      {
        this.storageFile.createNewFile();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void load()
  {
    try
    {
      DataInputStream input = new DataInputStream(new FileInputStream(this.storageFile));
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
      String line;
      while ((line = reader.readLine()) != null)
      {
        String[] info = line.split(":", 2);
        String key = this.caseSensitive ? info[0] : info[0].toLowerCase();
        String value = info[1];
        if (!this.data.containsKey(key)) {
          this.data.put(key, value);
        }
      }
      reader.close();
      input.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void save()
  {
    try
    {
      FileWriter stream = new FileWriter(this.storageFile);
      BufferedWriter out = new BufferedWriter(stream);
      for (Map.Entry<String, String> entry : this.data.entrySet())
      {
        out.write((String)entry.getKey() + ":" + (String)entry.getValue());
        out.newLine();
      }
      out.close();
      stream.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public boolean contains(String key)
  {
    return this.data.containsKey(this.caseSensitive ? key : key.toLowerCase());
  }
  
  public Set<Map.Entry<String, String>> getAll()
  {
    return this.data.entrySet();
  }
  
  public Set<String> getKeys()
  {
    return this.data.keySet();
  }
  
  public String getData(String key)
  {
    if (!this.caseSensitive) {
      key = key.toLowerCase();
    }
    if (!this.data.containsKey(key)) {
      return "";
    }
    return (String)this.data.get(key);
  }
  
  public int size()
  {
    return this.data.size();
  }
  
  public void put(String key, String value)
  {
    this.data.put(this.caseSensitive ? key : key.toLowerCase(), value);
  }
  
  public void add(String key, String value)
  {
    if (!this.caseSensitive) {
      key = key.toLowerCase();
    }
    if (!this.data.containsKey(key)) {
      this.data.put(key, value);
    }
  }
  
  public void remove(String key)
  {
    this.data.remove(this.caseSensitive ? key : key.toLowerCase());
  }
  
  public void removeAll()
  {
    this.data.clear();
  }
}
