package uk.co.jacekk.bukkit.baseplugin.conversation;

import org.bukkit.command.CommandSender;
import uk.co.jacekk.bukkit.baseplugin.BasePlugin;

public abstract class Conversation<T extends CommandSender>
{
  private BasePlugin plugin;
  private T with;
  private Node<? extends Conversation<? extends CommandSender>, T> nextNode;
  private boolean suppressChat;
  
  public Conversation(BasePlugin plugin, T with)
  {
    this.plugin = plugin;
    this.with = with;
    
    this.nextNode = null;
    this.suppressChat = false;
  }
  
  public T getWith()
  {
    return this.with;
  }
  
  public void setStartNode(Node<? extends Conversation<? extends CommandSender>, T> node)
  {
    if (this.nextNode != null) {
      throw new IllegalStateException("Start node already set");
    }
    this.nextNode = node;
  }
  
  public boolean isSuppressingChat()
  {
    return this.suppressChat;
  }
  
  public void setSuppressChat(boolean suppress)
  {
    this.suppressChat = suppress;
  }
  
  public boolean isEnded()
  {
    return this.nextNode == null;
  }
  
  public void start()
    throws IllegalStateException
  {
    if (this.nextNode == null) {
      throw new IllegalStateException("Start node not set");
    }
    String prompt = this.nextNode.getPromptText();
    if (prompt != null) {
      getWith().sendMessage(prompt);
    }
    this.plugin.conversationManager.addConversation(this);
  }
  
  public void abort()
  {
    onAbort();
    
    this.plugin.conversationManager.removeConversation(this);
  }
  
  public abstract void onAbort();
  
  public void onInput(String input)
  {
    Node<? extends Conversation<? extends CommandSender>, T> node = this.nextNode.processInput(input);
    if ((node != null) && ((!node.isRecurring()) || (!node.getClass().equals(this.nextNode.getClass()))))
    {
      String prompt = node.getPromptText();
      if (prompt != null) {
        getWith().sendMessage(prompt);
      }
    }
    this.nextNode = node;
  }
}
