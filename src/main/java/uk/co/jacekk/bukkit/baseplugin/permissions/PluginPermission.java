package uk.co.jacekk.bukkit.baseplugin.permissions;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class PluginPermission
{
  protected String node;
  protected PermissionDefault defaultValue;
  protected String description;
  
  public PluginPermission(String node, PermissionDefault defaultValue, String description)
  {
    this.node = node;
    this.defaultValue = defaultValue;
    this.description = description;
  }
  
  public String getNode()
  {
    return this.node;
  }
  
  public PermissionDefault getDefault()
  {
    return this.defaultValue;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public List<Player> getPlayersWith()
  {
    ArrayList<Player> players = new ArrayList<Player>();
    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
      if (has(player)) {
        players.add(player);
      }
    }
    return players;
  }
  
  public boolean has(CommandSender sender)
  {
    return sender.hasPermission(this.node);
  }
}
