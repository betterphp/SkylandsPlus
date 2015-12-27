package uk.co.jacekk.bukkit.baseplugin.permissions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.bukkit.permissions.Permission;
import uk.co.jacekk.bukkit.baseplugin.BaseObject;
import uk.co.jacekk.bukkit.baseplugin.BasePlugin;

public class PermissionManager
  extends BaseObject<BasePlugin>
{
  public PermissionManager(BasePlugin plugin)
  {
    super(plugin);
  }
  
  public void registerPermissions(PluginPermission[] permissions)
  {
    for (PluginPermission permission : permissions) {
      ((BasePlugin)this.plugin).pluginManager.addPermission(new Permission(permission.getNode(), permission.getDescription(), permission.getDefault()));
    }
  }
  
  public void registerPermissions(Class<?> permissionHolder)
  {
    ArrayList<PluginPermission> perms = new ArrayList<PluginPermission>();
    for (Field field : permissionHolder.getDeclaredFields()) {
      if (field.getType().equals(PluginPermission.class)) {
        try
        {
          perms.add((PluginPermission)field.get(null));
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    registerPermissions((PluginPermission[])perms.toArray(new PluginPermission[0]));
  }
}
