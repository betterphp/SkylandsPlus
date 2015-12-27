package uk.co.jacekk.bukkit.baseplugin.command.args;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class KeyValueArgumentProcessor
  extends ArgumentProcessor
{
  private String separator;
  private LinkedHashMap<String, String> values;
  private LinkedList<String> leftover;
  
  public KeyValueArgumentProcessor(String[] args, String separator)
  {
    this.args = args;
    this.separator = separator;
    this.values = new LinkedHashMap<String, String>();
    this.leftover = new LinkedList<String>();
    
    process();
  }
  
  public void process()
  {
    for (String argument : this.args)
    {
      String[] parts = argument.split(this.separator, 2);
      if (parts.length == 2) {
        this.values.put(parts[0].toLowerCase(), parts[1]);
      } else {
        this.leftover.add(argument);
      }
    }
  }
  
  public boolean contains(String key)
  {
    return this.values.containsKey(key.toLowerCase());
  }
  
  public String get(String key)
  {
    return (String)this.values.get(key.toLowerCase());
  }
  
  public Set<Map.Entry<String, String>> getAll()
  {
    return this.values.entrySet();
  }
  
  public LinkedList<String> getLeftOver()
  {
    return this.leftover;
  }
}
