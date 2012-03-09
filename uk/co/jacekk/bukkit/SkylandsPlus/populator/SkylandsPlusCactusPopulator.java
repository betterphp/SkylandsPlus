package uk.co.jacekk.bukkit.SkylandsPlus.populator;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class SkylandsPlusCactusPopulator extends BlockPopulator {
	
	private Random random;
	
	public SkylandsPlusCactusPopulator(World world) {
		this.random = new Random(world.getSeed());
	}

	public void populate(World world, Random random, Chunk chunk) {
		int x, y, z, h, i, c;
		Block block, blockAbove;
		Biome biome;
		
		int worldChunkX = chunk.getX() * 16;
		int worldChunkZ = chunk.getZ() * 16;
		
		for (c = 0; c < 2; ++c){
			x = this.random.nextInt(16);
			z = this.random.nextInt(16);
			
			biome = world.getBiome(worldChunkX + x, worldChunkZ + z);
			
			if (biome == Biome.DESERT || biome == Biome.DESERT_HILLS){
				h = 1 + this.random.nextInt(3);
				
				for (y = world.getMaxHeight(); y > 0; ++y){
					block = chunk.getBlock(x, y, z);
					blockAbove = block.getRelative(BlockFace.UP);
					
					if (blockAbove.getType() == Material.AIR && block.getType() == Material.SAND){
						for (i = 0; i < h; ++i){
							block = chunk.getBlock(x, y + i, z);
							
							if (block.getRelative(BlockFace.NORTH).getType() == Material.AIR
								&& block.getRelative(BlockFace.SOUTH).getType() == Material.AIR
								&& block.getRelative(BlockFace.EAST).getType() == Material.AIR
								&& block.getRelative(BlockFace.WEST).getType() == Material.AIR
								&& (block.getRelative(BlockFace.DOWN).getType() == Material.SAND
								|| block.getRelative(BlockFace.DOWN).getType() == Material.CACTUS)){
								
								block.setType(Material.CACTUS);
							}
						}
					}
				}
			}
		}
	}

}
