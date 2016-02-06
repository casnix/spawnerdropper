/*File SpawnerStack.java - Part of SpawnerDropper
 * Version 0.0.1
 * Copyright (c) Matt Rienzo, 2016
 * Licensed under GPLv2.  See license in src/doc/LICENSE or src/doc/LICENSE.txt
 */
package io.github.casnix.spawnerdropper;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class SpawnerStack {
	// Returns how many of what kind of spawners are in the game from SpawnerDropper
	public static int GetSpawnersInService(String spawnerType){
		try{
			// Read entire ./SpawnerDropper.SpawnerStack.json into a string
			String spawnerStack = new String(Files.readAllBytes(Paths.get("./plugins/SpawnerDropper/SpawnerStack.json")));
			
			// get the value of JSON->{spawnerType}
			JSONParser parser = new JSONParser();
				
			Object obj = parser.parse(spawnerStack);
			
			JSONObject jsonObj = (JSONObject) obj;
			
			int number = (Integer) jsonObj.get(spawnerType);
			
			return number;
		}catch(ParseException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught ParseException in GetSpawnersInService(String)");
			e.printStackTrace();
			
			return -1;
		}catch(FileNotFoundException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Could not find ./plugins/SpawnerDropper/SpawnerStack.json");
			e.printStackTrace();
			
			return -1;
		}catch(IOException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught IOException in GetSpawnersInService(String)");
			e.printStackTrace();
			
			return -1;
		}
		
	}
	
	// Takes a spawner out of service when one of our spawners are placed
	public static boolean TakeSpawnerOutOfService(String spawnerType){
		// Read ./SpawnerDropper.SpawnerStack.json into a string
		
		// get the value of JSON->{spawnerType} and subtract 1 to it unless it is 0.
		// If it's zero, return false
		
		// Write file back to disk
		try{
			// Read entire ./SpawnerDropper.SpawnerStack.json into a string
			String spawnerStack = new String(Files.readAllBytes(Paths.get("./plugins/SpawnerDropper/SpawnerStack.json")));
			
			// get the value of JSON->{spawnerType}
			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(spawnerStack);
			
			JSONObject jsonObj = (JSONObject) obj;
			
			int numberInService = (Integer) jsonObj.get(spawnerType);
			
			if(numberInService <= 0){
				return false;
			}else{
				numberInService -= 1;
				jsonObj.put(spawnerType, new Integer(numberInService));
				
				FileWriter file = new FileWriter("./plugins/SpawnerDropper/SpawnerStack.json");
				file.write(jsonObj.toJSONString());
				file.flush();
				file.close();
			}
		}catch(ParseException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught ParseException in TakeSpawnerOutOfService(String)");
			e.printStackTrace();
			
			return false;
		}catch(FileNotFoundException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Could not find ./plugins/SpawnerDropper/SpawnerStack.json");
			e.printStackTrace();
			
			return false;
		}catch(IOException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught IOException in TakeSpawnerOutOfService(String)");
			e.printStackTrace();
			
			return false;
		}
		
		return false;
	}

	// Puts a spawner into service when one is broken
	public static boolean PutSpawnerIntoService(String spawnerType){
		// Read ./SpawnerDropper.SpawnerStack.json into a string
		
		// get the value of JSON->{spawnerType} and add 1 to it.
		
		// Write file back to disk
		try{
			// Read entire ./SpawnerDropper.SpawnerStack.json into a string
		String spawnerStack = new String(Files.readAllBytes(Paths.get("./plugins/SpawnerDropper/SpawnerStack.json")));
			
			// get the value of JSON->{spawnerType}
			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(spawnerStack);
			
			JSONObject jsonObj = (JSONObject) obj;
				
			int numberInService = (Integer) jsonObj.get(spawnerType);
				
			if(numberInService < 0){
				numberInService = 0;
			}
			
			numberInService += 1;
			jsonObj.put(spawnerType, new Integer(numberInService));
						
			FileWriter file = new FileWriter("./plugins/SpawnerDropper/SpawnerStack.json");
			file.write(jsonObj.toJSONString());
			file.flush();
			file.close();
			
		}catch(ParseException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught ParseException in PutSpawnerIntoService(String)");
			e.printStackTrace();
			
			return false;
		}catch(FileNotFoundException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Could not find ./plugins/SpawnerDropper/SpawnerStack.json");
			e.printStackTrace();
					
			return false;
		}catch(IOException e){
			Bukkit.getLogger().warning("[SpawnerDropper] Caught IOException in PutSpawnerIntoService(String)");
			e.printStackTrace();
					
			return false;
		}
		
		return false;
	}
}
