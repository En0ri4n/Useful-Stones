package fr.eno.bluestone.init;

import fr.eno.bluestone.References;
import fr.eno.bluestone.block.BluestoneBlock;
import fr.eno.bluestone.block.BluestoneButtonBlock;
import fr.eno.bluestone.block.BluestoneComparatorBlock;
import fr.eno.bluestone.block.BluestoneLeverBlock;
import fr.eno.bluestone.block.BluestoneRepeaterBlock;
import fr.eno.bluestone.block.BluestoneTorchBlock;
import fr.eno.bluestone.block.BluestoneWireBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitBlocks
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Block.class, References.MOD_ID);
	
	public static final RegistryObject<BluestoneRepeaterBlock> BLUESTONE_REPEATER = BLOCKS.register("bluestone_repeater", () -> new BluestoneRepeaterBlock());
	public static final RegistryObject<BluestoneWireBlock> BLUESTONE_WIRE = BLOCKS.register("bluestone_wire", () -> new BluestoneWireBlock());
	public static final RegistryObject<BluestoneTorchBlock> BLUESTONE_TORCH = BLOCKS.register("bluestone_torch", () -> new BluestoneTorchBlock());
	public static final RegistryObject<BluestoneBlock> BLUESTONE_BLOCK = BLOCKS.register("bluestone_block", () -> new BluestoneBlock());
	public static final RegistryObject<BluestoneComparatorBlock> BLUESTONE_COMPARATOR = BLOCKS.register("bluestone_comparator", () -> new BluestoneComparatorBlock());
	public static final RegistryObject<BluestoneLeverBlock> BLUESTONE_LEVER = BLOCKS.register("bluestone_lever", () -> new BluestoneLeverBlock());
	
	public static final RegistryObject<BluestoneButtonBlock> BLUESTONE_OAK_BUTTON = BLOCKS.register("bluestone_oak_button", () -> new BluestoneButtonBlock(true));
	public static final RegistryObject<BluestoneButtonBlock> BLUESTONE_SPRUCE_BUTTON = BLOCKS.register("bluestone_spruce_button", () -> new BluestoneButtonBlock(true));
	public static final RegistryObject<BluestoneButtonBlock> BLUESTONE_BIRCH_BUTTON = BLOCKS.register("bluestone_birch_button", () -> new BluestoneButtonBlock(true));
	public static final RegistryObject<BluestoneButtonBlock> BLUESTONE_JUNGLE_BUTTON = BLOCKS.register("bluestone_jungle_button", () -> new BluestoneButtonBlock(true));
	public static final RegistryObject<BluestoneButtonBlock> BLUESTONE_ACACIA_BUTTON = BLOCKS.register("bluestone_acacia_button", () -> new BluestoneButtonBlock(true));
	public static final RegistryObject<BluestoneButtonBlock> BLUESTONE_DARK_OAK_BUTTON = BLOCKS.register("bluestone_dark_oak_button", () -> new BluestoneButtonBlock(true));
	
	public static final RegistryObject<BluestoneButtonBlock> BLUESTONE_STONE_BUTTON = BLOCKS.register("bluestone_stone_button", () -> new BluestoneButtonBlock(false));
	   

}