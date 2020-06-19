package fr.eno.bluestone.init;

import fr.eno.bluestone.References;
import fr.eno.bluestone.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Item.class, References.MOD_ID);
	
	public static final RegistryObject<Item> BLUESTONE = ITEMS.register("bluestone", () -> new ItemBlock(InitBlocks.BLUESTONE_WIRE.get()));
	public static final RegistryObject<Item> BLUESTONE_REPEATER = ITEMS.register("bluestone_repeater", () -> new ItemBlock(InitBlocks.BLUESTONE_REPEATER.get()));
	public static final RegistryObject<Item> BLUESTONE_TORCH = ITEMS.register("bluestone_torch", () -> new ItemBlock(InitBlocks.BLUESTONE_TORCH.get()));
	public static final RegistryObject<Item> BLUESTONE_BLOCK = ITEMS.register("bluestone_block", () -> new ItemBlock(InitBlocks.BLUESTONE_BLOCK.get()));
	public static final RegistryObject<Item> BLUESTONE_COMPARATOR = ITEMS.register("bluestone_comparator", () -> new ItemBlock(InitBlocks.BLUESTONE_COMPARATOR.get()));
	public static final RegistryObject<Item> BLUESTONE_LEVER = ITEMS.register("bluestone_lever", () -> new ItemBlock(InitBlocks.BLUESTONE_LEVER.get()));

}