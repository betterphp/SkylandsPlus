package uk.co.jacekk.bukkit.baseplugin;

public class PackageNameException extends RuntimeException
{
	private static final long serialVersionUID = -111121073497919830L;
	
	public PackageNameException(String className)
	{
		super("BasePlugin library has incorrect package name: " + className);
	}
}