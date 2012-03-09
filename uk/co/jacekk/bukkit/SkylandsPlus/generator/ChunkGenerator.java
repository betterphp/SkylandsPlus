package uk.co.jacekk.bukkit.SkylandsPlus.generator;

import java.util.Random;

import net.minecraft.server.NoiseGeneratorOctaves;

import org.bukkit.Material;
import org.bukkit.World;

public class ChunkGenerator extends org.bukkit.generator.ChunkGenerator {
	
	private Random hellRNG;
	private NoiseGeneratorOctaves field_4240_i;
	private NoiseGeneratorOctaves field_4239_j;
	private NoiseGeneratorOctaves field_4238_k;
	private NoiseGeneratorOctaves field_4237_l;
	private NoiseGeneratorOctaves field_4236_m;
	public NoiseGeneratorOctaves field_4248_a;
	public NoiseGeneratorOctaves field_4247_b;
	private World worldObj;
	private double field_4234_o[];
//	public MapGenNetherBridge genNetherBridge;
	private double field_4233_p[];
	private double gravelNoise[];
	private double field_4231_r[];
//	private MapGenBase netherCaveGenerator;
	double field_4246_c[];
	double field_4245_d[];
	double field_4244_e[];
	double field_4243_f[];
	double field_4242_g[];
	
	private double[] func_4060_a(double par1ArrayOfDouble[], int par2, int par3, int par4, int par5, int par6, int par7)
	{
		if (par1ArrayOfDouble == null)
		{
			par1ArrayOfDouble = new double[par5 * par6 * par7];
		}

		double d = 684.412D;
		double d1 = 2053.236D;
		field_4243_f = field_4248_a.a(field_4243_f, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
		field_4242_g = field_4247_b.a(field_4242_g, par2, par3, par4, par5, 1, par7, 100D, 0.0D, 100D);
		field_4246_c = field_4238_k.a(field_4246_c, par2, par3, par4, par5, par6, par7, d / 80D, d1 / 60D, d / 80D);
		field_4245_d = field_4240_i.a(field_4245_d, par2, par3, par4, par5, par6, par7, d, d1, d);
		field_4244_e = field_4239_j.a(field_4244_e, par2, par3, par4, par5, par6, par7, d, d1, d);
		int i = 0;
		int j = 0;
		double ad[] = new double[par6];

		for (int k = 0; k < par6; k++)
		{
			ad[k] = Math.cos(((double)k * Math.PI * 6D) / (double)par6) * 2D;
			double d2 = k;

			if (k > par6 / 2)
			{
				d2 = par6 - 1 - k;
			}

			if (d2 < 4D)
			{
				d2 = 4D - d2;
				ad[k] -= d2 * d2 * d2 * 10D;
			}
		}

		for (int l = 0; l < par5; l++)
		{
			for (int i1 = 0; i1 < par7; i1++)
			{
				double d3 = (field_4243_f[j] + 256D) / 512D;

				if (d3 > 1.0D)
				{
					d3 = 1.0D;
				}

				double d4 = 0.0D;
				double d5 = field_4242_g[j] / 8000D;

				if (d5 < 0.0D)
				{
					d5 = -d5;
				}

				d5 = d5 * 3D - 3D;

				if (d5 < 0.0D)
				{
					d5 /= 2D;

					if (d5 < -1D)
					{
						d5 = -1D;
					}

					d5 /= 1.4D;
					d5 /= 2D;
					d3 = 0.0D;
				}
				else
				{
					if (d5 > 1.0D)
					{
						d5 = 1.0D;
					}

					d5 /= 6D;
				}

				d3 += 0.5D;
				d5 = (d5 * (double)par6) / 16D;
				j++;

				for (int j1 = 0; j1 < par6; j1++)
				{
					double d6 = 0.0D;
					double d7 = ad[j1];
					double d8 = field_4245_d[i] / 512D;
					double d9 = field_4244_e[i] / 512D;
					double d10 = (field_4246_c[i] / 10D + 1.0D) / 2D;

					if (d10 < 0.0D)
					{
						d6 = d8;
					}
					else if (d10 > 1.0D)
					{
						d6 = d9;
					}
					else
					{
						d6 = d8 + (d9 - d8) * d10;
					}

					d6 -= d7;

					if (j1 > par6 - 4)
					{
						double d11 = (float)(j1 - (par6 - 4)) / 3F;
						d6 = d6 * (1.0D - d11) + -10D * d11;
					}

					if ((double)j1 < d4)
					{
						double d12 = (d4 - (double)j1) / 4D;

						if (d12 < 0.0D)
						{
							d12 = 0.0D;
						}

						if (d12 > 1.0D)
						{
							d12 = 1.0D;
						}

						d6 = d6 * (1.0D - d12) + -10D * d12;
					}

					par1ArrayOfDouble[i] = d6;
					i++;
				}
			}
		}

		return par1ArrayOfDouble;
	}
	
	public void generateTerrain(int par1, int par2, byte par3ArrayOfByte[])
	{
		byte byte0 = 8;
		int i = byte0 + 1;
		byte byte2 = 17;
		int j = byte0 + 1;
		
		field_4234_o = this.func_4060_a(field_4234_o, par1 * byte0, 0, par2 * byte0, i, byte2, j);

		for (int k = 0; k < byte0; k++)
		{
			for (int l = 0; l < byte0; l++)
			{
				for (int i1 = 0; i1 < 16; i1++)
				{
					double d = 0.125D;
					double d1 = field_4234_o[((k + 0) * j + (l + 0)) * byte2 + (i1 + 0)];
					double d2 = field_4234_o[((k + 0) * j + (l + 1)) * byte2 + (i1 + 0)];
					double d3 = field_4234_o[((k + 1) * j + (l + 0)) * byte2 + (i1 + 0)];
					double d4 = field_4234_o[((k + 1) * j + (l + 1)) * byte2 + (i1 + 0)];
					double d5 = (field_4234_o[((k + 0) * j + (l + 0)) * byte2 + (i1 + 1)] - d1) * d;
					double d6 = (field_4234_o[((k + 0) * j + (l + 1)) * byte2 + (i1 + 1)] - d2) * d;
					double d7 = (field_4234_o[((k + 1) * j + (l + 0)) * byte2 + (i1 + 1)] - d3) * d;
					double d8 = (field_4234_o[((k + 1) * j + (l + 1)) * byte2 + (i1 + 1)] - d4) * d;
					
					for (int j1 = 0; j1 < 8; j1++)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						
						for (int k1 = 0; k1 < 4; k1++)
						{
							int l1 = k1 + k * 4 << 11 | 0 + l * 4 << 7 | i1 * 8 + j1;
							char c = '\200';
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;
							
							for (int i2 = 0; i2 < 4; i2++)
							{
								if (d15 <= 0.0D)
								{
									par3ArrayOfByte[l1] = (byte) Material.WOOL.getId();
								}
								else
								{
									par3ArrayOfByte[l1] = (byte) Material.AIR.getId();
								}
								
								l1 += c;
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
				}
			}
		}
	}

	public byte[] generate(World world, Random random, int chunkX, int chunkZ){
		if (this.hellRNG == null){
			hellRNG = new Random(world.getSeed());
			
			field_4233_p = new double[256];
			field_4231_r = new double[256];
			
			field_4240_i = new NoiseGeneratorOctaves(hellRNG, 16);
	        field_4239_j = new NoiseGeneratorOctaves(hellRNG, 16);
	        field_4238_k = new NoiseGeneratorOctaves(hellRNG, 8);
	        field_4237_l = new NoiseGeneratorOctaves(hellRNG, 4);
	        field_4236_m = new NoiseGeneratorOctaves(hellRNG, 4);
	        field_4248_a = new NoiseGeneratorOctaves(hellRNG, 10);
	        field_4247_b = new NoiseGeneratorOctaves(hellRNG, 16);
		}
		
		byte[] blocks = new byte[65536];
		
		this.generateTerrain(chunkX, chunkZ, blocks);
		
		return blocks;
	}
	
}
