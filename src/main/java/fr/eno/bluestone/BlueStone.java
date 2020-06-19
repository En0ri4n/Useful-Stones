package fr.eno.bluestone;

import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.eno.bluestone.block.BluestoneWireBlock;
import fr.eno.bluestone.init.InitBlocks;
import fr.eno.bluestone.init.InitItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
	}

	private void commonSetup(final FMLCommonSetupEvent event)
	{
		Minecraft.getInstance().getBlockColors().register(new BluestoneWireBlock(), InitBlocks.BLUESTONE_WIRE.get());
	}

	private void clientSetup(final FMLClientSetupEvent event)
	{
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
	}
}
