/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.r0306.BlockRules;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author r0306
 */
public class BlockRules extends JavaPlugin {
    
	private Executor cmd;
	
    public void onEnable() 
    {
     
    	loadConfig();
        getServer().getPluginManager().registerEvents(new BlockInteractListener(this), this);
    	cmd = new Executor(this);
    	getCommand("br").setExecutor(cmd);
        System.out.println("[BlockRules] Version {" + getDescription().getVersion() + "] loaded.");
    
    }
    
    public void onDisable() 
    {
        
        System.out.println("[BlockRules] Version {" + getDescription().getVersion() + "] unloaded.");
        
    }
    
    public void loadConfig()
    {
    	
		FileConfiguration cfg = this.getConfig();
		FileConfigurationOptions cfgOptions = cfg.options();
		
		for (World w : Bukkit.getWorlds())
		{
			
			generateConfig(w.getName());
			
		}
    	
			
    }
    
    public void generateConfig(String worldName)
    {
    	
    	FileConfiguration cfg = this.getConfig();
    	String path = "Worlds." + worldName;
    	
    	cfg.addDefault(path + ".BlockRules", false);
    	List<String> starterList = new ArrayList<String>();
    	cfg.addDefault(path + ".Allow-Break", starterList);
    	cfg.addDefault(path + ".Allow-Place", starterList);
    	saveConfig();
    	
    }
    
}
