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
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(References.MOD_ID)
public class BlueStone
{
	private static final Logger LOGGER = LogManager.getLogger(References.MOD_NAME);

	public BlueStone()
	{
		LOGGER.debug("BlueStone Setup Started...");
		IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();
		
		mod.addListener(this::commonSetup);
		mod.addListener(this::clientSetup);
		
		InitBlocks.BLOCKS.register(mod);
		InitItems.ITEMS.register(mod);
		
		LOGGER.debug("BlueStone Setup Finished !");
	}

	private void commonSetup(final FMLCommonSetupEvent event)
	{
		Predicate<RenderType> cutoutPredicate = renderType -> renderType == RenderType.getCutout();
		
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_COMPARATOR.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_TORCH.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_REPEATER.get(), cutoutPredicate);
		RenderTypeLookup.setRenderLayer(InitBlocks.BLUESTONE_WIRE.get(), cutoutPredicate);
	}

	private void clientSetup(final FMLClientSetupEvent event)
	{
		BlockColors colors = Minecraft.getInstance().getBlockColors();
		
		colors.register(new BluestoneWireBlock(), InitBlocks.BLUESTONE_WIRE.get());
	}
}
