package fr.eno.bluestone.init;

import fr.eno.bluestone.References;
import fr.eno.bluestone.block.BluestoneBlock;
import fr.eno.bluestone.block.BluestoneComparator;
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
	public static final RegistryObject<BluestoneComparator> BLUESTONE_COMPARATOR = BLOCKS.register("bluestone_comparator", () -> new BluestoneComparator());
}