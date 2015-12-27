package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class NetherWartPopulator extends BlockPopulator
{
	private Random random;
	  
	  public NetherWartPopulator(World world)
	  {
	    this.random = new Random(world.getSeed());
	  }
	  
	  public void populate(World world, Random random, Chunk chunk)
	  {
	    for (int x = 0; x < 16; x++) {
	      for (int z = 0; z < 16; z++) {
	        for (int y = 0; y < 128; y++) {
	          if (y > 4)
	          {
	            Block block = chunk.getBlock(x, y, z);
	            Block ground = block.getRelative(BlockFace.DOWN);
	            Biome biome = block.getBiome();
	            if (ground.getType() == Material.SOUL_SAND) {
	              if (biome == Biome.HELL)
	              {
	                if (this.random.nextInt(100) < 7) {
	                  block.setType(this.random.nextInt(100) > 85 ? Material.NETHER_WARTS : Material.AIR);
	                }
	              }
	            }
	          }
	        }
	      }
	    }
	  }
}