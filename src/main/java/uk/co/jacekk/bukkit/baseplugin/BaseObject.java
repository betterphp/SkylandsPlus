package uk.co.jacekk.bukkit.baseplugin;

public abstract class BaseObject<Type>
{
	protected Type plugin;
	
	public BaseObject(Type plugin)
	{
		this.plugin = plugin;
	}
}