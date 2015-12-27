package uk.co.jacekk.bukkit.baseplugin.update;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.bukkit.plugin.Plugin;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.co.jacekk.bukkit.baseplugin.util.StringUtils;

public class BukkitDevUpdateChecker
  extends UpdateChecker
{
  public BukkitDevUpdateChecker(Plugin plugin, String url)
  {
    super(plugin, url);
  }
  
  public boolean updateNeeded()
  {
    try
    {
      InputStream input = this.filesFeed.openConnection().getInputStream();
      Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);
      
      Node latestFile = document.getElementsByTagName("item").item(0);
      NodeList children = latestFile.getChildNodes();
      
      this.version = children.item(1).getTextContent().replaceAll("[a-zA-Z ]", "");
      this.link = children.item(3).getTextContent();
      
      input.close();
      
      input = new URL(this.link).openConnection().getInputStream();
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().startsWith("<li class=\"user-action user-action-download\">")) {
          this.jarLink = line.substring(line.indexOf("href=\"") + 6, line.lastIndexOf("\""));
        }
      }
      reader.close();
      input.close();
      if (StringUtils.versionCompare(this.plugin.getDescription().getVersion(), this.version) == -1) {
        return true;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
}
