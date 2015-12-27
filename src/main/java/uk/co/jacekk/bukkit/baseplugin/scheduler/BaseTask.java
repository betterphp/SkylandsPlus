package uk.co.jacekk.bukkit.baseplugin.scheduler;

import uk.co.jacekk.bukkit.baseplugin.BaseObject;

public abstract class BaseTask<Type>
  extends BaseObject<Type>
  implements Runnable
{
  public BaseTask(Type plugin)
  {
    super(plugin);
  }
}
