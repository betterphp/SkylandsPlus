package uk.co.jacekk.bukkit.SkylandsPlus;

import uk.co.jacekk.bukkit.baseplugin.config.PluginConfigKey;

public enum Config implements PluginConfigKey {
	
	PREVENT_SAND_FALLING(		"prevent-sand-falling",		true),
	RESTRICT_MOB_SPAWNING(		"restrict-mob-spawning",	true);
	
	private String key;
	private Object defaultValue;
	
	private Config(String key, Object defaultValue){
		this.key = key;
		this.defaultValue = defaultValue;
	}
	
	public Object getDefault(){
		return this.defaultValue;
	}
	
	public String getKey(){
		return this.key;
	}
	
}
