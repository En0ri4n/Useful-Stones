package fr.eno.bluestone.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BluestoneDoorBlock extends DoorBlock implements IWaterLoggable
{
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BluestoneDoorBlock(Properties builder)
	{
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)
				.with(OPEN, Boolean.valueOf(false)).with(HINGE, DoorHingeSide.LEFT)
				.with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER)
				.with(WATERLOGGED, Boolean.valueOf(false)));
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
}
