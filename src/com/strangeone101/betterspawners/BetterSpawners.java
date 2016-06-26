package com.strangeone101.betterspawners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BetterSpawners extends JavaPlugin implements Listener
{	
	public boolean debug = false;
	
	public enum Hostility
	{
		PASSIVE (ChatColor.AQUA), 
		AGRESSIVE (ChatColor.RED), 
		NEUTRAL (ChatColor.RED), 
		ENTITY (ChatColor.YELLOW);
		
		Hostility(ChatColor c) 
		{
			color = c;
		}
		ChatColor color;
	}
	
	public class Mob
	{
		public EntityType TYPE = EntityType.PIG;
		public String displayName = "Pig";
		public Hostility hostility;
		public String[] aliases;
		public Map<String, Object> NBT = new HashMap<String, Object>();
		
		public Mob(EntityType type, String displayName, Hostility hostile, String[] aliases, Object... nbt)
		{
			this.TYPE = type;
			this.displayName = displayName;
			this.hostility = hostile;
			this.aliases = aliases;
			
			for (int i = 0; i < nbt.length; i+=2) 
			{
				this.NBT.put(nbt[i].toString(), nbt[i + 1]);
			}
			
			spawnerMobs.put(displayName.toLowerCase(), this);
			for (String s : aliases)
			{
				spawnerMobs.put(s.toLowerCase(), this);
			}
		}
		
		public Mob(EntityType type, String displayName, Hostility hostile) {
			this(type, displayName, hostile, new String[0]);
		}
		
		/**If this contains the matching entitytype*/
		public boolean isMatch(String mob)
		{
			return Arrays.asList(this.aliases).contains(mob.toLowerCase());
		}
	}
	
	public Map<String, Mob> spawnerMobs = new HashMap<>();
	
	
	/**Gets the blocks that are invisible to the game while setting spawners. E.g. So you can set spawners even with a torch on top*/
	public Set<Material> getTransparentBlocks()
	{
		Set<Material> s = new HashSet<Material>();
		s.add(Material.WATER);			s.add(Material.STATIONARY_WATER); 		s.add(Material.REDSTONE_WIRE);
		s.add(Material.LAVA);			s.add(Material.STATIONARY_LAVA);		s.add(Material.DEAD_BUSH);
		s.add(Material.TORCH);			s.add(Material.REDSTONE_TORCH_OFF); 	s.add(Material.REDSTONE_TORCH_ON);
		s.add(Material.LONG_GRASS);		s.add(Material.FLOWER_POT);				s.add(Material.LEVER);
		s.add(Material.SIGN_POST);		s.add(Material.WALL_SIGN);				s.add(Material.LADDER);
		s.add(Material.AIR);        	s.add(Material.BANNER);                 s.add(Material.WALL_BANNER);
		s.add(Material.YELLOW_FLOWER);	s.add(Material.BREWING_STAND);			s.add(Material.RED_ROSE);
		s.add(Material.DOUBLE_PLANT);	s.add(Material.ACTIVATOR_RAIL);			s.add(Material.RAILS);
		s.add(Material.DETECTOR_RAIL);	s.add(Material.POWERED_RAIL);			s.add(Material.IRON_TRAPDOOR);
		s.add(Material.TRAP_DOOR);		s.add(Material.RED_MUSHROOM);			s.add(Material.BROWN_MUSHROOM);
		s.add(Material.SKULL);			s.add(Material.SAPLING);				s.add(Material.STANDING_BANNER);
		s.add(Material.STONE_PLATE);	s.add(Material.WOOD_PLATE);				s.add(Material.GOLD_PLATE);
		s.add(Material.IRON_PLATE);		s.add(Material.SUGAR_CANE_BLOCK);		s.add(Material.TRIPWIRE);
		return s;
	}
	
	
	@Override
    public void onEnable()
	{
		this.getLogger().info(Bukkit.getServer().getClass().getPackage().getName());
        
		this.getLogger().info(Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3]);
        this.getServer().getPluginManager().registerEvents(this, this);
              
        //new Mob(EntityType.ARMOR_STAND, "ArmorStand", Hostility.ENTITY, "armorstand");
        new Mob(EntityType.BAT, "Bat", Hostility.PASSIVE);
        new Mob(EntityType.BLAZE, "Blaze", Hostility.AGRESSIVE);
        new Mob(EntityType.CAVE_SPIDER, "CaveSpider", Hostility.AGRESSIVE);
        new Mob(EntityType.CHICKEN, "Chicken", Hostility.PASSIVE);
        new Mob(EntityType.COW, "Cow", Hostility.PASSIVE);
        new Mob(EntityType.CREEPER, "Creeper", Hostility.AGRESSIVE);
        new Mob(EntityType.ENDER_DRAGON, "EnderDragon", Hostility.AGRESSIVE, new String[] {"dragon"});
        new Mob(EntityType.ENDERMAN, "Enderman", Hostility.AGRESSIVE);
        new Mob(EntityType.ENDERMITE, "Endermite", Hostility.AGRESSIVE);
        //new Mob(EntityType.THROWN_EXP_BOTTLE, "ExpBottle", Hostility.ENTITY, "experienceorb", "xporb", "exporb", "xpbottle", "xp");
        new Mob(EntityType.GHAST, "Ghast", Hostility.AGRESSIVE);
        new Mob(EntityType.GIANT, "Giant", Hostility.AGRESSIVE);
        new Mob(EntityType.GUARDIAN, "Guardian", Hostility.AGRESSIVE);
        new Mob(EntityType.HORSE, "Horse", Hostility.PASSIVE);
        new Mob(EntityType.IRON_GOLEM, "IronGolem", Hostility.PASSIVE);
        new Mob(EntityType.MAGMA_CUBE, "MagmaCube", Hostility.AGRESSIVE, new String[] {"lavaslime", "magmaslime"});
        /*new Mob(EntityType.MINECART, "Minecart", Hostility.ENTITY, "minecart");
        new Mob(EntityType.MINECART_CHEST, "ChestMinecart", Hostility.ENTITY, "minecartchest");
        new Mob(EntityType.MINECART_COMMAND, "CommandBlockMinecart", Hostility.ENTITY, "minecartcommandblock", "commandminecart", "minecartcommand");
        new Mob(EntityType.MINECART_FURNACE, "PoweredMinecart", Hostility.ENTITY, "furnaceminecart", "minecartfurnace");
        new Mob(EntityType.MINECART_HOPPER, "HopperMinecart", Hostility.ENTITY, "minecarthopper");
        new Mob(EntityType.MINECART_MOB_SPAWNER, "SpawnerMinecart", Hostility.ENTITY, "minecartspawner", "minecartmobspawner");
        new Mob(EntityType.MINECART_TNT, "TntMinecart", Hostility.ENTITY, "minecarttnt");*/
        new Mob(EntityType.MUSHROOM_COW, "Mooshroom", Hostility.PASSIVE, new String[] {"mushroomcow"});
        new Mob(EntityType.OCELOT, "Ocelot", Hostility.PASSIVE, new String[] {"cat"});
        new Mob(EntityType.PIG, "Pig", Hostility.PASSIVE);
        new Mob(EntityType.PIG_ZOMBIE, "ZombiePigman", Hostility.AGRESSIVE, new String[] {"pigzombie", "zombiepig"});
        new Mob(EntityType.RABBIT, "Rabbit", Hostility.PASSIVE, new String[] {"bunny"});
        new Mob(EntityType.SHEEP, "Sheep", Hostility.PASSIVE);
        new Mob(EntityType.SILVERFISH, "Silverfish", Hostility.AGRESSIVE);
        new Mob(EntityType.SKELETON, "Skeleton", Hostility.AGRESSIVE);
        new Mob(EntityType.SLIME, "Slime", Hostility.AGRESSIVE);
        new Mob(EntityType.SNOWMAN, "SnowGolem", Hostility.PASSIVE, new String[] {"snowman", "golem"});
        new Mob(EntityType.SPIDER, "Spider", Hostility.AGRESSIVE);
        new Mob(EntityType.SQUID, "Squid", Hostility.PASSIVE);
        new Mob(EntityType.VILLAGER, "Villager", Hostility.PASSIVE, new String[] {"testificate"});
        new Mob(EntityType.WITCH, "Witch", Hostility.AGRESSIVE);
        new Mob(EntityType.WITHER, "Wither", Hostility.AGRESSIVE, new String[] {"witherboss"});
        new Mob(EntityType.WOLF, "Wolf", Hostility.PASSIVE, new String[] {"dog"});
        new Mob(EntityType.ZOMBIE, "Zombie", Hostility.AGRESSIVE);
        
        new Mob(EntityType.SKELETON, "WitherSkeleton", Hostility.AGRESSIVE, new String[] {}, "SkeletonType", 1);
        new Mob(EntityType.RABBIT, "Toast", Hostility.PASSIVE, new String[] {}, "CustomName", "Toast");
        new Mob(EntityType.GUARDIAN, "ElderGuardian", Hostility.AGRESSIVE, new String[] {}, "Elder", 1);
        new Mob(EntityType.ZOMBIE, "BabyZombie", Hostility.AGRESSIVE, new String[] {}, "IsBaby", 1);
        new Mob(EntityType.ZOMBIE, "ZombieVillager", Hostility.AGRESSIVE, new String[] {"villagerzombie"}, "IsVillager", 1);
        new Mob(EntityType.HORSE, "Donkey", Hostility.PASSIVE, new String[] {}, "Type", 1);
        new Mob(EntityType.HORSE, "Mule", Hostility.PASSIVE, new String[] {}, "Type", 2);
        new Mob(EntityType.HORSE, "ZombieHorse", Hostility.AGRESSIVE, new String[] {}, "Type", 3);
        new Mob(EntityType.HORSE, "SkeletonHorse", Hostility.AGRESSIVE, new String[] {}, "Type", 4);
        if (NMSHandler.getVersion() >= 3) { //1.9 or above
        	new Mob(EntityType.SHULKER, "Shulker", Hostility.AGRESSIVE);
        }
        if (NMSHandler.getVersion() >= 5) { //1.10 or above
        	new Mob(EntityType.POLAR_BEAR, "PolarBear", Hostility.PASSIVE, new String[] {"bear"});
        	new Mob(EntityType.ZOMBIE, "Husk", Hostility.AGRESSIVE, new String[] {"desertzombie"}, "ZombieType", 6);
        	new Mob(EntityType.SKELETON, "Stray", Hostility.AGRESSIVE, new String[] {"iceskeleton"}, "SkeletonType", 2);
            
        }
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
    	if (cmd.getName().equalsIgnoreCase("spawner"))
    	{
    		if (args.length < 1) //If they only enter "/spawner"
        	{
    			if (!(sender instanceof Player)) //If non-player. e.g: Console
    			{
        			sender.sendMessage("This command can only be run by a player.");
        		}
    			else //If run by player, send them the ussage
    			{
    				sender.sendMessage(ChatColor.GOLD + "Usage: /spawner <mob>");
    				sender.sendMessage(ChatColor.GOLD + "Sets a spawner in your hand or where your looking to the specified mob.");
    			}
        	}
    		else if(args[0].toLowerCase().equals("help"))
			{
				sender.sendMessage(ChatColor.GOLD + "Usage: /spawner <mob>");
				sender.sendMessage(ChatColor.GOLD + "Sets a spawner in your hand or where you're looking to the specified mob.");
				sender.sendMessage(ChatColor.GOLD + "Go \"/spawner list\" to view a list of avaliable mobs.");
			}
			else if(args[0].toLowerCase().equals("list"))
			{
				String s = "";
				for (Object o : this.spawnerMobs.values())
				{
					Mob m = ((Mob)o);
					if (sender.hasPermission("betterspawners.set." + m.displayName.toLowerCase()))
					{
						s = s + m.displayName + (m.displayName != this.spawnerMobs.get(this.spawnerMobs.size() - 1).displayName ? ", " : "");	
					}
				}
				sender.sendMessage(ChatColor.GOLD + "Avaliable mobs: " + s);
			}
    		else 
    		{
    			if (sender instanceof Player) //If player specifies a mobname
    			{
    				if (!this.spawnerMobs.containsKey(args[0].toLowerCase())) {
    					sender.sendMessage(ChatColor.RED + "Invalid mob name!");
    					sender.sendMessage(ChatColor.RED + "Go \"/spawner help\" for more infomation.");
    					return true;
    				}
    				Mob mob = spawnerMobs.get(args[0].toLowerCase());
    				ItemStack hand = ((Player) sender).getPlayer().getItemInHand();
    					//Location target = ((Player) sender).get
    					Location target = ((Player) sender).getTargetBlock(this.getTransparentBlocks(), 7).getLocation();
    					if (target.getBlock().getType() == Material.MOB_SPAWNER)
        				{ 						
       						if (sender.hasPermission("betterspawners.set."+mob.displayName.toLowerCase()))
    						{
            		            this.setSpawner(target.getBlock(), mob.displayName);
    							sender.sendMessage(ChatColor.GOLD + "Spawner set to " + mob.hostility.color + mob.displayName);
            		            return true;
    						}
    						else
    						{
    							sender.sendMessage(ChatColor.DARK_RED + "You don't have permission to change spawners to this mob!");
    							return true;
    						}
        				}
        				else if (hand.getType().equals(Material.MOB_SPAWNER))
        				{        					
        					if (sender.hasPermission("betterspawners.set."+mob.displayName.toLowerCase()))
    						{
        						ItemMeta meta = hand.getItemMeta();
            					ArrayList<String> lore = new ArrayList<String>();
            					lore.add(mob.hostility.color + mob.displayName);
            					meta.setLore(lore);
            					hand.setItemMeta(meta);
            					sender.sendMessage(ChatColor.GOLD + "Spawner set to " + mob.hostility.color + mob.displayName);
            					return true;
    						}
        					else
        					{
        						sender.sendMessage(ChatColor.DARK_RED + "You don't have permission to change spawners to this mob!");
        						return true;
        					}
        		        }
        				else
        				{
        					sender.sendMessage(ChatColor.DARK_RED + "Error: "+ChatColor.RED+"You aren't holding or looking at a spawner!");
        				}
    			}
    			else
    			{
    				sender.sendMessage("This command can only be run by a player.");
    			}
    		}
    	}
    	return true;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Player player = event.getPlayer();
        EntityType type;
        if (block.getType() == Material.MOB_SPAWNER)
        {
        	type = NMSHandler.getType(block);
        	if (debug) {this.getLogger().info(type.toString());}
        	boolean flag = false;
        	Mob mob = null;
        	for (Object m : spawnerMobs.values())
        	{
        		if (type == ((Mob)m).TYPE)
        		{
        			mob = ((Mob)m);
        			flag = true;
        			break;
        		}
        	}
        	if (!flag || !player.hasPermission("betterspawners.break."+mob.displayName.toLowerCase()))
            {
            	event.setCancelled(true);
            	player.sendMessage("You don't have permission to break this spawner!");
            }
            if (player.hasPermission("betterspawners.mine."+mob.displayName.toLowerCase()))
            {
            	if (block.getType() == Material.MOB_SPAWNER && player.getGameMode() != GameMode.CREATIVE && (player.getItemInHand().getType().equals(Material.DIAMOND_PICKAXE) || player.getItemInHand().getType().equals(Material.GOLD_PICKAXE) || player.getItemInHand().getType().equals(Material.IRON_PICKAXE) || player.getItemInHand().getType().equals(Material.STONE_PICKAXE) || player.getItemInHand().getType().equals(Material.WOOD_PICKAXE)))
                {
                    ItemStack item = new ItemStack(Material.MOB_SPAWNER);
                    ItemMeta meta = item.getItemMeta();
                    ArrayList<String> lore = new ArrayList<String>();
                    if (!player.hasPermission("betterspawners.get."+mob.displayName.toLowerCase()))
                    {
                    	lore.add(ChatColor.DARK_AQUA + "Pig");
                    }
                    else
                    {
                    	 lore.add(mob.hostility.color + mob.displayName);
                    }
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    player.getWorld().dropItemNaturally(block.getLocation(), item);
                    event.setExpToDrop(0);
                } 
            }
        }
        
        
    }
    /*
    public String getMobNameToFancyName(String mobName)
    {
    	EntityType type = EntityType.fromName(mobName);
    	if (type != null && !this.blockedMobs.contains(type))
    	{
    		
    	}
 	   	return ChatColor.AQUA + "Pig";
    }*/
    
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event)
    {
    	ItemStack item = event.getItemInHand();
    	List<String> lore;
    	if (item == null) return;
    	if (item.getItemMeta().hasLore())
    	{
    	  	lore = item.getItemMeta().getLore();
    	  	if (item.getType() == Material.MOB_SPAWNER && lore.size() > 0)
        	{
        		Block block = event.getBlock();
            	if (lore.size() > 1)
        		{
        			String lore2 = lore.get(1);
        			String lore1 = lore.get(0);
        			if (!this.setSpawner(block, lore2))
            		{
        				if (!this.setSpawner(block, lore1))
                		{
                			this.setSpawner(block, "pig");
                			event.getPlayer().sendMessage(ChatColor.RED + "The type of spawner unknown, so a pig spawner has been placed.");
                		}
            		}
        		}
            	else if (!this.setSpawner(block, lore.get(0)))
        		{
            		this.setSpawner(block, "pig");
        			event.getPlayer().sendMessage(ChatColor.RED + "The type of spawner unknown, so a pig spawner has been placed.");
        		}
        	}
    	}
    }
    
    public boolean setSpawner(Block block, String mob)
    {
    	mob = ChatColor.stripColor(mob);
    	if (spawnerMobs.containsKey(mob.toLowerCase()))
    	{
    		NMSHandler.setSpawner(block, spawnerMobs.get(mob.toLowerCase()).TYPE);
    		for (String key : spawnerMobs.get(mob.toLowerCase()).NBT.keySet()) {
    			NMSHandler.setSpawnerEntityData(block, key, spawnerMobs.get(mob.toLowerCase()).NBT.get(key));
    		}
    	}
    	return false;
    }
}
