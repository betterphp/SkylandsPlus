package uk.co.jacekk.bukkit.baseplugin.command.args;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PairArgumentProcessor
  extends ArgumentProcessor
{
  private LinkedHashMap<String, String> values;
  
  public PairArgumentProcessor(String[] args)
  {
    this.args = args;
    this.values = new LinkedHashMap<String, String>();
    
    process();
  }
  
  public void process()
  {
    int k = 0;
    int v = 1;
    while ((k < this.args.length) && (v < this.args.length))
    {
      this.values.put(this.args[k].toLowerCase(), this.args[v]);
      
      k += 2;
      v += 2;
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
}
