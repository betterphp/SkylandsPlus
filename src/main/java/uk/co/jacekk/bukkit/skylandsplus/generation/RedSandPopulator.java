package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class RedSandPopulator extends BlockPopulator
{
	  public RedSandPopulator(){}
	  
	  @SuppressWarnings("deprecation")
	  public void populate(World world, Random random, Chunk chunk)
	  {
		  List<Biome> mesaBiomes = Arrays.asList(new Biome[] { Biome.MESA, Biome.MESA_BRYCE, Biome.MESA_PLATEAU, Biome.MESA_PLATEAU_MOUNTAINS });
		  for (int x = 0; x < 16; x++)
		  {
			  for (int z = 0; z < 16; z++)
			  {
				  for (int y = 0; y < 128; y++)
				  {
					  if (y > 4)
					  {
						  Block block = chunk.getBlock(x, y, z);
						  Biome biome = block.getBiome();
						  
						  if (mesaBiomes.contains(biome) && block.getType().equals(Material.SAND))
						  {
							  chunk.getBlock(x, y, z).setData((byte) 1);
						  }
						  
						  if (mesaBiomes.contains(biome) && (block.getType().equals(Material.DEAD_BUSH) || block.getType().equals(Material.LONG_GRASS)) || block.getType().equals(Material.CACTUS))
						  {
							  chunk.getBlock(x, y, z).setType(Material.AIR);
						  }
					  }
				  }
			  }
		  }
	  }
}
