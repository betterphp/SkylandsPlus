package uk.co.jacekk.bukkit.baseplugin.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListStore
{
  private File storageFile;
  private ArrayList<String> data;
  private boolean caseSensitive;
  
  public ListStore(File storageFile, boolean caseSensitive)
  {
    this.storageFile = storageFile;
    
    this.data = new ArrayList<String>();
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
        String entry = this.caseSensitive ? line : line.toLowerCase();
        if (!this.data.contains(entry)) {
          this.data.add(entry);
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
      for (String entry : this.data)
      {
        out.write(entry);
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
  
  public boolean contains(String entry)
  {
    return this.data.contains(this.caseSensitive ? entry : entry.toLowerCase());
  }
  
  public List<String> getAll()
  {
    return this.data;
  }
  
  public Integer size()
  {
    return Integer.valueOf(this.data.size());
  }
  
  public void add(String entry)
  {
    if (!this.caseSensitive) {
      entry = entry.toLowerCase();
    }
    if (!this.data.contains(entry))
    {
      this.data.add(entry);
      try
      {
        FileWriter stream = new FileWriter(this.storageFile, true);
        BufferedWriter out = new BufferedWriter(stream);
        
        out.write(entry);
        out.newLine();
        
        out.close();
        stream.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void remove(String entry)
  {
    this.data.remove(this.caseSensitive ? entry : entry.toLowerCase());
    save();
  }
  
  public void removeAll()
  {
    this.data.clear();
    save();
  }
}
