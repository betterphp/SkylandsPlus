package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.generator.BlockPopulator;

public class OrePopulator extends BlockPopulator
{
  private Random random;
  
  public OrePopulator(World world)
  {
    this.random = new Random(world.getSeed());
  }
  
  @SuppressWarnings("deprecation")
  private void createClump(World world, Material oreType, int size, int x, int y, int z)
  {
    float f = this.random.nextFloat() * 3.141593F;
    
    double d1 = x + 8 + Math.sin(f) * size / 8.0D;
    double d2 = x + 8 - Math.sin(f) * size / 8.0D;
    double d3 = z + 8 + Math.cos(f) * size / 8.0D;
    double d4 = z + 8 - Math.cos(f) * size / 8.0D;
    
    double d5 = y + this.random.nextInt(3) - 2;
    double d6 = y + this.random.nextInt(3) - 2;
    for (int i = 0; i <= size; i++)
    {
      double d7 = d1 + (d2 - d1) * i / size;
      double d8 = d5 + (d6 - d5) * i / size;
      double d9 = d3 + (d4 - d3) * i / size;
      
      double d10 = this.random.nextDouble() * size / 16.0D;
      double d11 = (Math.sin(i * 3.141593F / size) + 1.0D) * d10 + 1.0D;
      double d12 = (Math.sin(i * 3.141593F / size) + 1.0D) * d10 + 1.0D;
      
      int j = (int)Math.floor(d7 - d11 / 2.0D);
      int k = (int)Math.floor(d8 - d12 / 2.0D);
      int m = (int)Math.floor(d9 - d11 / 2.0D);
      
      int n = (int)Math.floor(d7 + d11 / 2.0D);
      int i1 = (int)Math.floor(d8 + d12 / 2.0D);
      int i2 = (int)Math.floor(d9 + d11 / 2.0D);
      for (int i3 = j; i3 <= n; i3++)
      {
        double d13 = (i3 + 0.5D - d7) / (d11 / 2.0D);
        if (d13 * d13 < 1.0D)
        {
          for (int i4 = k; i4 <= i1; i4++)
          {
            double d14 = (i4 + 0.5D - d8) / (d12 / 2.0D);
            if (d13 * d13 + d14 * d14 < 1.0D)
            {
              for (int i5 = m; i5 <= i2; i5++)
              {
                double d15 = (i5 + 0.5D - d9) / (d11 / 2.0D);
                if ((d13 * d13 + d14 * d14 + d15 * d15 < 1.0D) && ((world.getBlockTypeIdAt(i3, i4, i5) == Material.STONE.getId()) || (world.getBlockTypeIdAt(i3, i4, i5) == Material.NETHERRACK.getId())))
                {
                  world.getBlockAt(i3, i4, i5).setType(oreType);
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void populate(World world, Random random, Chunk chunk)
  {
	  int worldChunkX = chunk.getX() * 16;
	  int worldChunkZ = chunk.getZ() * 16;
	  if (world.getEnvironment() == Environment.NORMAL)
	  {
		    for (int i = 0; i < 10; i++)
		    {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(128);
		      
		      createClump(world, Material.GRAVEL, 32, x, y, z);
		    }
		    for (int i = 0; i < 20; i++)
		    {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(128);
		      
		      createClump(world, Material.COAL_ORE, 16, x, y, z);
		    }
		    for (int i = 0; i < 20; i++)
		    {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(51);
		      
		      createClump(world, Material.IRON_ORE, 8, x, y, z);
		    }
		    for (int i = 0; i < 3; i++)
		    {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(48);
		      
		      createClump(world, Material.GOLD_ORE, 8, x, y, z);
		    }
		    for (int i = 0; i < 8; i++)
		    {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(44);
		      
		      createClump(world, Material.REDSTONE_ORE, 7, x, y, z);
		    }
		    for (int i = 0; i < 2; i++)
		    {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(25);
		      
		      createClump(world, Material.DIAMOND_ORE, 7, x, y, z);
		    }
		    for (int i = 0; i < 2; i++)
		    {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(32);
		      
		      createClump(world, Material.LAPIS_ORE, 6, x, y, z);
		    }
	  }
	  else if (world.getEnvironment() == Environment.NETHER)
	  {
		  for (int i = 0; i <= 5; i++)
		  {
		      int x = worldChunkX + this.random.nextInt(16);
		      int z = worldChunkZ + this.random.nextInt(16);
		      int y = this.random.nextInt(128);
		      
		      createClump(world, Material.QUARTZ_ORE, 20, x, y, z);
		  }
	  }
  }
}
