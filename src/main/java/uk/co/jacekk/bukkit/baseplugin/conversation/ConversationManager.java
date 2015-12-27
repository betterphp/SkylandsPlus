package uk.co.jacekk.bukkit.baseplugin.conversation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import uk.co.jacekk.bukkit.baseplugin.BasePlugin;
import uk.co.jacekk.bukkit.baseplugin.event.BaseListener;

public class ConversationManager
  extends BaseListener<BasePlugin>
{
  private List<Conversation<? extends CommandSender>> conversations;
  
  public ConversationManager(BasePlugin plugin)
  {
    super(plugin);
    
    this.conversations = Collections.synchronizedList(new ArrayList<Conversation<? extends CommandSender>>());
  }
  
  public void addConversation(Conversation<? extends CommandSender> convo)
  {
    synchronized (this.conversations)
    {
      this.conversations.add(convo);
    }
  }
  
  public void removeConversation(Conversation<? extends CommandSender> convo)
  {
    synchronized (this.conversations)
    {
      this.conversations.remove(convo);
    }
  }
  
  @EventHandler(priority=EventPriority.LOWEST)
  public void onPlayerChat(AsyncPlayerChatEvent event)
  {
    synchronized (this.conversations)
    {
      CommandSender sender = event.getPlayer();
      String message = event.getMessage();
      
      Iterator<Conversation<? extends CommandSender>> iterator = this.conversations.iterator();
      while (iterator.hasNext())
      {
        Conversation<? extends CommandSender> conversation = (Conversation<? extends CommandSender>)iterator.next();
        if (conversation.getWith().equals(sender))
        {
          conversation.onInput(message);
          event.setCancelled(true);
          if (conversation.isEnded()) {
            iterator.remove();
          }
        }
        else if (conversation.isSuppressingChat())
        {
          Iterator<Player> recipients = event.getRecipients().iterator();
          while (recipients.hasNext())
          {
            Player recipient = (Player)recipients.next();
            if (conversation.getWith().equals(recipient)) {
              recipient.remove();
            }
          }
        }
      }
    }
  }
  
  @EventHandler(priority=EventPriority.LOWEST)
  public void onPlayerCommand(PlayerCommandPreprocessEvent event)
  {
    synchronized (this.conversations)
    {
      CommandSender sender = event.getPlayer();
      String message = event.getMessage();
      
      Iterator<Conversation<? extends CommandSender>> iterator = this.conversations.iterator();
      while (iterator.hasNext())
      {
        Conversation<? extends CommandSender> conversation = (Conversation<? extends CommandSender>)iterator.next();
        if (conversation.getWith().equals(sender))
        {
          conversation.onInput(message);
          event.setCancelled(true);
        }
        if (conversation.isEnded()) {
          iterator.remove();
        }
      }
    }
  }
  
  @EventHandler(priority=EventPriority.LOWEST)
  public void onServerCommand(ServerCommandEvent event)
  {
    synchronized (this.conversations)
    {
      CommandSender sender = event.getSender();
      String message = event.getCommand();
      
      Iterator<Conversation<? extends CommandSender>> iterator = this.conversations.iterator();
      while (iterator.hasNext())
      {
        Conversation<? extends CommandSender> conversation = (Conversation<? extends CommandSender>)iterator.next();
        if (conversation.getWith().equals(sender)) {
          conversation.onInput(message);
        }
        if (conversation.isEnded()) {
          iterator.remove();
        }
      }
    }
  }
}
