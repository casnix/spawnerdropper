/*File BlockEvent.java - Part of SpawnerDropper
 * Version 0.0.1
 * Copyright (c) Matt Rienzo, 2016
 * Licensed under GPLv2.  See license in src/doc/LICENSE or src/doc/LICENSE.txt
 */
package io.github.casnix.spawnerdropper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import io.github.casnix.spawnerdropper.Config;
import io.github.casnix.spawnerdropper.SpawnerStack;


public class BlockEvent implements Listener{
	// Handle a creeper explosion
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onEntityExplode(EntityExplodeEvent event){
		List<Block> destroyedBlocks = event.blockList();
		
		int maxPercent = 100;
		int percentDrop = 101;
		int rn;
		
		String entType = event.getEntityType().toString();
		Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lEvent entity type = \u00A7a"+entType);
		
		for(Block block : destroyedBlocks){
			if(block.getType() == Material.MOB_SPAWNER && (event.getEntityType().toString().equals("PRIMED_TNT") )){
				Random random = new Random();
				rn = random.nextInt(maxPercent);
				
				CreatureSpawner spawnerBlock = (CreatureSpawner) block.getState();
				String spawnerCreature = spawnerBlock.getCreatureTypeName();
				EntityType spawnerEntityID = spawnerBlock.getSpawnedType();
				
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lBroke spawner of type = \u00A7a"+spawnerCreature);
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lBroke spawner of type = \u00A7a"+spawnerEntityID);
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lExploding entity of = \u00A7a"+event.getEntity().getEntityId());
				
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lMeasuring percentile from configuration");
				percentDrop = Config.GetTNTChance(); //Will be 45
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lDone");
				
				if(rn < percentDrop){
					int closestPlayer = 0;
					
					// Get all entities within 15 blocks.
					List<Entity> nearByEntities = event.getEntity().getNearbyEntities(15, 15, 15);
					for(Entity myEntity : nearByEntities){
						if(myEntity instanceof Player){ // Is it a player?
							closestPlayer = 1; 
							
							// Check if player's inventory is full
							int hasSpace = ((Player) myEntity).getInventory().firstEmpty();
							
							if(hasSpace != -1){
								ItemStack newSpawnerBlock = new ItemStack(Material.MOB_SPAWNER, 1);
								ItemMeta itemMeta = newSpawnerBlock.getItemMeta();
								itemMeta.setDisplayName(spawnerCreature);
								itemMeta.setLore(Arrays.asList("SpawnerDropper", "Generated block", "Moo goes the truck"));
								
								newSpawnerBlock.setItemMeta(itemMeta);
																
								((Player) myEntity).getInventory().addItem(newSpawnerBlock);
								
								Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lPutting onto stack");
								boolean success = SpawnerStack.PutSpawnerIntoService(spawnerCreature);
								Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lDone");
								if(!success){ // Failed to write to our file
									((Player) myEntity).sendMessage("\u00A7e[SpawnerDropper] : Failed to add spawner to plugin service");
								}
							}
						}
						
						if(closestPlayer == 1){
							break;
						}
					}
				}
			}else if(block.getType() == Material.MOB_SPAWNER && (event.getEntityType().toString().equals("CREEPER"))){
				Random random = new Random();
				rn = random.nextInt(maxPercent);
				
				CreatureSpawner spawnerBlock = (CreatureSpawner) block.getState();
				String spawnerCreature = spawnerBlock.getCreatureTypeName();
				EntityType spawnerEntityID = spawnerBlock.getSpawnedType();
				
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lBroke spawner of type = \u00A7a"+spawnerCreature);
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpawnerDropper\u00A7e]! \u00A7c\u00A7lBroke spawner of type = \u00A7a"+spawnerEntityID);
				Bukkit.getServer().broadcastMessage("\u00A7e[\u00A74SpddawnerDropper\u00A7e]! \u00A7c\u00A7lExploding entity of = \u00A7a"+event.getEntity().getEntityId());
				
				percentDrop = Config.GetCreeperChance(); // Will be 10
				
				if(rn < percentDrop){
					int closestPlayer = 0;
					
					// Get all entities within 15 blocks.
					List<Entity> nearByEntities = event.getEntity().getNearbyEntities(15, 15, 15);
					for(Entity myEntity : nearByEntities){
						if(myEntity instanceof Player){ // Is it a player?
							closestPlayer = 1; 
							
							// Check if player's inventory is full
							int hasSpace = ((Player) myEntity).getInventory().firstEmpty();
							
							if(hasSpace != -1){
								ItemStack newSpawnerBlock = new ItemStack(Material.MOB_SPAWNER, 1);
								ItemMeta itemMeta = newSpawnerBlock.getItemMeta();
								itemMeta.setDisplayName(spawnerCreature);
								itemMeta.setLore(Arrays.asList("SpawnerDropper", "Generated block", "Moo goes the truck"));
								
								newSpawnerBlock.setItemMeta(itemMeta);
								
								((Player) myEntity).getInventory().addItem(newSpawnerBlock);
								
								boolean success = SpawnerStack.PutSpawnerIntoService(spawnerCreature);
								if(!success){ // Failed to write to our file
									((Player) myEntity).sendMessage("\u00A7e[SpawnerDropper] : Failed to add spawner to plugin service");
								}
							}
						}
						
						if(closestPlayer == 1){
							break;
						}
					}
					
				}
			}
		}
		
	}

	// Handle when a player places one of our spa
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Block placedBlock = event.getBlockPlaced();
		Player player = event.getPlayer();
		
		boolean isOurBlock = false;
		
		if(placedBlock.getType().equals(Material.MOB_SPAWNER)){
			if(player.getItemInHand().hasItemMeta()){
				if(player.getItemInHand().getItemMeta().hasLore()){
					List<String> lore = player.getItemInHand().getItemMeta().getLore();
					
					int i = 1;
					for(String line : lore){
						if(i == 1){
							if(line.equals("SpawnerDropper") && player.getItemInHand().getItemMeta().hasDisplayName()){
								isOurBlock = true;
								break;
							}
							
							i++;
							break;
						}else{
							break;
						}
					}
				}
			}
		}
		
		if(isOurBlock){
			int spawnersInService = SpawnerStack.GetSpawnersInService(player.getItemInHand().getItemMeta().getDisplayName());
			
			if(spawnersInService > 0){
				CreatureSpawner ourSpawner = (CreatureSpawner) placedBlock.getState();
				
				ourSpawner.setCreatureTypeByName(player.getItemInHand().getItemMeta().getDisplayName());
				
				boolean success = SpawnerStack.TakeSpawnerOutOfService(player.getItemInHand().getItemMeta().getDisplayName());
				
				if(!success){
					Bukkit.getLogger().info("[SpawnerDropper] Failed to take spawner out of service");
				}
			}
		}
	}
	
}
