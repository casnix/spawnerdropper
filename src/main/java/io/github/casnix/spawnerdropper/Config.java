/*File Config.java - Part of SpawnerDropper
 * Version 0.0.1
 * Copyright (c) Matt Rienzo, 2016
 * Licensed under GPLv2.  See license in src/doc/LICENSE or src/doc/LICENSE.txt
 */
package io.github.casnix.spawnerdropper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public final class Config {
	public static int GetTNTChance(){
		try{
			// Read entire ./SpawnerDropper/SpawnerDropperConfig.json into a string
			System.out.println("[SD DEBUG] 1");
			String configTable = new String(Files.readAllBytes(Paths.get("./plugins/SpawnerDropper/SpawnerDropperConfig.json")));
			
			// get the value of JSON->{spawnerType}
			System.out.println("[SD DEBUG] 2");
			JSONParser parser = new JSONParser();
			
			System.out.println("[SD DEBUG] 3");
			Object obj = parser.parse(configTable);
			
			System.out.println("[SD DEBUG] 4");
			JSONObject jsonObj = (JSONObject) obj;
			
			System.out.println("[SD DEBUG] 5");
			String chance = (String) jsonObj.get("Percent_chance_drop_from_TNT_explosion");
			
			System.out.println("[SD DEBUG] 6");
			int ret = Integer.valueOf(chance);
			
			System.out.println("[SD DEBUG] 7");
			return ret;
			//System.out.println("[SD DEBUG] 6");
			//return chance;
		}catch(ParseException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught ParseException in GetTNTChance(void)");
			e.printStackTrace();
			
			return -1;
		}catch(FileNotFoundException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Could not find ./plugins/SpawnerDropper/SpawnerDropperConfig.json");
			e.printStackTrace();
			
			return -1;
		}catch(IOException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught IOException in GetTNTChance(void)");
			e.printStackTrace();
			
			return -1;
		}
	}

	public static int GetCreeperChance(){
		try{
			// Read entire ./SpawnerDropper/SpawnerDropperConfig.json into a string
			System.out.println("[SD DEBUG] 7");
			String configTable = new String(Files.readAllBytes(Paths.get("./plugins/SpawnerDropper/SpawnerDropperConfig.json")));
			
			// get the value of JSON->{spawnerType}
			System.out.println("[SD DEBUG] 8");
			JSONParser parser = new JSONParser();
			
			System.out.println("[SD DEBUG] 9");
			Object obj = parser.parse(configTable);
			
			System.out.println("[SD DEBUG] 10");
			JSONObject jsonObj = (JSONObject) obj;
			
			System.out.println("[SD DEBUG] 11");
			int chance = (Integer) jsonObj.get("Percent chance drop from creeper explosion");
			System.out.println("[SD DEBUG] 12");
			return chance;
		}catch(ParseException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught ParseException in GetCreeperChance(void)");
			e.printStackTrace();
			
			return -1;
		}catch(FileNotFoundException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Could not find ./plugins/SpawnerDropper/SpawnerDropperConfig.json");
			e.printStackTrace();
			
			return -1;
		}catch(IOException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught IOException in GetCreeperChance(void)");
			e.printStackTrace();
			
			return -1;
		}
	}
}
