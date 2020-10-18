package fr.eno.usefulstones.init;

import fr.eno.usefulstones.References;
import fr.eno.usefulstones.containers.BluestoneInfuserContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitContainers
{
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, References.MOD_ID);
	
	public static final RegistryObject<ContainerType<BluestoneInfuserContainer>> BLUESTONE_INFUSER = CONTAINERS.register("bluestone_infuser", () -> IForgeContainerType.create(BluestoneInfuserContainer::new));

}
