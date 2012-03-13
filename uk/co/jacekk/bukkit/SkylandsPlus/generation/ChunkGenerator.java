package uk.co.jacekk.bukkit.SkylandsPlus.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class ChunkGenerator extends org.bukkit.generator.ChunkGenerator {
	
	private Random randomBottom;
	private Random randomTop;
	
	private ChunkPartsGenerator bottomHalf;
	private ChunkPartsGenerator topHalf;
	
	public List<BlockPopulator> getDefaultPopulators(World world){
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		
		populators.add(new LakePopulator(world));	/* should also do reeds */
		populators.add(new GrassPopulator(world));
		populators.add(new FlowerPopulator(world));
		populators.add(new PumpkinPopulator(world));
		populators.add(new OrePopulator(world));
		populators.add(new TreePopulator(world));
		populators.add(new MushroomPopulator(world));
		populators.add(new CactusPopulator(world));
		
		return populators;
	}
	
	public int xyzToByte(int x, int y, int z){
		return (x * 16 + z) * 256 + y;
	}
	
	public int xyzToByte128(int x, int y, int z){
		return (x * 16 + z) * 128 + y;
	}
	
	public byte[] generate(World world, Random random, int chunkX, int chunkZ){
		if (this.randomBottom == null || this.randomTop == null){
			this.randomBottom = new Random(world.getSeed());
			this.randomTop = new Random(world.getSeed() + 237182378192L);
			
			bottomHalf = new ChunkPartsGenerator(this.randomBottom);
			topHalf = new ChunkPartsGenerator(this.randomTop);
		}
		
		byte[] blocksBottom = new byte[16 * 16 * 128];
		
		bottomHalf.shapeLand(world, chunkX, chunkZ, blocksBottom);
		bottomHalf.decorateLand(world, chunkX, chunkZ, blocksBottom);
		
		byte[] blocksTop = new byte[16 * 16 * 128];
		
		topHalf.shapeLand(world, chunkX, chunkZ, blocksTop);
		topHalf.decorateLand(world, chunkX, chunkZ, blocksTop);
		
		byte[] realBlocks = new byte[16 * 16 * 256];
		
		for (int x = 0; x < 16; x++){
			for (int z = 0; z < 16; z++){
				for (int y = 0; y < 128; y++){
					realBlocks[this.xyzToByte(x, y, z)] = blocksBottom[this.xyzToByte128(x, y, z)];
					realBlocks[this.xyzToByte(x, y + 128, z)] = blocksTop[this.xyzToByte128(x, y, z)];
				}
			}
		}
		
		return realBlocks;
	}
	
}
