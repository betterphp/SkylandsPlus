package uk.co.jacekk.bukkit.baseplugin.util;

import java.util.HashMap;

public class StringUtils
{
  private static final HashMap<String, Integer> stringValues = new HashMap<String, Integer>();
  
  static
  {
    stringValues.put("snapshot", Integer.valueOf(-1));
    stringValues.put("dev", Integer.valueOf(-1));
    stringValues.put("alpha", Integer.valueOf(1));
    stringValues.put("beta", Integer.valueOf(2));
    stringValues.put("release", Integer.valueOf(3));
  }
  
  private static int numericValueOfVersionPart(String part)
  {
    try
    {
      return Integer.parseInt(part);
    }
    catch (NumberFormatException e)
    {
      if (part.length() == 1) {
        return part.getBytes()[0];
      }
      if (stringValues.containsKey(part)) {
        return ((Integer)stringValues.get(part)).intValue();
      }
    }
    return 0;
  }
  
  public static int versionCompare(String a, String b)
  {
    a = a.trim().toLowerCase();
    b = b.trim().toLowerCase();
    if (a.equals(b)) {
      return 0;
    }
    String[] partsa = a.replaceAll("([0-9]+)([a-z]+)", "$1.$2").replaceAll("([a-z]+)([0-9]+)", "$1.$2").split("[_ ,.+\\-]{1}");
    String[] partsb = b.replaceAll("([0-9]+)([a-z]+)", "$1.$2").replaceAll("([a-z]+)([0-9]+)", "$1.$2").split("[_ ,.+\\-]{1}");
    
    int max = Math.max(partsa.length, partsb.length);
    for (int i = 0; i < max; i++)
    {
      Integer vala = Integer.valueOf(i < partsa.length ? numericValueOfVersionPart(partsa[i]) : 0);
      Integer valb = Integer.valueOf(i < partsb.length ? numericValueOfVersionPart(partsb[i]) : 0);
      if (vala.intValue() > valb.intValue()) {
        return 1;
      }
      if (vala.intValue() < valb.intValue()) {
        return -1;
      }
    }
    return 0;
  }
}
