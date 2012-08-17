package uk.co.jacekk.bukkit.SkylandsPlus.generation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class FlowerPopulator extends BlockPopulator {
	
	private Random random;
	
	public FlowerPopulator(World world){
		this.random = new Random(world.getSeed());
	}
	
	public void populate(World world, Random random, Chunk chunk){
		int x, y, z;
		Block block, ground;
		Biome biome;
		
		List<Biome> iceBiomes = Arrays.asList(Biome.TAIGA, Biome.TAIGA_HILLS, Biome.ICE_PLAINS, Biome.ICE_MOUNTAINS, Biome.FROZEN_RIVER, Biome.FROZEN_OCEAN);
		
		for (x = 0; x < 16; ++x){
			for (z = 0; z < 16; ++z){
				for (y = 0; y < 128; y++){
					if (y > 4){
						block = chunk.getBlock(x, y, z);
						ground = block.getRelative(BlockFace.DOWN);
						biome = block.getBiome();
						
						if (ground.getType() == Material.GRASS){
							if (biome == Biome.PLAINS){
								if (this.random.nextInt(100) < 7){
									block.setType((this.random.nextInt(100) < 75) ? Material.YELLOW_FLOWER : Material.RED_ROSE);
								}
							}else if (biome != Biome.DESERT && iceBiomes.contains(biome) == false){
								if (this.random.nextInt(100) < 2){
									block.setType((this.random.nextInt(100) < 75) ? Material.YELLOW_FLOWER : Material.RED_ROSE);
								}
							}
						}
					}
				}
			}
		}
	}

}
