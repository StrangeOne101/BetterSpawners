package com.strangeone101.betterspawners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftCreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterSpawners extends JavaPlugin implements Listener
{
	protected ChatColor r = ChatColor.RED;
	protected ChatColor y = ChatColor.YELLOW;
	protected ChatColor a = ChatColor.AQUA;
	
	public boolean debug = false;
	
	public class Mob
	{
		public EntityType TYPE = EntityType.PIG;
		public String displayName = "Pig";
		public ChatColor color;
		public String[] aliases;
		
		public Mob(EntityType type, String displayName, ChatColor color, String... aliases)
		{
			this.TYPE = type;
			this.displayName = displayName;
			this.color = color;
			this.aliases = aliases;
		}
		
		/**If this contains the matching entitytype*/
		public boolean isMatch(String mob)
		{
			return Arrays.asList(this.aliases).contains(mob.toLowerCase());
		}
	}
	
	public List<Mob> spawnerMobs = new ArrayList<Mob>();
	
	
	/**Gets the blocks that are invisible to the game while setting spawners. E.g. So you can set spawners even with a torch on top*/
	public Set<Material> getTransparentBlocks()
	{
		Set<Material> s = new HashSet<Material>();
		s.add(Material.WATER);		s.add(Material.STATIONARY_WATER); 		s.add(Material.REDSTONE_WIRE);
		s.add(Material.LAVA);		s.add(Material.STATIONARY_LAVA);		s.add(Material.DEAD_BUSH);
		s.add(Material.TORCH);		s.add(Material.REDSTONE_TORCH_OFF); 	s.add(Material.REDSTONE_TORCH_ON);
		s.add(Material.LONG_GRASS);	s.add(Material.FLOWER_POT);				s.add(Material.LEVER);
		s.add(Material.SIGN_POST);	s.add(Material.WALL_SIGN);				s.add(Material.LADDER);
		s.add(Material.AIR);
		return s;
	}
	
	
	@Override
    public void onEnable()
	{
        this.getServer().getPluginManager().registerEvents(this, this);
        
        ChatColor other = ChatColor.YELLOW;
        ChatColor mob = ChatColor.DARK_RED;
        ChatColor animal = ChatColor.AQUA;
        
        this.spawnerMobs.add(new Mob(EntityType.ARMOR_STAND, "ArmorStand", other, "armorstand"));
        //this.spawnerMobs.add(new Mob(EntityType.ARROW, "Arrow", other, "arrow"));
        this.spawnerMobs.add(new Mob(EntityType.BAT, "Bat", animal, "bat"));
        this.spawnerMobs.add(new Mob(EntityType.BLAZE, "Blaze", mob, "blaze"));
        //this.spawnerMobs.add(new Mob(EntityType.BOAT, "Boat", other, "boat")); //Doesnt work
        this.spawnerMobs.add(new Mob(EntityType.CAVE_SPIDER, "CaveSpider", mob, "cavespider"));
        this.spawnerMobs.add(new Mob(EntityType.CHICKEN, "Chicken", animal, "chicken"));
        this.spawnerMobs.add(new Mob(EntityType.COW, "Cow", animal, "cow"));
        this.spawnerMobs.add(new Mob(EntityType.CREEPER, "Creeper", mob, "creeper"));
        //this.spawnerMobs.add(new Mob(EntityType.ENDER_CRYSTAL, "EnderCrystal", other, "endercrystal"));
        this.spawnerMobs.add(new Mob(EntityType.ENDER_DRAGON, "EnderDragon", mob, "enderdragon"));
        this.spawnerMobs.add(new Mob(EntityType.ENDERMAN, "Enderman", mob, "enderman"));
        this.spawnerMobs.add(new Mob(EntityType.ENDERMITE, "Endermite", mob, "endermite"));
        this.spawnerMobs.add(new Mob(EntityType.THROWN_EXP_BOTTLE, "ExpBottle", other, "experienceorb", "xporb", "exporb", "xpbottle", "expbottle"));
        //this.spawnerMobs.add(new Mob(EntityType.FALLING_BLOCK, "FallingSand", other, "fallingsand", "fallingblock"));
        //this.spawnerMobs.add(new Mob(EntityType.FIREBALL, "Fireball", other, "fireball"));
        this.spawnerMobs.add(new Mob(EntityType.GHAST, "Ghast", mob, "ghast"));
        this.spawnerMobs.add(new Mob(EntityType.GIANT, "Giant", mob, "giant"));
        this.spawnerMobs.add(new Mob(EntityType.GUARDIAN, "Guardian", mob, "guardian"));
        this.spawnerMobs.add(new Mob(EntityType.HORSE, "Horse", animal, "horse"));
        this.spawnerMobs.add(new Mob(EntityType.IRON_GOLEM, "IronGolem", animal, "irongolem"));
        this.spawnerMobs.add(new Mob(EntityType.MAGMA_CUBE, "MagmaCube", mob, "magmacube", "lavaslime", "magmaslime"));
        this.spawnerMobs.add(new Mob(EntityType.MINECART, "Minecaft", other, "minecart"));
        this.spawnerMobs.add(new Mob(EntityType.MINECART_CHEST, "ChestMinecart", other, "minecartchest", "chestminecart"));
        this.spawnerMobs.add(new Mob(EntityType.MINECART_COMMAND, "CommandBlockMinecart", other, "minecartcommandblock", "commandminecart", "commandblockminecart", "minecartcommand"));
        this.spawnerMobs.add(new Mob(EntityType.MINECART_FURNACE, "PoweredMinecart", other, "poweredminecart", "furnaceminecart", "minecartfurnace"));
        this.spawnerMobs.add(new Mob(EntityType.MINECART_HOPPER, "HopperMinecart", other, "hopperminecart", "minecarthopper"));
        this.spawnerMobs.add(new Mob(EntityType.MINECART_MOB_SPAWNER, "SpawnerMinecart", other, "spawnerminecart", "minecartspawner", "minecartmobspawner"));
        this.spawnerMobs.add(new Mob(EntityType.MINECART_TNT, "TntMinecart", other, "tntminecart", "minecarttnt"));
        this.spawnerMobs.add(new Mob(EntityType.MUSHROOM_COW, "Mooshroom", animal, "mooshroom", "mushroomcow"));
        this.spawnerMobs.add(new Mob(EntityType.OCELOT, "Ocelot", animal, "ocelot", "cat"));
        this.spawnerMobs.add(new Mob(EntityType.PIG, "Pig", animal, "pig"));
        this.spawnerMobs.add(new Mob(EntityType.PIG_ZOMBIE, "ZombiePigman", mob, "zombiepigman", "pigzombie", "zombiepig"));
        //this.spawnerMobs.add(new Mob(EntityType.PRIMED_TNT, "PrimedTnt", other, "primedtnt", "tnt"));
        this.spawnerMobs.add(new Mob(EntityType.RABBIT, "Rabbit", animal, "rabbit", "bunny"));
        this.spawnerMobs.add(new Mob(EntityType.SHEEP, "Sheep", animal, "sheep"));
        this.spawnerMobs.add(new Mob(EntityType.SILVERFISH, "Silverfish", mob, "silverfish"));
        this.spawnerMobs.add(new Mob(EntityType.SKELETON, "Skeleton", mob, "skeleton"));
        this.spawnerMobs.add(new Mob(EntityType.SLIME, "Slime", mob, "slime"));
        //this.spawnerMobs.add(new Mob(EntityType.SMALL_FIREBALL, "SmallFireball", other, "smallfireball", "fireballsmall"));
        this.spawnerMobs.add(new Mob(EntityType.SNOWMAN, "SnowGolem", animal, "snowgolem", "snowman", "golem"));
        this.spawnerMobs.add(new Mob(EntityType.SPIDER, "Spider", mob, "spider"));
        this.spawnerMobs.add(new Mob(EntityType.SQUID, "Squid", animal, "squid"));
        this.spawnerMobs.add(new Mob(EntityType.VILLAGER, "Villager", animal, "villager"));
        this.spawnerMobs.add(new Mob(EntityType.WITCH, "Witch", mob, "witch"));
        this.spawnerMobs.add(new Mob(EntityType.WITHER, "Wither", mob, "wither", "witherboss"));
        this.spawnerMobs.add(new Mob(EntityType.WOLF, "Wolf", mob, "wolf", "dog"));
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
				for (Object o : this.spawnerMobs.toArray())
				{
					Mob m = ((Mob)o);
					if (sender.hasPermission("betterspawners.set." + m.displayName.toLowerCase()))
					{
						s = s + m.displayName + (m.TYPE != this.spawnerMobs.get(this.spawnerMobs.size() - 1).TYPE ? ", " : "");	
					}
				}
				sender.sendMessage(ChatColor.GOLD + "Avaliable mobs: " + s);
			}
    		else 
    		{
    			if (sender instanceof Player) //If player specifies a mobname
    			{
    				boolean flag = false;
    	        	Mob mob = null;
    	        	for (Object m : spawnerMobs.toArray())
    	        	{
    	        		if (((Mob)m).isMatch(args[0].toLowerCase()))
    	        		{
    	        			mob = ((Mob)m);
    	        			flag = true;
    	        			break;
    	        		}
    	        	}
    				if (flag )//|| args[0].equalsIgnoreCase("spiderpig")) //Easteregg :3
    				{
    					ItemStack hand = ((Player) sender).getPlayer().getItemInHand();
    					//Location target = ((Player) sender).get
    					Location target = ((Player) sender).getTargetBlock(this.getTransparentBlocks(), 7).getLocation();
    					if (target.getBlock().getType() == Material.MOB_SPAWNER)
        				{ 						
       						if (sender.hasPermission("betterspawners.set."+mob.displayName.toLowerCase()))
    						{
    							CraftCreatureSpawner spawner = (CraftCreatureSpawner)target.getBlock().getState();
            		            //spawner.setSpawnedType((EntityType.fromName(this.getMobTypedToName(args[0]))));
            		            this.setSpawner(spawner, mob.displayName);
    							sender.sendMessage(ChatColor.GOLD + "Spawner set to " + mob.color + mob.displayName);
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
            					lore.add(mob.color + mob.displayName);
            					meta.setLore(lore);
            					hand.setItemMeta(meta);
            					sender.sendMessage(ChatColor.GOLD + "Spawner set to " + mob.color + mob.displayName);
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
    					sender.sendMessage(ChatColor.RED + "Invalid mob name!");
    					sender.sendMessage(ChatColor.RED + "Go \"/spawner help\" for more infomation.");
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
        Block b1 = event.getBlock();
        Player player = event.getPlayer();
        CraftCreatureSpawner spawner;
        EntityType type;
        if (b1.getType() == Material.MOB_SPAWNER)
        {
        	spawner = (CraftCreatureSpawner)b1.getState();
        	type = spawner.getSpawnedType();
        	if (debug) {this.getLogger().info(type.toString());}
        	boolean flag = false;
        	Mob mob = null;
        	for (Object m : spawnerMobs.toArray())
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
            	if (b1.getType() == Material.MOB_SPAWNER && player.getGameMode() != GameMode.CREATIVE && (player.getItemInHand().getType().equals(Material.DIAMOND_PICKAXE) || player.getItemInHand().getType().equals(Material.GOLD_PICKAXE) || player.getItemInHand().getType().equals(Material.IRON_PICKAXE) || player.getItemInHand().getType().equals(Material.STONE_PICKAXE) || player.getItemInHand().getType().equals(Material.WOOD_PICKAXE)))
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
                    	 lore.add(mob.color + mob.displayName);
                    }
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    player.getWorld().dropItemNaturally(b1.getLocation(), item);
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
    	if (item.getItemMeta().hasLore())
    	{
    	  	lore = item.getItemMeta().getLore();
    	  	if (item.getType() == Material.MOB_SPAWNER && lore.size() > 0)
        	{
        		CraftBlock block = (CraftBlock) event.getBlock();
            	CraftCreatureSpawner spawner = (CraftCreatureSpawner)block.getState();
        		if (lore.size() > 1)
        		{
        			String lore2 = lore.get(1).replace(ChatColor.DARK_RED.toString(), "").replaceAll(ChatColor.YELLOW.toString(), "").replaceAll(ChatColor.AQUA.toString(), "").replaceAll(ChatColor.DARK_GREEN.toString(), "");
        			String lore1 = lore.get(0).replace(ChatColor.DARK_RED.toString(), "").replaceAll(ChatColor.YELLOW.toString(), "").replaceAll(ChatColor.AQUA.toString(), "").replaceAll(ChatColor.DARK_GREEN.toString(), "");
        			if (!this.setSpawner(spawner, lore2))
            		{
        				if (!this.setSpawner(spawner, lore1))
                		{
                			this.setSpawner(spawner, "pig");
                			event.getPlayer().sendMessage(ChatColor.RED + "The type of spawner isn't known, so a pig spawner has been placed.");
                		}
            		}
        		}
            	else if (!this.setSpawner(spawner, lore.get(0).replace(ChatColor.DARK_RED.toString(), "").replaceAll(ChatColor.YELLOW.toString(), "").replaceAll(ChatColor.AQUA.toString(), "").replaceAll(ChatColor.DARK_GREEN.toString(), "")))
        		{
            		this.setSpawner(spawner, "pig");
        			event.getPlayer().sendMessage(ChatColor.RED + "The type of spawner isn't known, so a pig spawner has been placed.");
        		}
        	}
    	}
    }
    
    public boolean setSpawner(CraftCreatureSpawner spawner, String mob)
    {
    	/*if (mob.equalsIgnoreCase("spiderpig")) //Easteregg :3
    	{
    		NBTTagCompound nbt = new NBTTagCompound();
    		spawner.getTileEntity().b(nbt);
    		nbt.setString("EntityId", "Pig");
    		nbt.setString("CustomName", "Spider Pig!");
    		nbt.setByte("CustomNameVisible", (byte)1);
    		NBTTagCompound riding = new NBTTagCompound();
    		riding.setString("id", "Spider");
    		nbt.set("Riding", riding);
    		spawner.getTileEntity().a(nbt);
    		spawner.setSpawnedType(EntityType.SPIDER);
    		return true;
    	}*/
    	for (Object o : this.spawnerMobs.toArray())
    	{
    		Mob m = (Mob) o;
    		if (m.isMatch(mob.toLowerCase()))
    		{
    			spawner.setSpawnedType(m.TYPE);
    			return true;
    		}
    	}
    	return false;
    }
}
