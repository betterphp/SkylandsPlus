package uk.co.jacekk.bukkit.SkylandsPlus.populator;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class SkylandsPlusTreePopulator extends BlockPopulator {
	
	private Random random;
	
	public SkylandsPlusTreePopulator(World world) {
		this.random = new Random(world.getSeed());
	}
	
	public void populate(World world, Random random, Chunk chunk){
		int x, y, z;
		Block block, ground;
		Biome biome;
		
		int worldChunkX = chunk.getX() * 16;
		int worldChunkZ = chunk.getZ() * 16;
		
		for (x = 4; x < 13; ++x){
			for (z = 4; z < 13; ++z){
				y = world.getHighestBlockYAt(worldChunkX + x, worldChunkZ + z);
				
				if (y > 4){
					block = chunk.getBlock(x, y, x);
					ground = block.getRelative(BlockFace.DOWN);
					biome = world.getBiome(worldChunkX + x, worldChunkZ + z);
					
					if (ground.getType() == Material.GRASS){
						if (biome == Biome.TUNDRA){
							if (this.random.nextInt(1000) < 10){
								world.generateTree(block.getLocation(), TreeType.TREE);
							}
						}else if (biome == Biome.PLAINS || biome == Biome.ICE_PLAINS){
							if (this.random.nextInt(1000) < 5){
								world.generateTree(block.getLocation(), TreeType.TREE);
							}
						}else if (biome == Biome.TAIGA || biome == Biome.TAIGA_HILLS){
							if (this.random.nextInt(1000) < 70){
								world.generateTree(block.getLocation(), (this.random.nextInt(100) < 60) ? TreeType.REDWOOD : TreeType.TALL_REDWOOD);
							}
						}else if (biome == Biome.SWAMPLAND){
							if (this.random.nextInt(1000) < 60){
								world.generateTree(block.getLocation(), TreeType.TREE);
							}
						}else if (biome == Biome.MUSHROOM_ISLAND || biome == Biome.MUSHROOM_SHORE){
							if (this.random.nextInt(1000) < 40){
								world.generateTree(block.getLocation(), (this.random.nextInt(100) < 50) ? TreeType.BROWN_MUSHROOM : TreeType.RED_MUSHROOM);
							}
						}else if (biome != Biome.DESERT && biome != Biome.DESERT_HILLS){
							if (this.random.nextInt(1000) < 75){
								if (this.random.nextInt(100) < 30){
									world.generateTree(block.getLocation(), TreeType.BIRCH);
								}else if (this.random.nextInt(100) < 55){
									world.generateTree(block.getLocation(), TreeType.TREE);
								}else{
									world.generateTree(block.getLocation(), TreeType.BIG_TREE);
								}
							}
						}
					}
				}
			}
		}
	}

}
