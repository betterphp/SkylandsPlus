package uk.co.jacekk.bukkit.baseplugin.command;

public class CommandRegistrationException
  extends RuntimeException
{
  private static final long serialVersionUID = -1674769027758878225L;
  
  public CommandRegistrationException(String message)
  {
    super(message);
  }
}
