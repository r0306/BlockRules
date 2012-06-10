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
        
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        else
        {
            System.out.println("[BlockRules] You must be a player to use this command!");
            return true;
        }
        
        if (args.length == 1) {
            
            mainMenu(player);
            
        }
        
    }
    
    public void mainMenu(Player player) {
        
        player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.DARK_GRAY + "This server is running Version 1.0.");
        player.sendMessage(ChatColor.DARK_AQUA + "For a list of commands and help, type " + ChatColor.DARK_PURPLE + "/br help" + ChatColor.DARK_AQUA + ".");
            
    }
    
    public void helpMenu(Player player) {
        
        player.sendMessage(ChatColor.DARK_AQUA + "[BlockRules] " + ChatColor.GREEN + "Below is a list of commands for ops.");
        player.sendMessage(null);
        
    }
    
    
    
}
