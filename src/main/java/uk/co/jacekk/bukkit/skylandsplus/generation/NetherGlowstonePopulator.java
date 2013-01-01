package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class NetherGlowstonePopulator extends BlockPopulator {
	
private Random random;
	
	public NetherGlowstonePopulator(World world){
		this.random = new Random(world.getSeed());
	}
	
	private double getLengthSquared(double x, double y, double z){
		return (x * x) + (y * y) + (z * z); 
	}
	
	public void populate(World world, Random rand, Chunk chunk){
		if (this.random.nextInt(100) < 12){
			Integer x = 5 + this.random.nextInt(6);
			Integer y = world.getMaxHeight() - this.random.nextInt(120) - 28;
			Integer z = 5 + this.random.nextInt(6);
			
			boolean lava = (this.random.nextInt(100) < 40);
			
			int radius = (int) Math.ceil(5D + 0.5);
			int radiusSq = radius * radius;
			
			for (int sx = 0; sx < radius; ++sx){
				for (int sy = 0; sy < radius; ++sy){
					for (int sz = 0; sz < radius; ++sz){
						double length = this.getLengthSquared(sx, sy, sz);
						
						if ((lava && length < radiusSq && length > radiusSq - 16) || (!lava && length < radiusSq)){
							chunk.getBlock(x + sx, y + sy, z + sz).setType(Material.GLOWSTONE);
							chunk.getBlock(x - sx, y + sy, z + sz).setType(Material.GLOWSTONE);
							chunk.getBlock(x + sx, y - sy, z + sz).setType(Material.GLOWSTONE);
							chunk.getBlock(x + sx, y + sy, z - sz).setType(Material.GLOWSTONE);
							chunk.getBlock(x - sx, y - sy, z + sz).setType(Material.GLOWSTONE);
							chunk.getBlock(x + sx, y - sy, z - sz).setType(Material.GLOWSTONE);
							chunk.getBlock(x - sx, y + sy, z - sz).setType(Material.GLOWSTONE);
							chunk.getBlock(x - sx, y - sy, z - sz).setType(Material.GLOWSTONE);
						}else if (length < radiusSq - 16){
							chunk.getBlock(x + sx, y + sy, z + sz).setType(Material.LAVA);
							chunk.getBlock(x - sx, y + sy, z + sz).setType(Material.LAVA);
							chunk.getBlock(x + sx, y - sy, z + sz).setType(Material.LAVA);
							chunk.getBlock(x + sx, y + sy, z - sz).setType(Material.LAVA);
							chunk.getBlock(x - sx, y - sy, z + sz).setType(Material.LAVA);
							chunk.getBlock(x + sx, y - sy, z - sz).setType(Material.LAVA);
							chunk.getBlock(x - sx, y + sy, z - sz).setType(Material.LAVA);
							chunk.getBlock(x - sx, y - sy, z - sz).setType(Material.LAVA);
						}
					}
				}
				
				if (lava){
					for (int ly = 0; ly < radius; ++ly){
						chunk.getBlock(x + ly, y, z).setType(Material.LAVA);
						chunk.getBlock(x - ly, y, z).setType(Material.LAVA);
						chunk.getBlock(x, y, z + ly).setType(Material.LAVA);
						chunk.getBlock(x, y, z - ly).setType(Material.LAVA);
					}
				}
			}
		}
	}
	
}
