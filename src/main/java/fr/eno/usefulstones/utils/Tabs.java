package fr.eno.usefulstones.utils;

import fr.eno.usefulstones.init.InitItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.NonNullLazy;

public class Tabs
{
	public static final ItemGroup BLUESTONE_GROUP = create("bluestone_group", () -> InitItems.BLUESTONE.get());
	
	private static ItemGroup create(String name, NonNullLazy<Item> icon)
	{
		return new ItemGroup(name)
		{
			@Override
			public ItemStack createIcon()
			{
				return new ItemStack(icon.get());
			}
		};
	}
}
