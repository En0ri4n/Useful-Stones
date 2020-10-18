package fr.eno.usefulstones.block;

import net.minecraft.block.Block;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BluestoneBlock extends RedstoneBlock
{
	public BluestoneBlock()
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f));
	}
}
