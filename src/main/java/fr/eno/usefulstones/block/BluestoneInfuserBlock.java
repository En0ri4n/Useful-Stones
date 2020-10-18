package fr.eno.usefulstones.block;

import fr.eno.usefulstones.tileentity.TileBluestoneInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class BluestoneInfuserBlock extends Block
{
	private static final VoxelShape SHAPE_BOTTOM = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);
	private static final VoxelShape SHAPE_TOP = Block.makeCuboidShape(4.0D, 14.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	private static final VoxelShape INFUSER_SHAPE = VoxelShapes.or(SHAPE_TOP, SHAPE_BOTTOM);
	
	public BluestoneInfuserBlock()
	{
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).harvestTool(ToolType.PICKAXE));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return INFUSER_SHAPE;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if(!worldIn.isRemote)
		{
			TileEntity tile = worldIn.getTileEntity(pos);
			
			if(tile instanceof TileBluestoneInfuser)
			{
				NetworkHooks.openGui((ServerPlayerEntity) player, (TileBluestoneInfuser) tile, pos);
				
				return ActionResultType.PASS;
			}
		}
		
		return ActionResultType.FAIL;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new TileBluestoneInfuser();
	}
}
