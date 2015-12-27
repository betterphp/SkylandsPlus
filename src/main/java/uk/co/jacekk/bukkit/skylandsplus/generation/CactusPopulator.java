package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class CactusPopulator
  extends BlockPopulator
{
  private Random random;
  
  public CactusPopulator(World world)
  {
    this.random = new Random(world.getSeed());
  }
  
  public void populate(World world, Random random, Chunk chunk)
  {
    int worldChunkX = chunk.getX() * 16;
    int worldChunkZ = chunk.getZ() * 16;
    for (int c = 0; c < 2; c++)
    {
      int x = this.random.nextInt(16);
      int z = this.random.nextInt(16);
      
      Biome biome = world.getBiome(worldChunkX + x, worldChunkZ + z);
      if ((biome == Biome.DESERT) || (biome == Biome.DESERT_HILLS))
      {
        int h = 1 + this.random.nextInt(3);
        for (int y = 128; y > 0; y--)
        {
          Block block = chunk.getBlock(x, y, z);
          Block blockAbove = block.getRelative(BlockFace.UP);
          if ((blockAbove.getType() == Material.AIR) && (block.getType() == Material.SAND)) {
            for (int i = 0; i < h; i++)
            {
              block = chunk.getBlock(x, y + i, z);
              if ((block.getRelative(BlockFace.NORTH).getType() == Material.AIR) && (block.getRelative(BlockFace.SOUTH).getType() == Material.AIR) && (block.getRelative(BlockFace.EAST).getType() == Material.AIR) && (block.getRelative(BlockFace.WEST).getType() == Material.AIR) && ((block.getRelative(BlockFace.DOWN).getType() == Material.SAND) || (block.getRelative(BlockFace.DOWN).getType() == Material.CACTUS))) {
                block.setType(Material.CACTUS);
              }
            }
          }
        }
      }
    }
  }
}
