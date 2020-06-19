package fr.eno.bluestone.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BluestoneLeverBlock extends LeverBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BluestoneLeverBlock()
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)).with(FACE, AttachFace.WALL).with(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (worldIn.isRemote)
		{
			BlockState blockstate1 = state.cycle(POWERED);
			if (blockstate1.get(POWERED))
			{
				addParticles(blockstate1, worldIn, pos, 1.0F);
			}

			return ActionResultType.SUCCESS;
		} else
		{
			BlockState blockstate = this.func_226939_d_(state, worldIn, pos);
			float f = blockstate.get(POWERED) ? 0.6F : 0.5F;
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
			return ActionResultType.SUCCESS;
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (stateIn.get(POWERED) && rand.nextFloat() < 0.25F)
		{
			addParticles(stateIn, worldIn, pos, 0.5F);
		}
	}

	private static void addParticles(BlockState state, IWorld worldIn, BlockPos pos, float alpha)
	{
		Direction direction = state.get(HORIZONTAL_FACING).getOpposite();
		Direction direction1 = getFacing(state).getOpposite();
		double d0 = (double) pos.getX() + 0.5D + 0.1D * (double) direction.getXOffset() + 0.2D * (double) direction1.getXOffset();
		double d1 = (double) pos.getY() + 0.5D + 0.1D * (double) direction.getYOffset() + 0.2D * (double) direction1.getYOffset();
		double d2 = (double) pos.getZ() + 0.5D + 0.1D * (double) direction.getZOffset() + 0.2D * (double) direction1.getZOffset();
		worldIn.addParticle(new RedstoneParticleData(0.0F, 0.0F, 1.0F, alpha), d0, d1, d2, 0.0D, 0.0D, 0.0D);
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
