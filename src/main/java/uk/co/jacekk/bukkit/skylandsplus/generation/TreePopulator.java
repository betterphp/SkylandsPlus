package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class TreePopulator extends BlockPopulator
{
	private Random random;
	private static float FOREST = 0.05f;
	private static float HILLS = 0.01f;
	private static float JUNGLE = 0.075f;
	private static float EDGE = 0.0025f;
	private static float PLAINS = 0.0005f;
	  
	public TreePopulator(World world)
	{
		this.random = new Random(world.getSeed());
	}
	
	public void populate(World world, Random rand, Chunk chunk)
	{
		int cX = chunk.getX() * 16;
		int cZ = chunk.getZ() * 16;
		
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				int y = world.getHighestBlockYAt(cX + x, cZ + z);
				Block block = chunk.getBlock(x, y, z);
				Location loc = block.getLocation();
				
				switch (world.getBiome(cX + x, cZ + z))
				{
					case SWAMPLAND:
					{
						if (random.nextFloat() < FOREST)
							world.generateTree(loc,TreeType.SWAMP);
						break;
					}
					case FOREST:
					{
						if (random.nextFloat() < FOREST)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.BIG_TREE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.BIRCH);
							}
							else
							{
								world.generateTree(loc,TreeType.TREE);
							}
						}
						break;
					}
					case TAIGA:
					{
						if (random.nextFloat() < FOREST)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case PLAINS:
					{
						if (random.nextFloat() < PLAINS)
							world.generateTree(loc, random.nextBoolean()&&random.nextBoolean()?TreeType.BIG_TREE:TreeType.TREE);
						break;
					}
					case EXTREME_HILLS:
					{
						if (random.nextFloat() < PLAINS)
							world.generateTree(loc, random.nextBoolean()&&random.nextBoolean()?TreeType.BIG_TREE:TreeType.TREE);
						break;
					}
					case ICE_PLAINS:
					{
						if (random.nextFloat() < PLAINS)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case ICE_MOUNTAINS:
					{
						if (random.nextFloat() < HILLS)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case MUSHROOM_ISLAND:
					{
						if (random.nextFloat() < FOREST)
							world.generateTree(loc, random.nextBoolean()?TreeType.BROWN_MUSHROOM:TreeType.RED_MUSHROOM);
						break;
					}
					case MUSHROOM_SHORE:
					{
						if (random.nextFloat() < FOREST)
							world.generateTree(loc, random.nextBoolean()?TreeType.BROWN_MUSHROOM:TreeType.RED_MUSHROOM);
						break;
					}
					case FOREST_HILLS:
					{
						if (random.nextFloat() < FOREST - 0.1f)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.BIG_TREE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.BIRCH);
							}
							else
							{
								world.generateTree(loc,TreeType.TREE);
							}
						}
						break;
					}
					case TAIGA_HILLS:
					{
						if (random.nextFloat() < HILLS)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case SMALL_MOUNTAINS:
					{
						if (random.nextFloat() < HILLS)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.BIG_TREE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.BIRCH);
							}
							else
							{
								world.generateTree(loc,TreeType.TREE);
							}
						}
						break;
					}
					case JUNGLE:
					{
						if (random.nextFloat() < JUNGLE)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.SMALL_JUNGLE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.COCOA_TREE);
							}
							else
							{
								world.generateTree(loc,random.nextBoolean()?TreeType.JUNGLE:TreeType.JUNGLE_BUSH);
							}
						}
						break;
					}
					case JUNGLE_HILLS:
					{
						if (random.nextFloat() < JUNGLE - 0.1f)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.SMALL_JUNGLE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.COCOA_TREE);
							}
							else
							{
								world.generateTree(loc,random.nextBoolean()?TreeType.JUNGLE:TreeType.JUNGLE_BUSH);
							}
						}
						break;
					}
					case JUNGLE_EDGE:
					{
						if (random.nextFloat() < EDGE - 0.1)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.SMALL_JUNGLE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.COCOA_TREE);
							}
							else
							{
								world.generateTree(loc,random.nextBoolean()?TreeType.JUNGLE:TreeType.JUNGLE_BUSH);
							}
						}
						break;
					}
					case BIRCH_FOREST:
					{
						if (random.nextFloat() < FOREST)
							world.generateTree(loc,TreeType.BIRCH);
						break;
					}
					case BIRCH_FOREST_HILLS:
					{
						if (random.nextFloat() < HILLS)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_BIRCH:TreeType.BIRCH);
						break;
					}
					case ROOFED_FOREST:
					{
						if (random.nextFloat() < FOREST)
						{
							if (random.nextInt(10) > 4)
							{
								world.generateTree(loc, TreeType.DARK_OAK);
							}
							else
							{
								world.generateTree(loc, random.nextBoolean()?TreeType.BROWN_MUSHROOM:TreeType.RED_MUSHROOM);
							}
						}
						break;
					}
					case COLD_TAIGA:
					{
						if (random.nextFloat() < FOREST)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case COLD_TAIGA_HILLS:
					{
						if (random.nextFloat() < HILLS)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case MEGA_TAIGA:
					{
						if (random.nextFloat() < FOREST)
						{
							if (random.nextInt(10) >= 7)
							{
								world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
							}
							else
							{
								world.generateTree(loc, TreeType.MEGA_REDWOOD);
							}
						}
						break;
					}
					case MEGA_TAIGA_HILLS:
					{
						if (random.nextFloat() < HILLS)
						{
							if (random.nextInt(10) >= 7)
							{
								world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
							}
							else
							{
								world.generateTree(loc, TreeType.MEGA_REDWOOD);
							}
						}
						break;
					}
					case EXTREME_HILLS_PLUS:
					{
						if (random.nextFloat() < PLAINS)
							world.generateTree(loc, TreeType.TREE);
						break;
					}
					case SAVANNA:
					{
						if (random.nextFloat() < HILLS)
							world.generateTree(loc, TreeType.ACACIA);
						break;
					}
					case SAVANNA_PLATEAU:
					{
						if (random.nextFloat() < HILLS)
							world.generateTree(loc, TreeType.ACACIA);
						break;
					}
					case TAIGA_MOUNTAINS:
					{
						if (random.nextFloat() < EDGE)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case SWAMPLAND_MOUNTAINS:
					{
						if (random.nextFloat() < EDGE)
							world.generateTree(loc, TreeType.SWAMP);
						break;
					}
					case JUNGLE_MOUNTAINS:
					{
						if (random.nextFloat() < JUNGLE - EDGE)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.SMALL_JUNGLE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.COCOA_TREE);
							}
							else
							{
								world.generateTree(loc,random.nextBoolean()?TreeType.JUNGLE:TreeType.JUNGLE_BUSH);
							}
						}
						break;
					}
					case JUNGLE_EDGE_MOUNTAINS:
					{
						if (random.nextFloat() < JUNGLE - EDGE)
						{
							int r = random.nextInt(10);
							if (r >= 9)
							{
								world.generateTree(loc,TreeType.SMALL_JUNGLE);
							}
							else if(r < 9 && r >= 7)
							{
								world.generateTree(loc,TreeType.COCOA_TREE);
							}
							else
							{
								world.generateTree(loc,random.nextBoolean()?TreeType.JUNGLE:TreeType.JUNGLE_BUSH);
							}
						}
						break;
					}
					case COLD_TAIGA_MOUNTAINS:
					{
						if (random.nextFloat() < EDGE)
							world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
						break;
					}
					case SAVANNA_MOUNTAINS:
					{
						if (random.nextFloat() < EDGE)
							world.generateTree(loc, TreeType.ACACIA);
						break;
					}
					case SAVANNA_PLATEAU_MOUNTAINS:
					{
						if (random.nextFloat() < EDGE)
							world.generateTree(loc, TreeType.ACACIA);
						break;
					}
					case BIRCH_FOREST_MOUNTAINS:
					{
						if (random.nextFloat() < EDGE)
							world.generateTree(loc, random.nextBoolean()?TreeType.BIRCH:TreeType.TALL_BIRCH);
						break;
					}
					case BIRCH_FOREST_HILLS_MOUNTAINS:
					{
						if (random.nextFloat() < HILLS)
							world.generateTree(loc, random.nextBoolean()?TreeType.BIRCH:TreeType.TALL_BIRCH);
						break;
					}
					case ROOFED_FOREST_MOUNTAINS:
					{
						if (random.nextFloat() < EDGE)
						{
							if (random.nextInt(10) > 4)
							{
								world.generateTree(loc, TreeType.DARK_OAK);
							}
							else
							{
								world.generateTree(loc, random.nextBoolean()?TreeType.BROWN_MUSHROOM:TreeType.RED_MUSHROOM);
							}
						}
						break;
					}
					case MEGA_SPRUCE_TAIGA:
					{
						if (random.nextFloat() < FOREST)
						{
							if (random.nextInt(10) >= 7)
							{
								world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
							}
							else
							{
								world.generateTree(loc, TreeType.MEGA_REDWOOD);
							}
						}
						break;
					}
					case EXTREME_HILLS_MOUNTAINS:
					{
						if (random.nextFloat() < PLAINS)
						{
							world.generateTree(loc,random.nextBoolean()?TreeType.BIG_TREE:TreeType.TREE);
						}
						break;
					}
					case EXTREME_HILLS_PLUS_MOUNTAINS:
					{
						if (random.nextFloat() < PLAINS)
						{
							world.generateTree(loc,random.nextBoolean()?TreeType.BIG_TREE:TreeType.TREE);
						}
						break;
					}
					case MEGA_SPRUCE_TAIGA_HILLS:
					{
						if (random.nextFloat() < HILLS)
						{
							if (random.nextInt(10) >= 7)
							{
								world.generateTree(loc, random.nextBoolean()?TreeType.TALL_REDWOOD:TreeType.REDWOOD);
							}
							else
							{
								world.generateTree(loc, TreeType.MEGA_REDWOOD);
							}
						}
						break;
					}
					default:
					{
						Biome b = world.getBiome(cX + x, cZ + z);
						if (b != Biome.DESERT
								&& b != Biome.DESERT_HILLS
								&& b != Biome.DESERT_MOUNTAINS
								&& b != Biome.MESA
								&& b != Biome.MESA_PLATEAU_FOREST
								&& b != Biome.MESA_PLATEAU_FOREST_MOUNTAINS
								&& b != Biome.MESA_PLATEAU_MOUNTAINS
								&& b != Biome.ICE_PLAINS_SPIKES)
							if (random.nextFloat() < FOREST)
								world.generateTree(loc, TreeType.TREE);
						break;
					}
				}
			}
		}
	}
}
