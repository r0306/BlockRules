/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.r0306.BlockRules;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 *
 * @author MRT253
 */
public class BlockPlaceListener implements Listener {
    
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        
        Player player = event.getPlayer();
        Block block = event.getBlock();
        
        
    }
    
}
