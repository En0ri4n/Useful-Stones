package fr.eno.bluestone.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BluestoneTorchBlock extends RedstoneTorchBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BluestoneTorchBlock()
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement());
	    this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(true)).with(WATERLOGGED, false));
	    
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
}
