/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.r0306.BlockRules;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author MRT253
 */
public class Executor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        
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
        
        if (args.length == 1) 
        {
            
            mainMenu(player);
            
        }
        else if (args.length == 2)
        {
        
            if (args[0].equalsIgnoreCase("help"))
            {
                
                helpMenu(player);
                
            }
            
        }
        
    }
    
    public void mainMenu(Player player) {
        
        player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GRAY + "This server is running Version 1.0.");
        player.sendMessage(ChatColor.DARK_AQUA + "For a list of commands and help, type " + ChatColor.DARK_PURPLE + "/br help" + ChatColor.DARK_AQUA + ".");
            
    }
    
    public void helpMenu(Player player) {
        
        player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.GREEN + "Below is a list of commands for ops.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br allowplace (block) (world)" + ChatColor.GOLD + " Allows the placing of the block in the world where BlockRules is enabled.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br disallowplace (block) (world)" + ChatColor.GOLD + " Removes the block from the whitelist in the world where BlockRules is enabled.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br toggle (world)" + ChatColor.GREEN + " If enabled for a world, players won't be able to place/break blocks unless they are whitelisted.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br allowbreak (block) (world)" + ChatColor.GREEN + " Allows placing of the block in the world where BlockRules is enabled.");
        player.sendMessage(ChatColor.DARK_PURPLE + "/br disallowbreak (block) (world)" + ChatColor.GREEN + " Removes the block from the whitelist in a world where BlockRules is enabled.");        
        
    }
    
    
    
}
