package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;

import net.minecraft.server.v1_6_R2.BiomeBase;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_6_R2.CraftWorld;
import org.bukkit.generator.BlockPopulator;

import uk.co.jacekk.bukkit.baseplugin.util.ReflectionUtils;

public class BiomePopulator extends BlockPopulator {
	
	@Override
	public void populate(World world, Random random, Chunk chunk){
		Biome biome = world.getBiome(chunk.getX() * 16, chunk.getZ() * 16);
		
		try{
			BiomeBase biomeBase = ReflectionUtils.getFieldValue(BiomeBase.class, biome.name(), BiomeBase.class, null);
			
			biomeBase.a(((CraftWorld) world).getHandle(), random, chunk.getX() * 16, chunk.getZ() * 16);
		}catch (NoSuchFieldException e){
			e.printStackTrace();
		}
	}
	
}
