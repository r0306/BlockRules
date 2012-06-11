/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.r0306.BlockRules;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 *
 * @author MRT253
 */
public class BlockInteractListener implements Listener {
	
	private BlockRules plugin;
	
	public BlockInteractListener(BlockRules plugin)
	{
		
		this.plugin = plugin;
		
	}
	
    @EventHandler
    public void onPlace(BlockPlaceEvent event)
    {
    	
    	Configuration config = plugin.getConfig();
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();
        World world = location.getWorld();
        String path = "Worlds." + world.getName();
        
        if (player.hasPermission("br.exempt"))
        {
        	
        	return;
        	
        }
        
        if (!config.contains(path))
        {
        	
        	generateConfig(world.getName());
        	
        }
        
        if (config.getBoolean(path + ".BlockRules"))
        {
        	
        	if (!config.getStringList(path + ".Allow-Place").contains(block.getType().toString()))
        	{
        		
        		event.setCancelled(true);
        		
        	}
        	
        }
        
    }
    
    @EventHandler
    public void onBreak(BlockBreakEvent event)
    {
    	
    	Configuration config = plugin.getConfig();
    	Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();
        World world = location.getWorld();
        String path = "Worlds." + world.getName();
        
        if (player.hasPermission("br.exempt"))
        {
        	
        	return;
        	
        }
        
        if (!config.contains(path))
        {
        	
        	generateConfig(world.getName());
        	
        }
        
        if (config.getBoolean(path + ".BlockRules"))
        {
        	
        	if (!config.getStringList(path + ".Allow-Break").contains(block.getType().toString()))
        	{
        		
        		event.setCancelled(true);
        		
        	}
        	
        }
    	
    }
    
    public void generateConfig(String worldName)
    {
    	
    	Configuration config = plugin.getConfig();
    	String path = "Worlds." + worldName;
    	
    	config.set(path + ".BlockRules", false);
    	List<String> starterList = new ArrayList<String>();
    	config.set(path + ".Allow-Break", starterList);
    	config.set(path + ".Allow-Place", starterList);
    	plugin.saveConfig();
    	
    }
    
}
