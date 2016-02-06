package io.github.casnix.spawnerdropper;

public class ConfigJSON {
	int Percent_chance_drop_from_TNT_explosion;
	int Percent_chance_drop_from_creeper_explosion;
	
	public int getTNTChance(int chance){
		return Percent_chance_drop_from_TNT_explosion;
	}
	
	public int getCreeperChance(int chance){
		return Percent_chance_drop_from_creeper_explosion;
	}
	
}
