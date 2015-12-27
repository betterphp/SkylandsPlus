package uk.co.jacekk.bukkit.baseplugin.command;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.SimplePluginManager;

import uk.co.jacekk.bukkit.baseplugin.BasePlugin;
import uk.co.jacekk.bukkit.baseplugin.util.ReflectionUtils;

public class CommandManager
{
  private BasePlugin plugin;
  private CommandMap commandMap;
  
  public CommandManager(BasePlugin plugin)
  {
    this.plugin = plugin;
    try
    {
      this.commandMap = ((CommandMap)ReflectionUtils.getFieldValue(SimplePluginManager.class, "commandMap", CommandMap.class, plugin.pluginManager));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private boolean registerCommand(PluginCommand command)
  {
    return this.commandMap.register(this.plugin.description.getName(), command);
  }
  
@SuppressWarnings("rawtypes")
public void registerCommandExecutor(BaseCommandExecutor<? extends BasePlugin> executor)
  {
    Class<? extends BaseCommandExecutor> cls = executor.getClass();
    for (Method method : cls.getDeclaredMethods())
    {
      CommandHandler commandInfo = (CommandHandler)method.getAnnotation(CommandHandler.class);
      CommandTabCompletion tabInfo = (CommandTabCompletion)method.getAnnotation(CommandTabCompletion.class);
      if (commandInfo != null)
      {
        if (!method.getReturnType().equals(Void.TYPE)) {
          throw new CommandRegistrationException("Incorrect return type for command method " + method.getName() + " in " + cls.getName());
        }
        if (!Arrays.equals(method.getParameterTypes(), new Class[] { CommandSender.class, String.class, String[].class })) {
          throw new CommandRegistrationException("Incorrect arguments for command method " + method.getName() + " in " + cls.getName());
        }
        PluginCommand command = new PluginCommand(this.plugin, executor, method, commandInfo.names(), commandInfo.description(), commandInfo.usage(), tabInfo == null ? new String[0] : tabInfo.value());
        if (!registerCommand(command)) {
          throw new CommandRegistrationException("Failed to register command for method " + method.getName() + " in " + cls.getName());
        }
      }
    }
    for (Method method : cls.getDeclaredMethods())
    {
      SubCommandHandler subCommandInfo = (SubCommandHandler)method.getAnnotation(SubCommandHandler.class);
      CommandTabCompletion tabInfo = (CommandTabCompletion)method.getAnnotation(CommandTabCompletion.class);
      if (subCommandInfo != null)
      {
        if (!method.getReturnType().equals(Void.TYPE)) {
          throw new CommandRegistrationException("Incorrect return type for command method " + method.getName() + " in " + cls.getName());
        }
        if (!Arrays.equals(method.getParameterTypes(), new Class[] { CommandSender.class, String.class, String[].class })) {
          throw new CommandRegistrationException("Incorrect arguments for command method " + method.getName() + " in " + cls.getName());
        }
        PluginCommand parent = (PluginCommand)this.commandMap.getCommand(subCommandInfo.parent());
        if (parent == null) {
          throw new CommandRegistrationException("Attempted to register sub-command of " + subCommandInfo.parent() + " before main handler.");
        }
        parent.registerSubCommandHandler(subCommandInfo.name(), new PluginSubCommand(executor, method, tabInfo == null ? new String[0] : tabInfo.value()));
      }
    }
  }
}
