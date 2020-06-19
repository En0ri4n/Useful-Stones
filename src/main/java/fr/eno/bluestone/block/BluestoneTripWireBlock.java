package fr.eno.bluestone.block;

import fr.eno.bluestone.init.InitBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.TripWireBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BluestoneTripWireBlock extends TripWireBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BluestoneTripWireBlock()
	{
		super(InitBlocks.BLUESTONE_TRIPWIRE_HOOK.get(), Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement());
		this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, Boolean.valueOf(false))
				.with(ATTACHED, Boolean.valueOf(false)).with(DISARMED, Boolean.valueOf(false))
				.with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false))
				.with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false))
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
