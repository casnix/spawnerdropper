/*File Main.java - Part of SpawnerDropper
 * Version 0.0.1
 * Copyright (c) Matt Rienzo, 2016
 * Licensed under GPLv2.  See license in src/doc/LICENSE or src/doc/LICENSE.txt
 */
package io.github.casnix.spawnerdropper;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.casnix.spawnerdropper.Versioning;

public class Main extends JavaPlugin{

	@Override
	public void onEnable(){
		getLogger().info("SpawnerDropper ["+Versioning.CurrentVersion()+"] Copyright (c) Matt Rienzo, 2016");
		
		getServer().getPluginManager().registerEvents(new BlockEvent(),  this);
		
	}
	
	@Override
	public void onDisable(){
		
	}
}
