package fr.eno.bluestone.block;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.DyeColor;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RedstoneSide;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ILightReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BluestoneWireBlock extends RedstoneWireBlock implements IWaterLoggable, IBlockColor
{
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BluestoneWireBlock()
	{
		super(Block.Properties.create(Material.MISCELLANEOUS, DyeColor.BLUE).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(0.0F));

		this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, RedstoneSide.NONE).with(EAST, RedstoneSide.NONE).with(SOUTH, RedstoneSide.NONE).with(WEST, RedstoneSide.NONE).with(POWER, Integer.valueOf(0)).with(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, Direction side)
	{
		return true;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return !state.get(WATERLOGGED);
	}

	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state)
	{
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public void fillStateContainer(Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);

		builder.add(WATERLOGGED);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if(stateIn.get(POWER) > 0)
		{
			worldIn.addParticle(new RedstoneParticleData(0, 0, rand.nextFloat(), 1.0F), (float) rand.nextInt(10) / 10F, (float) rand.nextInt(10) / 10F, (float) rand.nextInt(10) / 10F, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public int getColor(BlockState state, ILightReader light, BlockPos pos, int p_getColor_4_)
	{
		int blue = 70;

		if (state.get(POWER) > 0)
		{
			blue = state.get(POWER) * 255 / 15;
		}
		return new Color(0, 0, blue).getRGB();
	}
}
