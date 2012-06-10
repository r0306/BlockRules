/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.r0306.BlockRules;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author r0306
 */
public class BlockRules extends JavaPlugin {
    
    public void onEnable() {
     
        System.out.println("[BlockRules] Version {" + getDescription().getVersion() + "] loaded.");
    
    }
    
    public void onDisable() {
        
        System.out.println("[BlockRules] Version {" + getDescription().getVersion() + "] unloaded.");
        
    }
    
}
