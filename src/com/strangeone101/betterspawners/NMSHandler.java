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
		else if (v == 3) ((org.bukkit.craftbukkit.v1_9_R1.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else if (v == 4) ((org.bukkit.craftbukkit.v1_9_R2.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else if (v == 5) ((org.bukkit.craftbukkit.v1_10_R1.block.CraftCreatureSpawner) block.getState()).setSpawnedType(entity);
		else return false;
		return true;
	}
	
	/**Sets the spawner to the mob passed*/
	public static boolean setSpawnerEntityData(Block block, String key, Object obj)
	{
		if (block.getType() != Material.MOB_SPAWNER) return false;
		if (getVersion() == -1) return false;
		int v = getVersion();
		if (v == 0) {
			net.minecraft.server.v1_8_R1.TileEntity te = ((org.bukkit.craftbukkit.v1_8_R1.CraftWorld)block.getWorld()).getHandle().getTileEntity(new net.minecraft.server.v1_8_R1.BlockPosition(block.getX(), block.getY(), block.getZ()));
			if(te instanceof net.minecraft.server.v1_8_R1.TileEntityMobSpawner) {
				net.minecraft.server.v1_8_R1.TileEntityMobSpawner tems = (net.minecraft.server.v1_8_R1.TileEntityMobSpawner)te;
				net.minecraft.server.v1_8_R1.NBTTagCompound c2 = new net.minecraft.server.v1_8_R1.NBTTagCompound();
				tems.b(c2);
				net.minecraft.server.v1_8_R1.NBTTagCompound c = c2.getCompound("SpawnData");
			    if (c == null) c = new net.minecraft.server.v1_8_R1.NBTTagCompound();
			   	if (obj instanceof Boolean) c.setBoolean(key, (boolean) obj);
			   	else if (obj instanceof Integer) c.setInt(key, (int) obj);
			   	else if (obj instanceof String) c.setString(key, (String) obj);
			   	else if (obj instanceof Byte) c.setByte(key, (byte) obj);
			   	else if (obj instanceof Float) c.setFloat(key, (float) obj);
				c2.set("SpawnData", c);
			    tems.a(c2);
			}
		}
		else if (v == 1) {
			net.minecraft.server.v1_8_R2.TileEntity te = ((org.bukkit.craftbukkit.v1_8_R2.CraftWorld)block.getWorld()).getHandle().getTileEntity(new net.minecraft.server.v1_8_R2.BlockPosition(block.getX(), block.getY(), block.getZ()));
			if(te instanceof net.minecraft.server.v1_8_R2.TileEntityMobSpawner) {
				net.minecraft.server.v1_8_R2.TileEntityMobSpawner tems = (net.minecraft.server.v1_8_R2.TileEntityMobSpawner)te;
				net.minecraft.server.v1_8_R2.NBTTagCompound c2 = new net.minecraft.server.v1_8_R2.NBTTagCompound();
			    tems.b(c2);
			    net.minecraft.server.v1_8_R2.NBTTagCompound c = c2.getCompound("SpawnData");
			    if (c == null) c = new net.minecraft.server.v1_8_R2.NBTTagCompound();
			   	if (obj instanceof Boolean) c.setBoolean(key, (boolean) obj);
			   	else if (obj instanceof Integer) c.setInt(key, (int) obj);
			   	else if (obj instanceof String) c.setString(key, (String) obj);
			   	else if (obj instanceof Byte) c.setByte(key, (byte) obj);
			   	else if (obj instanceof Float) c.setFloat(key, (float) obj);
			   	
			   	c2.set("SpawnData", c);
			    tems.a(c2);
			}
		}
		else if (v == 2) {
			net.minecraft.server.v1_8_R3.TileEntity te = ((org.bukkit.craftbukkit.v1_8_R3.CraftWorld)block.getWorld()).getHandle().getTileEntity(new net.minecraft.server.v1_8_R3.BlockPosition(block.getX(), block.getY(), block.getZ()));
			if(te instanceof net.minecraft.server.v1_8_R3.TileEntityMobSpawner) {
				net.minecraft.server.v1_8_R3.TileEntityMobSpawner tems = (net.minecraft.server.v1_8_R3.TileEntityMobSpawner)te;
				net.minecraft.server.v1_8_R3.NBTTagCompound c2 = new net.minecraft.server.v1_8_R3.NBTTagCompound();
			    tems.b(c2);
			    net.minecraft.server.v1_8_R3.NBTTagCompound c = c2.getCompound("SpawnData");
			    if (c == null) c = new net.minecraft.server.v1_8_R3.NBTTagCompound();
			   	if (obj instanceof Boolean) c.setBoolean(key, (boolean) obj);
			   	else if (obj instanceof Integer) c.setInt(key, (int) obj);
			   	else if (obj instanceof String) c.setString(key, (String) obj);
			   	else if (obj instanceof Byte) c.setByte(key, (byte) obj);
			   	else if (obj instanceof Float) c.setFloat(key, (float) obj);
			   	
			   	c2.set("SpawnData", c);
			    tems.a(c2);
			}
		}
		else if (v == 3) {
			net.minecraft.server.v1_9_R1.TileEntity te = ((org.bukkit.craftbukkit.v1_9_R1.CraftWorld)block.getWorld()).getHandle().getTileEntity(new net.minecraft.server.v1_9_R1.BlockPosition(block.getX(), block.getY(), block.getZ()));
			if(te instanceof net.minecraft.server.v1_9_R1.TileEntityMobSpawner) {
				net.minecraft.server.v1_9_R1.TileEntityMobSpawner tems = (net.minecraft.server.v1_9_R1.TileEntityMobSpawner)te;
				net.minecraft.server.v1_9_R1.NBTTagCompound c2 = new net.minecraft.server.v1_9_R1.NBTTagCompound();
			    tems.save(c2);
			    net.minecraft.server.v1_9_R1.NBTTagCompound c = c2.getCompound("SpawnData");
			    if (c == null) c = new net.minecraft.server.v1_9_R1.NBTTagCompound();
			   	if (obj instanceof Boolean) c.setBoolean(key, (boolean) obj);
			   	else if (obj instanceof Integer) c.setInt(key, (int) obj);
			   	else if (obj instanceof String) c.setString(key, (String) obj);
			   	else if (obj instanceof Byte) c.setByte(key, (byte) obj);
			   	else if (obj instanceof Float) c.setFloat(key, (float) obj);
			   	
			   	c2.set("SpawnData", c);
			    tems.a(c2);
			}
		}
		else if (v == 4) {
			net.minecraft.server.v1_9_R2.TileEntity te = ((org.bukkit.craftbukkit.v1_9_R2.CraftWorld)block.getWorld()).getHandle().getTileEntity(new net.minecraft.server.v1_9_R2.BlockPosition(block.getX(), block.getY(), block.getZ()));
			if(te instanceof net.minecraft.server.v1_9_R2.TileEntityMobSpawner) {
				net.minecraft.server.v1_9_R2.TileEntityMobSpawner tems = (net.minecraft.server.v1_9_R2.TileEntityMobSpawner)te;
				net.minecraft.server.v1_9_R2.NBTTagCompound c2 = new net.minecraft.server.v1_9_R2.NBTTagCompound();
			    tems.save(c2);
			    net.minecraft.server.v1_9_R2.NBTTagCompound c = c2.getCompound("SpawnData");
			    if (c == null) c = new net.minecraft.server.v1_9_R2.NBTTagCompound();
			   	if (obj instanceof Boolean) c.setBoolean(key, (boolean) obj);
			   	else if (obj instanceof Integer) c.setInt(key, (int) obj);
			   	else if (obj instanceof String) c.setString(key, (String) obj);
			   	else if (obj instanceof Byte) c.setByte(key, (byte) obj);
			   	else if (obj instanceof Float) c.setFloat(key, (float) obj);
			   	
			   	c2.set("SpawnData", c);
			    tems.a(c2);
			}
		}
		else if (v == 5) {
			net.minecraft.server.v1_10_R1.TileEntity te = ((org.bukkit.craftbukkit.v1_10_R1.CraftWorld)block.getWorld()).getHandle().getTileEntity(new net.minecraft.server.v1_10_R1.BlockPosition(block.getX(), block.getY(), block.getZ()));
			if(te instanceof net.minecraft.server.v1_10_R1.TileEntityMobSpawner) {
				net.minecraft.server.v1_10_R1.TileEntityMobSpawner tems = (net.minecraft.server.v1_10_R1.TileEntityMobSpawner)te;
				net.minecraft.server.v1_10_R1.NBTTagCompound c2 = new net.minecraft.server.v1_10_R1.NBTTagCompound();
			    tems.save(c2);
			    net.minecraft.server.v1_10_R1.NBTTagCompound c = c2.getCompound("SpawnData");
			    if (c == null) c = new net.minecraft.server.v1_10_R1.NBTTagCompound();
			   	if (obj instanceof Boolean) c.setBoolean(key, (boolean) obj);
			   	else if (obj instanceof Integer) c.setInt(key, (int) obj);
			   	else if (obj instanceof String) c.setString(key, (String) obj);
			   	else if (obj instanceof Byte) c.setByte(key, (byte) obj);
			   	else if (obj instanceof Float) c.setFloat(key, (float) obj);
			   	
			   	c2.set("SpawnData", c);
			    tems.a(c2);
			}
		}
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
		else if (v == 3) return ((org.bukkit.craftbukkit.v1_9_R1.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else if (v == 4) return ((org.bukkit.craftbukkit.v1_9_R2.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else if (v == 5) return ((org.bukkit.craftbukkit.v1_10_R1.block.CraftCreatureSpawner) block.getState()).getSpawnedType();
		else return null;
	}
	
	/**
	 * Returns the version of Spigot. v1_8_R1 = 0, v1_8_R2 = 1, etc and -1 for no correct version found.
	 * */
	public static int getVersion()
	{
		
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		System.out.println(version);
        if (version.equals("v1_8_R1")) return 0;
        else if (version.equals("v1_8_R2")) return 1;
        else if (version.equals("v1_8_R3")) return 2;
        else if (version.equals("v1_9_R1")) return 3;
        else if (version.equals("v1_9_R2")) return 4;
        else if (version.equals("v1_10_R1")) return 5;
        else return -1;
	}
}
