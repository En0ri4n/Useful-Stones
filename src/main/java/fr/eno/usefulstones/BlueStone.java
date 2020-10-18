package fr.eno.usefulstones;

import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.eno.usefulstones.block.BluestoneWireBlock;
import fr.eno.usefulstones.client.gui.BluestoneInfuserScreen;
import fr.eno.usefulstones.init.InitBlocks;
import fr.eno.usefulstones.init.InitContainers;
import fr.eno.usefulstones.init.InitItems;
import fr.eno.usefulstones.init.InitTileEntities;
import fr.eno.usefulstones.packets.NetworkRegistryHandler;
import fr.eno.usefulstones.recipes.InfuserRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod(References.MOD_ID)
public class BlueStone
{
	public static final Logger LOGGER = LogManager.getLogger(References.MOD_NAME);

	public BlueStone()
	{
		IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();
		
		mod.addListener(this::commonSetup);
		mod.addListener(this::clientSetup);
		
		InitBlocks.BLOCKS.register(mod);
		InitItems.ITEMS.register(mod);
		InitContainers.CONTAINERS.register(mod);
		InitTileEntities.TILE_ENTITIES.register(mod);
	}

	private void commonSetup(final FMLCommonSetupEvent event)
	{
		NetworkRegistryHandler.registerMessages();
		InfuserRecipes.registerRecipes();
		
		ForgeRegistries.BIOMES.getValues().stream().filter(biome -> biome.getCategory() != Category.NETHER).filter(biome -> biome.getCategory() != Category.THEEND)
		.forEach(biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, InitBlocks.BLUESTONE_ORE.get().getDefaultState(), 8))
				.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 16)))));
	}

	private void clientSetup(final FMLClientSetupEvent event)
	{
		Minecraft.getInstance().getBlockColors().register(new BluestoneWireBlock(), InitBlocks.BLUESTONE_WIRE.get());
		
		Predicate<RenderType> cutoutPredicate = renderType -> renderType == RenderType.getCutout();
		
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_COMPARATOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_TORCH.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_REPEATER.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_WIRE.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_TRIPWIRE_HOOK.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_TRIPWIRE.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_ACACIA_DOOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_BIRCH_DOOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_DARK_OAK_DOOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_JUNGLE_DOOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_SPRUCE_DOOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_OAK_DOOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_IRON_DOOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_INFUSER.get(), cutoutPredicate);
		
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(InitContainers.BLUESTONE_INFUSER.get(), BluestoneInfuserScreen::new));
	}
}
