package uk.co.jacekk.bukkit.SkylandsPlus.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.server.NoiseGeneratorOctaves;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusCactusPopulator;
import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusFlowerPopulator;
import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusGrassPopulator;
import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusLakePopulator;
import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusMushroomPopulator;
import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusOrePopulator;
import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusPumpkinPopulator;
import uk.co.jacekk.bukkit.SkylandsPlus.populator.SkylandsPlusTreePopulator;

public class ChunkGenerator extends org.bukkit.generator.ChunkGenerator {
	
	private Random random;
	
	private NoiseGeneratorOctaves k;
	private NoiseGeneratorOctaves l;
	private NoiseGeneratorOctaves m;
	private NoiseGeneratorOctaves o;
	private NoiseGeneratorOctaves a;
	private NoiseGeneratorOctaves b;
	
	private double[] q;
	private double[] t = new double[256];
	
	double[] d;
	double[] e;
	double[] f;
	double[] g;
	double[] h;
	
	int[][] i = new int[32][32];
	
	public List<BlockPopulator> getDefaultPopulators(World world){
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		
		populators.add(new SkylandsPlusLakePopulator(world));	/* should also do reeds */
		populators.add(new SkylandsPlusGrassPopulator(world));
		populators.add(new SkylandsPlusFlowerPopulator(world));
		populators.add(new SkylandsPlusPumpkinPopulator(world));
		populators.add(new SkylandsPlusOrePopulator(world));
		populators.add(new SkylandsPlusTreePopulator(world));
		populators.add(new SkylandsPlusMushroomPopulator(world));
		populators.add(new SkylandsPlusCactusPopulator(world));
		
		return populators;
	}
	
	// anybody know what this does, let me know !
	private double[] a(double[] adouble, int i, int j, int k, int l, int i1, int j1){
		if (adouble == null){
			adouble = new double[l * i1 * j1];
		}
		
		double d0 = 684.412D;
		double d1 = 684.412D;
		
		this.g = this.a.a(this.g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
		this.h = this.b.a(this.h, i, k, l, j1, 200.0D, 200.0D, 0.5D);
		
		d0 *= 2.0D;
		
		this.d = this.m.a(this.d, i, j, k, l, i1, j1, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
		this.e = this.k.a(this.e, i, j, k, l, i1, j1, d0, d1, d0);
		this.f = this.l.a(this.f, i, j, k, l, i1, j1, d0, d1, d0);
		
		int k1 = 0;
		int l1 = 0;
		
		for (int i2 = 0; i2 < l; ++i2){
			for (int j2 = 0; j2 < j1; ++j2){
				double d2 = (this.g[l1] + 256.0D) / 512.0D;
				
				if (d2 > 1.0D) d2 = 1.0D;
				
				double d3 = this.h[l1] / 8000.0D;
				
				if (d3 < 0.0D) d3 = -d3 * 0.3D;
				
				d3 = d3 * 3.0D - 2.0D;
				
				if (d3 > 1.0D) d3 = 1.0D;
				
				d3 /= 8.0D;
				d3 = 0.0D;
				
				if (d2 < 0.0D) d2 = 0.0D;
				
				d2 += 0.5D;
				d3 = d3 * (double) i1 / 16.0D;
				
				++l1;
				
				double d4 = (double) i1 / 2.0D;
				
				for (int k2 = 0; k2 < i1; ++k2){
					double d5 = 0.0D;
					double d6 = ((double) k2 - d4) * 8.0D / d2;
					
					if (d6 < 0.0D) d6 *= -1.0D;
					
					double d7 = this.e[k1] / 512.0D;
					double d8 = this.f[k1] / 512.0D;
					double d9 = (this.d[k1] / 10.0D + 1.0D) / 2.0D;
					
					if (d9 < 0.0D){
						d5 = d7;
					}else if (d9 > 1.0D){
						d5 = d8;
					}else{
						d5 = d7 + (d8 - d7) * d9;
					}
				
					d5 -= 8.0D;
					
					byte b0 = 32;
					double d10;
					
					if (k2 > i1 - b0){
						d10 = (double) ((float) (k2 - (i1 - b0)) / ((float) b0 - 1.0F));
						d5 = d5 * (1.0D - d10) + -30.0D * d10;
					}
					
					b0 = 8;
					
					if (k2 < b0){
						d10 = (double) ((float) (b0 - k2) / ((float) b0 - 1.0F));
						d5 = d5 * (1.0D - d10) + -30.0D * d10;
					}
					
					adouble[k1] = d5;
					++k1;
				}
			}
		}
		
		return adouble;
	}
	
	private void shapeLand(World world, int chunkX, int chunkZ, byte[] blocks){
		byte b0 = 2;
		int k = b0 + 1;
		
		int l = 128 / 4 + 1;
		int i1 = b0 + 1;
		
		this.q = this.a(this.q, chunkX * b0, 0, chunkZ * b0, k, l, i1);
		
		for (int j1 = 0; j1 < b0; ++j1){
			int k1 = 0;
			
			while (k1 < b0){
				int l1 = 0;
				
				while (true){
					
					if (l1 >= 128 / 4){
						++k1;
						break;
					}
					
					double d0 = 0.25D;
					double d1 = this.q[((j1 + 0) * i1 + k1 + 0) * l + l1 + 0];
					double d2 = this.q[((j1 + 0) * i1 + k1 + 1) * l + l1 + 0];
					double d3 = this.q[((j1 + 1) * i1 + k1 + 0) * l + l1 + 0];
					double d4 = this.q[((j1 + 1) * i1 + k1 + 1) * l + l1 + 0];
					double d5 = (this.q[((j1 + 0) * i1 + k1 + 0) * l + l1 + 1] - d1) * d0;
					double d6 = (this.q[((j1 + 0) * i1 + k1 + 1) * l + l1 + 1] - d2) * d0;
					double d7 = (this.q[((j1 + 1) * i1 + k1 + 0) * l + l1 + 1] - d3) * d0;
					double d8 = (this.q[((j1 + 1) * i1 + k1 + 1) * l + l1 + 1] - d4) * d0;
					
					for (int i2 = 0; i2 < 4; ++i2){
						double d9 = 0.125D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						
						for (int j2 = 0; j2 < 8; ++j2){
							int k2 = j2 + j1 * 8;
							
							k2 <<= 11;
							int l2 = 0 + k1 * 8;
							
							int i3 = k2 | l2 << 7 | l1 * 4 + i2;
							
							int j3 = 1 << 7;
							double d14 = 0.125D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;
							
							for (int k3 = 0; k3 < 8; ++k3){
								int l3 = Material.AIR.getId();
								
								if (d15 > 0.0D){
									l3 = Material.STONE.getId();
								}
								
								blocks[i3] = (byte) l3;
								
								i3 += j3;
								d15 += d16;
							}
							
							d10 += d12;
							d11 += d13;
						}
						
						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
					
					++l1;
				}
			}
		}
	}
	
	private void decorateLand(World world, int chunkX, int chunkZ, byte[] blocks){
		byte b0 = 63;
		double d0 = 0.03125D;
		
		this.t = this.o.a(this.t, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);
		
		for (int x = 0; x < 16; ++x){
			for (int z = 0; z < 16; ++z){
				int i1 = (int) (this.t[x + z * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
				int j1 = -1;
				
				byte b1 = (byte) Material.DIRT.getId();
				byte b2 = (byte) Material.GRASS.getId();
				
				for (int y = 127; y >= 0; --y){
					int l1 = z * 16 + x;
					int i2 = l1 * 128 + y;
					
					byte b3 = blocks[i2];
					
					if (b3 == 0){
						j1 = -1;
					}else if (b3 == Material.STONE.getId()){
						if (j1 == -1){
							if (i1 <= 0){
								b1 = 0;
							//	b2 = groundBlock;
							}else if (y >= b0 - 4 && y <= b0 + 1){
						//		b1 = surfaceBlock;
						//		b2 = groundBlock;
							}
							
							j1 = i1;
							blocks[i2] = b1;
						}else if (j1 > 0){
							--j1;
							blocks[i2] = b2;
							
							if (j1 == 0 && b2 == Material.SAND.getId()){
								j1 = this.random.nextInt(4);
								b2 = (byte) Material.SANDSTONE.getId();
							}
						}
					}
				}
			}
		}
	}
	
	public byte[] generate(World world, Random random, int chunkX, int chunkZ){
		if (this.random == null){
			this.random = new Random(world.getSeed());
			
			this.k = new NoiseGeneratorOctaves(this.random, 16);
			this.l = new NoiseGeneratorOctaves(this.random, 16);
			this.m = new NoiseGeneratorOctaves(this.random, 8);
			this.o = new NoiseGeneratorOctaves(this.random, 4);
			this.a = new NoiseGeneratorOctaves(this.random, 10);
			this.b = new NoiseGeneratorOctaves(this.random, 16);
		}
		
		byte[] blocks = new byte[32768];
		
		this.random.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
		
		this.shapeLand(world, chunkX, chunkZ, blocks);
		this.decorateLand(world, chunkX, chunkZ, blocks);
		
		return blocks;
	}
	
}
