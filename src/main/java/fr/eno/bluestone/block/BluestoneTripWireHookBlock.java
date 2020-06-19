package fr.eno.bluestone.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;

import fr.eno.bluestone.init.InitBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.TripWireHookBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BluestoneTripWireHookBlock extends TripWireHookBlock implements IWaterLoggable
{
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BluestoneTripWireHookBlock()
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement());
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)).with(ATTACHED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		this.calculateState(worldIn, pos, state, false, false, -1, (BlockState) null);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
	{
		this.calculateState(worldIn, pos, state, false, true, -1, (BlockState) null);
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
	{
		if (!isMoving && state.getBlock() != newState.getBlock())
		{
			boolean flag = state.get(ATTACHED);
			boolean flag1 = state.get(POWERED);
			if (flag || flag1)
			{
				this.calculateState(worldIn, pos, state, true, false, -1, (BlockState) null);
			}

			if (flag1)
			{
				worldIn.notifyNeighborsOfStateChange(pos, this);
				worldIn.notifyNeighborsOfStateChange(pos.offset(state.get(FACING).getOpposite()), this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	public void calculateState(World worldIn, BlockPos pos, BlockState hookState, boolean p_176260_4_, boolean p_176260_5_, int p_176260_6_, @Nullable BlockState p_176260_7_)
	{
		Direction direction = hookState.get(FACING);
		boolean flag = hookState.get(ATTACHED);
		boolean flag1 = hookState.get(POWERED);
		boolean flag2 = !p_176260_4_;
		boolean flag3 = false;
		int i = 0;
		BlockState[] ablockstate = new BlockState[42];

		for (int j = 1; j < 42; ++j)
		{
			BlockPos blockpos = pos.offset(direction, j);
			BlockState blockstate = worldIn.getBlockState(blockpos);
			if (blockstate.getBlock() == InitBlocks.BLUESTONE_TRIPWIRE_HOOK.get())
			{
				if (blockstate.get(FACING) == direction.getOpposite())
				{
					i = j;
				}
				break;
			}

			if (blockstate.getBlock() != InitBlocks.BLUESTONE_TRIPWIRE.get() && j != p_176260_6_)
			{
				ablockstate[j] = null;
				flag2 = false;
			} else
			{
				if (j == p_176260_6_)
				{
					blockstate = MoreObjects.firstNonNull(p_176260_7_, blockstate);
				}

				boolean flag4 = !blockstate.get(BluestoneTripWireBlock.DISARMED);
				boolean flag5 = blockstate.get(BluestoneTripWireBlock.POWERED);
				flag3 |= flag4 && flag5;
				ablockstate[j] = blockstate;
				if (j == p_176260_6_)
				{
					worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
					flag2 &= flag4;
				}
			}
		}

		flag2 = flag2 & i > 1;
		flag3 = flag3 & flag2;
		BlockState blockstate1 = this.getDefaultState().with(ATTACHED, Boolean.valueOf(flag2)).with(POWERED, Boolean.valueOf(flag3));
		if (i > 0)
		{
			BlockPos blockpos1 = pos.offset(direction, i);
			Direction direction1 = direction.getOpposite();
			worldIn.setBlockState(blockpos1, blockstate1.with(FACING, direction1), 3);
			this.notifyNeighbors(worldIn, blockpos1, direction1);
			this.playSound(worldIn, blockpos1, flag2, flag3, flag, flag1);
		}

		this.playSound(worldIn, pos, flag2, flag3, flag, flag1);
		if (!p_176260_4_)
		{
			worldIn.setBlockState(pos, blockstate1.with(FACING, direction), 3);
			if (p_176260_5_)
			{
				this.notifyNeighbors(worldIn, pos, direction);
			}
		}

		if (flag != flag2)
		{
			for (int k = 1; k < i; ++k)
			{
				BlockPos blockpos2 = pos.offset(direction, k);
				BlockState blockstate2 = ablockstate[k];
				if (blockstate2 != null)
				{
					worldIn.setBlockState(blockpos2, blockstate2.with(ATTACHED, Boolean.valueOf(flag2)), 3);
					if (!worldIn.getBlockState(blockpos2).isAir(worldIn, blockpos2))
					{
						;
					}
				}
			}
		}

	}

	private void playSound(World worldIn, BlockPos pos, boolean p_180694_3_, boolean p_180694_4_, boolean p_180694_5_, boolean p_180694_6_)
	{
		if (p_180694_4_ && !p_180694_6_)
		{
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_TRIPWIRE_CLICK_ON, SoundCategory.BLOCKS, 0.4F, 0.6F);
		} else if (!p_180694_4_ && p_180694_6_)
		{
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_TRIPWIRE_CLICK_OFF, SoundCategory.BLOCKS, 0.4F, 0.5F);
		} else if (p_180694_3_ && !p_180694_5_)
		{
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_TRIPWIRE_ATTACH, SoundCategory.BLOCKS, 0.4F, 0.7F);
		} else if (!p_180694_3_ && p_180694_5_)
		{
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_TRIPWIRE_DETACH, SoundCategory.BLOCKS, 0.4F, 1.2F / (worldIn.rand.nextFloat() * 0.2F + 0.9F));
		}

	}

	private void notifyNeighbors(World worldIn, BlockPos pos, Direction side)
	{
		worldIn.notifyNeighborsOfStateChange(pos, this);
		worldIn.notifyNeighborsOfStateChange(pos.offset(side.getOpposite()), this);
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
