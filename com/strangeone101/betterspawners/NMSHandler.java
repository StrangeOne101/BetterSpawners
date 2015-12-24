package com.strangeone101.betterspawners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

public class NMSHandler 
{
	/**Sets the spawner to the mob passed*/
	public static boolean setSpawner(Block block, EntityType entity)
	{
		if (block.getType() != Material.MOB_SPAWNER) return false;
		if (getVersion() == -1) return false;
		int v = getVersion();
		if (v == 0) ((org.bukkit.craftbukkit.v1_8_R1.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else if (v == 1) ((org.bukkit.craftbukkit.v1_8_R2.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else if (v == 2) ((org.bukkit.craftbukkit.v1_8_R3.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else if (v == 3) ((org.bukkit.craftbukkit.v1_8_R4.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else if (v == 4) ((org.bukkit.craftbukkit.v1_8_R5.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else return false;
		return true;
	}
	
	public static EntityType getType(Block block)
	{
		if (block.getType() != Material.MOB_SPAWNER) return null;
		if (getVersion() == -1) return null;
		int v = getVersion();
		if (v == 0) return ((org.bukkit.craftbukkit.v1_8_R1.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else if (v == 1) return ((org.bukkit.craftbukkit.v1_8_R2.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else if (v == 2) return ((org.bukkit.craftbukkit.v1_8_R3.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else if (v == 3) return ((org.bukkit.craftbukkit.v1_8_R4.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else if (v == 4) return ((org.bukkit.craftbukkit.v1_8_R5.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else return null;
	}
	
	/**
	 * Returns the version of Spigot. v1_8_R1 = 0, v1_8_R2 = 1, etc and -1 for no correct version found.
	 * */
	public static int getVersion()
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
        if (version.equals("v1_8_R1")) return 0;
        else if (version.equals("v1_8_R2")) return 1;
        else if (version.equals("v1_8_R3")) return 2;
        else if (version.equals("v1_8_R4")) return 3;
        else if (version.equals("v1_8_R5")) return 4;
        else return -1;
	}
}
