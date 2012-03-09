package uk.co.jacekk.bukkit.SkylandsPlus.populator;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class SkylandsPlusMushroomPopulator extends BlockPopulator {
	
	private Random random;
	
	public SkylandsPlusMushroomPopulator(World world) {
		this.random = new Random(world.getSeed());
	}
	
	public void populate(World world, Random random, Chunk chunk){
		int x, y, z;
		Block block, ground;
		
		int worldChunkX = chunk.getX() * 16;
		int worldChunkZ = chunk.getZ() * 16;
		
		for (x = 0; x < 16; ++x){
			for (z = 0; z < 16; ++z){
				if (world.getBiome(worldChunkX + x, worldChunkZ + z) == Biome.SWAMPLAND){
					for (y = world.getMaxHeight(); y > 0; --y){
						block = chunk.getBlock(x, y, z);
						ground = block.getRelative(BlockFace.DOWN);
						
						if (block.getType() == Material.AIR && ground.getType() == Material.GRASS && block.getLightLevel() <= 8 && this.random.nextInt(100) < 5){
							if (world.getHighestBlockYAt(x + worldChunkX, z + worldChunkZ) > y){
								block.setType((this.random.nextInt(100) < 20) ? Material.RED_MUSHROOM : Material.BROWN_MUSHROOM);
							}
						}
					}
				}
			}
		}
	}

}
