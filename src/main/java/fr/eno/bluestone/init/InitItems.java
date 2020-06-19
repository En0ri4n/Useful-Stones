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

	public static final RegistryObject<Item> BLUESTONE_OAK_BUTTON = ITEMS.register("bluestone_oak_button", () -> new ItemBlock(InitBlocks.BLUESTONE_OAK_BUTTON.get()));
	public static final RegistryObject<Item> BLUESTONE_SPRUCE_BUTTON = ITEMS.register("bluestone_spruce_button", () -> new ItemBlock(InitBlocks.BLUESTONE_SPRUCE_BUTTON.get()));
	public static final RegistryObject<Item> BLUESTONE_BIRCH_BUTTON = ITEMS.register("bluestone_birch_button", () -> new ItemBlock(InitBlocks.BLUESTONE_BIRCH_BUTTON.get()));
	public static final RegistryObject<Item> BLUESTONE_JUNGLE_BUTTON = ITEMS.register("bluestone_jungle_button", () -> new ItemBlock(InitBlocks.BLUESTONE_JUNGLE_BUTTON.get()));
	public static final RegistryObject<Item> BLUESTONE_ACACIA_BUTTON = ITEMS.register("bluestone_acacia_button", () -> new ItemBlock(InitBlocks.BLUESTONE_ACACIA_BUTTON.get()));
	public static final RegistryObject<Item> BLUESTONE_DARK_OAK_BUTTON = ITEMS.register("bluestone_dark_oak_button", () -> new ItemBlock(InitBlocks.BLUESTONE_DARK_OAK_BUTTON.get()));
	public static final RegistryObject<Item> BLUESTONE_STONE_BUTTON = ITEMS.register("bluestone_stone_button", () -> new ItemBlock(InitBlocks.BLUESTONE_STONE_BUTTON.get()));

	public static final RegistryObject<Item> BLUESTONE_OAK_PRESSURE_PLATE = ITEMS.register("bluestone_oak_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_OAK_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_SPRUCE_PRESSURE_PLATE = ITEMS.register("bluestone_spruce_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_SPRUCE_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_BIRCH_PRESSURE_PLATE = ITEMS.register("bluestone_birch_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_BIRCH_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_JUNGLE_PRESSURE_PLATE = ITEMS.register("bluestone_jungle_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_JUNGLE_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_ACACIA_PRESSURE_PLATE = ITEMS.register("bluestone_acacia_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_ACACIA_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_DARK_OAK_PRESSURE_PLATE = ITEMS.register("bluestone_dark_oak_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_DARK_OAK_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_STONE_PRESSURE_PLATE = ITEMS.register("bluestone_stone_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_STONE_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_LIGHT_WEIGHTED_PRESSURE_PLATE = ITEMS.register("bluestone_light_weighted_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_LIGHT_WEIGHTED_PRESSURE_PLATE.get()));
	public static final RegistryObject<Item> BLUESTONE_HEAVY_WEIGHTED_PRESSURE_PLATE = ITEMS.register("bluestone_heavy_weighted_pressure_plate", () -> new ItemBlock(InitBlocks.BLUESTONE_HEAVY_WEIGHTED_PRESSURE_PLATE.get()));

	public static final RegistryObject<Item> BLUESTONE_STRING = ITEMS.register("bluestone_string", () -> new ItemBlock(InitBlocks.BLUESTONE_TRIPWIRE.get()));
	public static final RegistryObject<Item> BLUESTONE_TRIPWIRE_HOOK = ITEMS.register("bluestone_tripwire_hook", () -> new ItemBlock(InitBlocks.BLUESTONE_TRIPWIRE_HOOK.get()));

	public static final RegistryObject<Item> BLUESTONE_OAK_DOOR = ITEMS.register("bluestone_oak_door", () -> new ItemBlock(InitBlocks.BLUESTONE_OAK_DOOR.get()));
	public static final RegistryObject<Item> BLUESTONE_BIRCH_DOOR = ITEMS.register("bluestone_birch_door", () -> new ItemBlock(InitBlocks.BLUESTONE_BIRCH_DOOR.get()));
	public static final RegistryObject<Item> BLUESTONE_SPRUCE_DOOR = ITEMS.register("bluestone_spruce_door", () -> new ItemBlock(InitBlocks.BLUESTONE_SPRUCE_DOOR.get()));
	public static final RegistryObject<Item> BLUESTONE_ACACIA_DOOR = ITEMS.register("bluestone_acacia_door", () -> new ItemBlock(InitBlocks.BLUESTONE_ACACIA_DOOR.get()));
	public static final RegistryObject<Item> BLUESTONE_DARK_OAK_DOOR = ITEMS.register("bluestone_dark_oak_door", () -> new ItemBlock(InitBlocks.BLUESTONE_DARK_OAK_DOOR.get()));
	public static final RegistryObject<Item> BLUESTONE_JUNGLE_DOOR = ITEMS.register("bluestone_jungle_door", () -> new ItemBlock(InitBlocks.BLUESTONE_JUNGLE_DOOR.get()));
	public static final RegistryObject<Item> BLUESTONE_IRON_DOOR = ITEMS.register("bluestone_iron_door", () -> new ItemBlock(InitBlocks.BLUESTONE_IRON_DOOR.get()));
}