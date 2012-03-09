package uk.co.jacekk.bukkit.SkylandsPlus.populator;

import java.util.Random;

import net.minecraft.server.WorldGenMinable;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.generator.BlockPopulator;

public class SkylandsPlusOrePopulator extends BlockPopulator {
	
	private Random random;
	
	public SkylandsPlusOrePopulator(World world) {
		this.random = new Random(world.getSeed());
	}
	
	public void populate(World world, Random random, Chunk chunk){
		net.minecraft.server.World mcWorld = ((CraftWorld) world).getHandle();
		
		int x, y, z, i;
		
		int worldChunkX = chunk.getX() * 16;
		int worldChunkZ = chunk.getZ() * 16;
		
		for (i = 0; i < 10; ++i){
			x = worldChunkX + this.random.nextInt(16);
			z = worldChunkZ + this.random.nextInt(16);
			
			y = this.random.nextInt(world.getMaxHeight());
			
			(new WorldGenMinable(Material.GRAVEL.getId(), 32)).a(mcWorld, this.random, x, y, z);
		}
		
		for (i = 0; i < 20; ++i){
			x = worldChunkX + this.random.nextInt(16);
			z = worldChunkZ + this.random.nextInt(16);
			
			y = this.random.nextInt(world.getMaxHeight());
			
			(new WorldGenMinable(Material.COAL_ORE.getId(), 16)).a(mcWorld, this.random, x, y, z);
		}
		
		for (i = 0; i < 20; ++i){
			x = worldChunkX + this.random.nextInt(16);
			z = worldChunkZ + this.random.nextInt(16);
			
			y = this.random.nextInt((int) (world.getSeaLevel() * 0.8));
			
			(new WorldGenMinable(Material.IRON_ORE.getId(), 8)).a(mcWorld, this.random, x, y, z);
		}
		
		for (i = 0; i < 3; ++i){
			x = worldChunkX + this.random.nextInt(16);
			z = worldChunkZ + this.random.nextInt(16);
			
			y = this.random.nextInt((int) (world.getSeaLevel() * 0.75));
			
			(new WorldGenMinable(Material.GOLD_ORE.getId(), 8)).a(mcWorld, this.random, x, y, z);
		}
		
		for (i = 0; i < 8; ++i){
			x = worldChunkX + this.random.nextInt(16);
			z = worldChunkZ + this.random.nextInt(16);
			
			y = this.random.nextInt(44);
			
			(new WorldGenMinable(Material.REDSTONE_ORE.getId(), 7)).a(mcWorld, this.random, x, y, z);
		}
		
		for (i = 0; i < 2; ++i){
			x = worldChunkX + this.random.nextInt(16);
			z = worldChunkZ + this.random.nextInt(16);
			
			y = this.random.nextInt((int) (world.getSeaLevel() * 0.4));
			
			(new WorldGenMinable(Material.DIAMOND_ORE.getId(), 7)).a(mcWorld, this.random, x, y, z);
		}
		
		for (i = 0; i < 2; ++i){
			x = worldChunkX + this.random.nextInt(16);
			z = worldChunkZ + this.random.nextInt(16);
			
			y = this.random.nextInt((int) (world.getSeaLevel() * 0.5));
			
			(new WorldGenMinable(Material.LAPIS_ORE.getId(), 6)).a(mcWorld, this.random, x, y, z);
		}
	}

}
