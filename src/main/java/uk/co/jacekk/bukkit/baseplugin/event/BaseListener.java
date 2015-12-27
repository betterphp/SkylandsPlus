package uk.co.jacekk.bukkit.baseplugin.event;

import org.bukkit.event.Listener;
import uk.co.jacekk.bukkit.baseplugin.BaseObject;

public abstract class BaseListener<Type>
  extends BaseObject<Type>
  implements Listener
{
  public BaseListener(Type plugin)
  {
    super(plugin);
  }
}
