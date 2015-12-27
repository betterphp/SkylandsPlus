package uk.co.jacekk.bukkit.baseplugin.util;

import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.ChatColor;

public class ChatUtils
{
  private static final LinkedHashMap<String, ChatColor> formattingCodeMap = new LinkedHashMap<String, ChatColor>();
  
  static
  {
    for (ChatColor colour : ChatColor.values()) {
      if (colour.isColor()) {
        formattingCodeMap.put("&" + colour.getChar(), colour);
      } else {
        formattingCodeMap.put("#" + colour.name().substring(0, 1).toLowerCase(), colour);
      }
    }
  }
  
  public static LinkedHashMap<String, ChatColor> getFormattingCodeMap()
  {
    return formattingCodeMap;
  }
  
  public static String parseFormattingCodes(String message)
  {
    for (Map.Entry<String, ChatColor> entry : formattingCodeMap.entrySet()) {
      message = message.replaceAll((String)entry.getKey(), ((ChatColor)entry.getValue()).toString());
    }
    return message;
  }
}
