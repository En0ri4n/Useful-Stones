package fr.eno.usefulstones.init;

import fr.eno.usefulstones.References;
import fr.eno.usefulstones.tileentity.TileBluestoneInfuser;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitTileEntities
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, References.MOD_ID);
	
	public static final RegistryObject<TileEntityType<TileBluestoneInfuser>> BLUESTONE_INFUSER = TILE_ENTITIES.register("bluestone_infuser", () -> TileEntityType.Builder.create(TileBluestoneInfuser::new, InitBlocks.BLUESTONE_INFUSER.get()).build(null));
}
