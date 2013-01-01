package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class NetherSoulSandPopulator extends BlockPopulator {
	
	private SimplexOctaveGenerator soulSandNoise;
	
	public NetherSoulSandPopulator(World world){
		this.soulSandNoise = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
		
		this.soulSandNoise.setScale(1 / 32.0);
	}
	
	public void populate(World world, Random rand, Chunk chunk){
		int chunkX = chunk.getX();
		int chunkZ = chunk.getZ();
		
		for (int x = 0; x < 16; ++x){
			for (int z = 0; z < 16; ++z){
				int blockX = x + chunkX * 16;
				int blockZ = z + chunkZ * 16;
				
				if (this.soulSandNoise.noise(blockX, blockZ, 0.5, 0.5) > 0.25D){
					for (int y = 128; y > 0; --y){
						Block block = world.getBlockAt(blockX, y, blockZ);
						
						if (block.getType() == Material.NETHERRACK && block.getRelative(BlockFace.UP).getType() == Material.AIR){
							block.setType(Material.SOUL_SAND);
						}
					}
				}
			}
		}
	}
	
}
