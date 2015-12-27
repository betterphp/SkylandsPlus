package uk.co.jacekk.bukkit.baseplugin.conversation;

import org.bukkit.command.CommandSender;

public abstract class Node<C extends Conversation<? extends CommandSender>, T extends CommandSender>
{
  private C convo;
  private boolean recurring;
  
  protected Node(C convo)
  {
    this.convo = convo;
    this.recurring = true;
  }
  
  protected C getConversation()
  {
    return this.convo;
  }
  
  public boolean isRecurring()
  {
    return this.recurring;
  }
  
  protected void setRecurring(boolean recurring)
  {
    this.recurring = recurring;
  }
  
  public abstract String getPromptText();
  
  public abstract Node<C, T> processInput(String paramString);
}
