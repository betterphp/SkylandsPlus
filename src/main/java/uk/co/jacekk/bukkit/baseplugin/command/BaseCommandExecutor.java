package uk.co.jacekk.bukkit.baseplugin.command;

import uk.co.jacekk.bukkit.baseplugin.BaseObject;

public abstract class BaseCommandExecutor<Type> extends BaseObject<Type>
{
	public BaseCommandExecutor(Type plugin)
	{
	    super(plugin);
	}
}
