/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.r0306.BlockRules;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

/**
 *
 * @author MRT253
 */
public class Executor implements CommandExecutor {
	
	private BlockRules plugin;
	
	public Executor(BlockRules plugin)
	{
		
		this.plugin = plugin;
		
	}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        
    	Configuration config = plugin.getConfig();
    	
    	Player player = null;
        
        if (sender instanceof Player) 
        {
            
            player = (Player) sender;
        
        }
        else
        {
            
            System.out.println("[BlockRules] You must be a player to use this command!");
            return true;
        
        }
        
        if (cmd.getName().equalsIgnoreCase("br") || cmd.getName().equalsIgnoreCase("blockrules") || cmd.getName().equalsIgnoreCase("blockrule"))
        {
        	
	        if (args.length == 0) 
	        {
	            
	            mainMenu(player);
	            
	        }
	        
	        else if (args.length == 1)
	        {
	        
	            if (args[0].equalsIgnoreCase("help"))
	            {
	                
	                helpMenu(player);
	                
	            }
	            
		        else
		        {
		        	
		        	if (checkPerms(player, "br.configure")) return true;
		        	player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "Unknown command or not enough args. Type " + ChatColor.DARK_PURPLE + "/br help" + ChatColor.RED + ".");
		        	
		        }
	            
	        }
	        
	        else if (args.length == 2)
	        {
	        	
	        	if (args[0].equalsIgnoreCase("toggle"))
	        	{
	        		
	        		if (checkPerms(player, "br.toggle")) return true;
	        		toggleWorld(args[1], player);
	        		
	        	}
	        	
		        else
		        {
		        	
		        	if (checkPerms(player, "br.configure")) return true;
		        	player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "Unknown command or not enough args. Type " + ChatColor.DARK_PURPLE + "/br help" + ChatColor.RED + ".");
		        	
		        }
	        	
	        }
	        
	        else if (args.length == 3)
	        {
	        	
	        	if (args[0].equalsIgnoreCase("allowplace"))
	        	{
	        		
	        		if (checkPerms(player, "br.configure")) return true;
	        		allowBlockPlace(args[1], args[2], player);
	        		
	        	}
	        	
	        	else if (args[0].equalsIgnoreCase("disallowplace"))
	        	{
	        		
	        		if (checkPerms(player, "br.configure")) return true;
	        		disallowBlockPlace(args[1], args[2], player);
	        		
	        	}
	        	
	        	else if (args[0].equalsIgnoreCase("allowbreak"))
	        	{
	        		
	        		if (checkPerms(player, "br.configure")) return true;
	        		allowBlockBreak(args[1], args[2], player);
	        		
	        	}
	        	
	        	else if (args[0].equalsIgnoreCase("disallowbreak"))
	        	{
	        		
	        		if (checkPerms(player, "br.configure")) return true;
	        		disallowBlockBreak(args[1], args[2], player);
	        		
	        	}
	        	
		        else
		        {
		        	
		        	if (checkPerms(player, "br.configure")) return true;
		        	player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "Unknown command or not enough args. Type " + ChatColor.DARK_PURPLE + "/br help" + ChatColor.RED + ".");
		        	
		        }
	        	
	        }
	        
	        else
	        {
	        	
	        	player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "Unknown command or not enough args. Type " + ChatColor.DARK_PURPLE + "/br help" + ChatColor.RED + ".");
	        	
	        }
        
        }
        
        return true;
        
    }
    
    public void mainMenu(Player player)
    {
        
        player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GRAY + "This server is running Version 1.0.");
        player.sendMessage(ChatColor.DARK_AQUA + "For a list of commands and help, type " + ChatColor.DARK_PURPLE + "/br help" + ChatColor.DARK_AQUA + ".");
            
    }
    
    public void helpMenu(Player player)
    {
        
        player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.GREEN + "Below is a list of commands for ops:");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br allowplace (block) (world)" + ChatColor.GREEN + " Allows the placing of the block in the world where BlockRules is enabled.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br disallowplace (block) (world)" + ChatColor.GREEN + " Removes the block from the whitelist in the world where BlockRules is enabled.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br toggle (world)" + ChatColor.GREEN + " If enabled for a world, players won't be able to place/break blocks unless they are whitelisted.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br allowbreak (block) (world)" + ChatColor.GREEN + " Allows placing of the block in the world where BlockRules is enabled.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br disallowbreak (block) (world)" + ChatColor.GREEN + " Removes the block from the whitelist in a world where BlockRules is enabled.");        
        
    }
    
    public void toggleWorld(String worldName, Player player)
    {
    	
    	Configuration config = plugin.getConfig();
    	World world = Bukkit.getWorld(worldName);
    	String path = "Worlds." + worldName;
    	
    	if (world != null)
    	{
    		
    		if (!config.contains(path)) {
    			
    			generateConfig(worldName);
    		
    		}
    			
			if (config.getBoolean(path + ".BlockRules") == true)
			{
				
    			config.set(path + ".BlockRules", false);
    			player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GREEN + "BlockRules disabled for world: " + ChatColor.YELLOW + worldName);
				
			}
			
			else
			{
				
				config.set(path + ".BlockRules", true);
				player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GREEN + "BlockRules enabled for world: " + ChatColor.YELLOW + worldName);
				
			}
    			
    	}
    	
    	else
    	{
    		
    		player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "World does not exist.");
    		
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
    
    public void allowBlockPlace(String blockName, String worldName, Player player)
    {
    	
    	World world = Bukkit.getWorld(worldName);
    	String path = "Worlds." + worldName;
    	blockName = blockName.toUpperCase();
    	
    	if (checkBlock(blockName) == false)
    	{
    		
    		invalidBlock(player);
    		return;
    		
    	}
    	
    	if (world != null)
    	{
    		
        	Configuration config = plugin.getConfig();
    		if (!config.contains(path)) {
    			
    			generateConfig(worldName);
    		
    		}
    		
    		List<String> allowPlace = getList(worldName, false);
    		allowPlace.add(blockName);
    		saveList(worldName, allowPlace, false);
    		player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GREEN + "Added block: " + ChatColor.YELLOW + blockName + ChatColor.DARK_GREEN + " to placing whitelist in world: " + ChatColor.YELLOW + worldName);
    		
    	}
    	
    	
    }
    
    public void allowBlockBreak(String blockName, String worldName, Player player)
    {
    	
    	Configuration config = plugin.getConfig();
    	World world = Bukkit.getWorld(worldName);
    	String path = "Worlds." + worldName;
    	blockName = blockName.toUpperCase();
    	
    	if (checkBlock(blockName) == false)
    	{
    		
    		invalidBlock(player);
    		return;
    		
    	}
    	
    	if (world != null)
    	{
    		
    		if (!config.contains(path)) {
    			
    			generateConfig(worldName);
    		
    		}
    		
    		List<String> allowBreak = getList(worldName, true);
    		allowBreak.add(blockName);
    		saveList(worldName, allowBreak, true);
    		player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GREEN + "Added block: " + ChatColor.YELLOW + blockName + ChatColor.DARK_GREEN + " to breaking whitelist in world: " + ChatColor.YELLOW + worldName);
    		
    	}
    	
    }
    
    public void disallowBlockPlace(String blockName, String worldName, Player player)
    {
    	
    	Configuration config = plugin.getConfig();
    	World world = Bukkit.getWorld(worldName);
    	String path = "Worlds." + worldName;
    	blockName = blockName.toUpperCase();
    	
    	if (checkBlock(blockName) == false)
    	{
    		
    		invalidBlock(player);
    		return;
    		
    	}
    	
    	if (world != null)
    	{
    		
    		if (!config.contains(path)) {
    			
    			generateConfig(worldName);
    		
    		}
    		
    		List<String> allowPlace = getList(worldName, false);
    		
    		if (allowPlace.contains(blockName))
    		{
    			
    			allowPlace.remove(blockName);
        		saveList(worldName, allowPlace, false);
        		player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GREEN + "Removed block: " + ChatColor.YELLOW + blockName + ChatColor.DARK_GREEN + " from placing whitelist in world: " + ChatColor.YELLOW + worldName);
    			
    		}
    		
    		else
    		{
    			
    			player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "Block was not allowed in the first place.");
    			
    		}
    		
    		
    	}
    	
    }
    
    public void disallowBlockBreak(String blockName, String worldName, Player player)
    {
    	
    	Configuration config = plugin.getConfig();
    	World world = Bukkit.getWorld(worldName);
    	String path = "Worlds." + worldName;
    	blockName = blockName.toUpperCase();
    	
    	if (checkBlock(blockName) == false)
    	{
    		
    		invalidBlock(player);
    		return;
    		
    	}
    	
    	if (world != null)
    	{
    		
    		if (!config.contains(path)) {
    			
    			generateConfig(worldName);
    		
    		}
    		
    		List<String> allowBreak = getList(worldName, true);
    		
    		if (allowBreak.contains(blockName))
    		{
    			
        		allowBreak.remove(blockName);
        		saveList(worldName, allowBreak, true);
        		player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GREEN + "Removed block: " + ChatColor.YELLOW + blockName + ChatColor.DARK_GREEN + " from breaking whitelist in world: " + ChatColor.YELLOW + worldName);
    			
    		}

    		else
    		{
    			
    			player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "Block was not allowed in the first place.");
    			
    		}
    		
    	}
    	
    }
    
    public List<String> getList(String worldName, boolean type)
    {

    	Configuration config = plugin.getConfig();
    	String path = "Worlds." + worldName;
    	List<String> list = new ArrayList<String>();
    	
    	if (type)
    	{
    	
    		list = config.getStringList(path + ".Allow-Break");
    				
    	}
    	
    	else
    	{
    		
    		list = config.getStringList(path + ".Allow-Place");
    		
    	}
    	
    	return list;
    	
    }
    
    public void saveList(String worldName, List<String> list, boolean type)
    {
    	
    	Configuration config = plugin.getConfig();
    	String path = "Worlds." + worldName;
    	
    	if (type)
    	{
    		
        	config.set(path + ".Allow-Break", list);
        	
    	}
    	
    	else
    	{
    		
    		config.set(path + ".Allow-Place", list);
    		
    	}
    	
    	plugin.saveConfig();
    	
    }
    
    public boolean checkBlock(String blockName)
    {
    	
    	return Material.getMaterial(blockName) != null;
    	
    }
    
    public void invalidBlock(Player player)
    {
    	
    	player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.RED + "Invalid block name.");
    	player.sendMessage(ChatColor.RED + "You can check the list of valid block names here: " + ChatColor.AQUA + "http://jd.bukkit.org/apidocs/org/bukkit/Material.html");
    	
    }
    
    public boolean checkPerms(Player player, String perm)
    {
    	
    	if (!player.hasPermission(perm))
		{
	
    		player.sendMessage(ChatColor.RED + "You do not have permission!");
    			
		}
    	
    	return !player.hasPermission(perm);
    	
    }
    
    
}
