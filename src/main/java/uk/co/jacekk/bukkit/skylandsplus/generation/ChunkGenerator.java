package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.Blocks;
import net.minecraft.server.v1_7_R1.NoiseGeneratorOctaves;
import net.minecraft.server.v1_7_R1.WorldGenCanyon;
import net.minecraft.server.v1_7_R1.WorldGenCaves;
import net.minecraft.server.v1_7_R1.WorldGenCavesHell;
import net.minecraft.server.v1_7_R1.WorldGenMineshaft;
import net.minecraft.server.v1_7_R1.WorldGenNether;
import net.minecraft.server.v1_7_R1.WorldGenStronghold;
import net.minecraft.server.v1_7_R1.WorldGenVillage;
import net.minecraft.server.v1_7_R1.WorldGenLargeFeature;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.generator.BlockPopulator;

public class ChunkGenerator extends org.bukkit.generator.ChunkGenerator {
	
	private Random random;
	
	private NoiseGeneratorOctaves k;
	private NoiseGeneratorOctaves l;
	private NoiseGeneratorOctaves m;
	private NoiseGeneratorOctaves o;
	private NoiseGeneratorOctaves a;
	private NoiseGeneratorOctaves b;
	
	private WorldGenCaves caveGen;
	
	private WorldGenCanyon canyonGen;
	private WorldGenStronghold strongholdGen;
	private WorldGenMineshaft mineshaftGen;
	private WorldGenVillage villageGen;
	private WorldGenLargeFeature largefeatureGen;
	
	private WorldGenCavesHell caveGenNether;
	private WorldGenNether genNetherFort;
	
	private double[] q;
	private double[] t = new double[256];
	
	double[] d;
	double[] e;
	double[] f;
	double[] g;
	double[] h;
	
	int[][] i = new int[32][32];
	
	private int offset, liquid;
	private boolean only, canyon, stronghold, mineshaft, village, largefeature, bedrock;
	private boolean no_plains, no_desert, no_forest, no_jungle, no_taiga, no_ice, no_ocean;
	private boolean no_mushroom = true;
	private boolean no_swampland = true;
	private Biome onlybiome, plains, desert, forest, jungle, taiga, ice, ocean;
	private Biome mushroom = Biome.FOREST;
	private Biome swampland = Biome.ICE_PLAINS;
	byte liquid_id;
	
	public ChunkGenerator(String id){
		String tokens[] = id.split("[,]");
		
		for (int i = 0; i < tokens.length; i++){
			if (tokens[i].matches("offset={1}-?\\d{1,3}")){
				offset = Integer.parseInt(tokens[i].substring(7));
			}else if (tokens[i].equals("canyon")){
				canyon = true;
			}else if (tokens[i].equals("stronghold")){
				stronghold = true;
			}else if (tokens[i].equals("mineshaft")){
				mineshaft = true;
			}else if (tokens[i].equals("village")){
				village = true;
			}else if (tokens[i].equals("largefeatures")){
				largefeature = true;
			}else if (tokens[i].equals("bedrock")){
				bedrock = true;
			}else if (tokens[i].equals("no-desert")){
				no_desert = true;
				desert = Biome.PLAINS;
			}else if (tokens[i].equals("no-forest")){
				no_forest = true;
				forest = Biome.PLAINS;
			}else if (tokens[i].equals("no-jungle")){
				no_jungle = true;
				jungle = Biome.PLAINS;
			}else if (tokens[i].equals("no-taiga")){
				no_taiga = true;
				taiga = Biome.PLAINS;
			}else if (tokens[i].equals("no-ice")){
				no_ice = true;
				ice = Biome.PLAINS;
			}else if (tokens[i].equals("no-ocean")){
				no_ocean = true;
				ocean = Biome.PLAINS;
			}else if (tokens[i].equals("mushroom")){
				no_mushroom = false;
			}else if (tokens[i].equals("swampland")){
				no_swampland = false;
			}else if (tokens[i].matches("only={1}[A-Z_]+")){
				only = true;
				onlybiome = Biome.valueOf(tokens[i].substring(5));
			}else if (tokens[i].matches("plains={1}[A-Z_]+")){
				no_plains = true;
				plains = Biome.valueOf(tokens[i].substring(7));
			}else if (tokens[i].matches("desert={1}[A-Z_]+")){
				no_desert = true;
				desert = Biome.valueOf(tokens[i].substring(7));
			}else if (tokens[i].matches("forest={1}[A-Z_]+")){
				no_forest = true;
				forest = Biome.valueOf(tokens[i].substring(7));
			}else if (tokens[i].matches("jungle={1}[A-Z_]+")){
				no_jungle = true;
				jungle = Biome.valueOf(tokens[i].substring(7));
			}else if (tokens[i].matches("taiga={1}[A-Z_]+")){
				no_taiga = true;
				taiga = Biome.valueOf(tokens[i].substring(6));
			}else if (tokens[i].matches("ice={1}[A-Z_]+")){
				no_ice = true;
				ice = Biome.valueOf(tokens[i].substring(4));
			}else if (tokens[i].matches("mushroom={1}[A-Z_]+")){
				no_mushroom = true;
				mushroom = Biome.valueOf(tokens[i].substring(9));
			}else if (tokens[i].matches("swampland={1}[A-Z_]+")){
				no_swampland = true;
				swampland = Biome.valueOf(tokens[i].substring(10));
			}else if (tokens[i].matches("ocean={1}[A-Z_]+")){
				no_ocean = true;
				ocean = Biome.valueOf(tokens[i].substring(5));
			}else if (tokens[i].matches("water={1}\\d{1,3}")){
				liquid = Integer.parseInt(tokens[i].substring(6)) + 1;
				liquid_id = (byte) Material.WATER.getId();
			}else if (tokens[i].matches("lava={1}\\d{1,3}")){
				liquid = Integer.parseInt(tokens[i].substring(5)) + 1;
				liquid_id = (byte) Material.LAVA.getId();
			}
		}
	}
	
	public List<BlockPopulator> getDefaultPopulators(World world){
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		
		switch (world.getEnvironment()){
			case NORMAL:
				populators.add(new BiomePopulator());
				populators.add(new SnowPopulator());
			break;
			
			case THE_END:
				populators.add(new EndTowerPopulator(world));
			break;
			
			case NETHER:
				populators.add(new NetherSoulSandPopulator(world));
				populators.add(new NetherFirePopulator(world));
				populators.add(new NetherGlowstonePopulator(world));
			break;
		}
		
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
				
				if (d2 > 1.0D)
					d2 = 1.0D;
				
				double d3 = this.h[l1] / 8000.0D;
				
				if (d3 < 0.0D)
					d3 = -d3 * 0.3D;
				
				d3 = d3 * 3.0D - 2.0D;
				
				if (d3 > 1.0D)
					d3 = 1.0D;
				
				d3 /= 8.0D;
				d3 = 0.0D;
				
				if (d2 < 0.0D)
					d2 = 0.0D;
				
				d2 += 0.5D;
				d3 = d3 * i1 / 16.0D;
				
				++l1;
				
				double d4 = i1 / 2.0D;
				
				for (int k2 = 0; k2 < i1; ++k2){
					double d5 = 0.0D;
					double d6 = (k2 - d4) * 8.0D / d2;
					
					if (d6 < 0.0D)
						d6 *= -1.0D;
					
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
						d10 = (k2 - (i1 - b0)) / (b0 - 1.0F);
						d5 = d5 * (1.0D - d10) + -30.0D * d10;
					}
					
					b0 = 8;
					
					if (k2 < b0){
						d10 = (b0 - k2) / (b0 - 1.0F);
						d5 = d5 * (1.0D - d10) + -30.0D * d10;
					}
					
					adouble[k1] = d5;
					++k1;
				}
			}
		}
		return adouble;
	}
	
	private void shapeLand(World world, int chunkX, int chunkZ, Block[] blocks){
		byte b0 = 2;
		int k = b0 + 1;
		
		int l = 128 / 4 + 1;
		int i1 = b0 + 1;
		
		this.q = this.a(this.q, chunkX * b0, 0, chunkZ * b0, k, l, i1);
		
		Block blockType;
		
		switch (world.getEnvironment()){
			case NETHER:
				blockType = Blocks.NETHERRACK;
			break;
			
			case THE_END:
				blockType = Blocks.WHITESTONE;
			break;
			
			default:
				blockType = Blocks.STONE;
			break;
		}
		
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
					double d1 = this.q[((j1 + 0) * i1 + (k1 + 0)) * l + l1 + 0];
					double d2 = this.q[((j1 + 0) * i1 + (k1 + 1)) * l + l1 + 0];
					double d3 = this.q[((j1 + 1) * i1 + (k1 + 0)) * l + l1 + 0];
					double d4 = this.q[((j1 + 1) * i1 + (k1 + 1)) * l + l1 + 0];
					double d5 = (this.q[((j1 + 0) * i1 + (k1 + 0)) * l + l1 + 1] - d1) * d0;
					double d6 = (this.q[((j1 + 0) * i1 + (k1 + 1)) * l + l1 + 1] - d2) * d0;
					double d7 = (this.q[((j1 + 1) * i1 + (k1 + 0)) * l + l1 + 1] - d3) * d0;
					double d8 = (this.q[((j1 + 1) * i1 + (k1 + 1)) * l + l1 + 1] - d4) * d0;
					
					for (int i2 = 0; i2 < 4; ++i2){
						double d9 = 0.125D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						
						for (int j2 = 0; j2 < 8; ++j2){
							int i3 = j2 + j1 * 8 << 11 | 0 + k1 * 8 << 7 | l1 * 4 + i2;
							
							int j3 = 1 << 7;
							double d14 = 0.125D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;
							
							for (int k3 = 0; k3 < 8; ++k3){
								if (d15 > 0.0D){
									blocks[i3] = blockType;
								}
								
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
	
	private void decorateLand(int chunkX, int chunkZ, Block[] blocks, BiomeGrid biomes){
		double d0 = 0.03125D;
		
		this.t = this.o.a(this.t, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);
		
		for (int z = 0; z < 16; ++z){
			for (int x = 0; x < 16; ++x){
				int i1 = (int) (this.t[z + x * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
				int j1 = -1;
				
				Biome biome = biomes.getBiome(x, z);
				
				Block b1, b2;
				
				if (only == true){
					biomes.setBiome(x, z, onlybiome);
					biome = onlybiome;
				}else{
					if (no_plains == true){
						if (biome == Biome.PLAINS){
							biomes.setBiome(x, z, plains);
							biome = plains;
						}
					}
					if (no_desert == true){
						if (biome == Biome.DESERT || biome == Biome.DESERT_HILLS){
							biomes.setBiome(x, z, desert);
							biome = desert;
						}
					}
					if (no_forest == true){
						if (biome == Biome.FOREST || biome == Biome.FOREST_HILLS){
							biomes.setBiome(x, z, forest);
							biome = forest;
						}
					}
					if (no_jungle == true){
						if (biome == Biome.JUNGLE || biome == Biome.JUNGLE_HILLS){
							biomes.setBiome(x, z, jungle);
							biome = jungle;
						}
					}
					if (no_taiga == true){
						if (biome == Biome.TAIGA || biome == Biome.TAIGA_HILLS){
							biomes.setBiome(x, z, taiga);
							biome = taiga;
						}
					}
					if (no_ice == true){
						if (biome == Biome.ICE_PLAINS || biome == Biome.ICE_MOUNTAINS){
							biomes.setBiome(x, z, ice);
							biome = ice;
						}
					}
					if (no_mushroom == true){
						if (biome == Biome.MUSHROOM_ISLAND || biome == Biome.MUSHROOM_SHORE){
							biomes.setBiome(x, z, mushroom);
							biome = mushroom;
						}
					}
					if (no_swampland == true){
						if (biome == Biome.SWAMPLAND){
							biomes.setBiome(x, z, swampland);
							biome = swampland;
						}
					}
					if (no_ocean == true){
						if (biome == Biome.OCEAN){
							biomes.setBiome(x, z, ocean);
							biome = ocean;
						}
					}
				}
				
				if (biome == Biome.DESERT || biome == Biome.DESERT_HILLS){
					b1 = Blocks.SAND;
					b2 = Blocks.SAND;
				}else if (biome == Biome.HELL){
					b1 = Blocks.NETHERRACK;
					b2 = Blocks.NETHERRACK;
				}else if (biome == Biome.MUSHROOM_ISLAND || biome == Biome.MUSHROOM_SHORE){
					b1 = Blocks.MYCEL;
					b2 = Blocks.DIRT;
				}else{
					b1 = Blocks.GRASS;
					b2 = Blocks.DIRT;
				}
				
				for (int y = 127; y >= 0; --y){
					int l1 = x * 16 + z;
					int i2 = l1 * 128 + y;
					
					Block b3 = blocks[i2];
					
					if (b3 == Blocks.AIR){
						j1 = -1;
					}else if (b3 == Blocks.STONE){
						if (j1 == -1){
							j1 = i1;
							blocks[i2] = b1;
						}else if (j1 > 0){
							--j1;
							blocks[i2] = b2;
							
							if (j1 == 0 && b2 == Blocks.SAND){
								j1 = this.random.nextInt(4);
								b2 = Blocks.SANDSTONE;
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomes){
		Environment environment = world.getEnvironment();
		
		if (this.random == null){
			this.random = new Random(world.getSeed());
			
			this.k = new NoiseGeneratorOctaves(this.random, 16);
			this.l = new NoiseGeneratorOctaves(this.random, 16);
			this.m = new NoiseGeneratorOctaves(this.random, 8);
			this.o = new NoiseGeneratorOctaves(this.random, 4);
			this.a = new NoiseGeneratorOctaves(this.random, 10);
			this.b = new NoiseGeneratorOctaves(this.random, 16);
			
			if (environment == Environment.NORMAL){
				this.caveGen = new WorldGenCaves();
				this.canyonGen = new WorldGenCanyon();
				this.strongholdGen = new WorldGenStronghold();
				this.mineshaftGen = new WorldGenMineshaft();
				this.villageGen = new WorldGenVillage();
				this.largefeatureGen = new WorldGenLargeFeature();
				
			}else if (environment == Environment.NETHER){
				this.caveGenNether = new WorldGenCavesHell();
				this.genNetherFort = new WorldGenNether();
			}
		}
		
		net.minecraft.server.v1_7_R1.World mcWorld = ((CraftWorld) world).getHandle();
		
		Block[] blocks = new Block[65536];
		
		this.random.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		
		this.shapeLand(world, chunkX, chunkZ, blocks);
		
		if (environment == Environment.NORMAL){
			this.caveGen.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
			
			if (canyon == true){
				this.canyonGen.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
			}
			if (stronghold == true){
				this.strongholdGen.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
			}
			if (mineshaft == true){
				this.mineshaftGen.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
			}
			if (village == true){
				this.villageGen.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
			}
			if (largefeature == true){
				this.largefeatureGen.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
			}
		}else if (environment == Environment.NETHER){
			this.caveGenNether.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
			this.genNetherFort.a(mcWorld.chunkProvider, mcWorld, chunkX, chunkZ, blocks);
		}
		
		this.decorateLand(chunkX, chunkZ, blocks, biomes);
		
		int cut_top = 0;
		int cut_bottom = 0;
		
		if (offset > 128){
			cut_top = offset - 128;
		}else if (offset < 0){
			cut_bottom = -offset;
		}
		
		byte[][] chunk = new byte[16][4096];
		
		// TODO: Do this in a nice way.
		// Yes. Do it. :3
		for (int x = 0; x < 16; ++x){
			for (int y = 0 + cut_bottom; y < 128 - cut_top; ++y){
				for (int z = 0; z < 16; ++z){
					chunk[y + offset >> 4][((y + offset & 0xF) << 8) | (z << 4) | x] = ((Integer) Block.REGISTRY.b(blocks[(x * 16 + z) * 128 + y])).byteValue();
					
					if (bedrock == true && y == 0){
						chunk[y - cut_bottom >> 4][((y - cut_bottom & 0xF) << 8) | (z << 4) | x] = (byte) Material.BEDROCK.getId();
					}
					
					if (liquid > 0 && y <= liquid - 1 + cut_bottom && chunk[y - cut_bottom >> 4][((y - cut_bottom & 0xF) << 8) | (z << 4) | x] == 0){
						chunk[y - cut_bottom >> 4][((y - cut_bottom & 0xF) << 8) | (z << 4) | x] = liquid_id;
					}
				}
			}
		}
		
		return chunk;
	}
	
}
