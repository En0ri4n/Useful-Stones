package fr.eno.bluestone.item;

import fr.eno.bluestone.Tabs;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ItemBlock extends BlockItem
{

	public ItemBlock(Block block)
	{
		super(block, new Item.Properties().setNoRepair().group(Tabs.BLUESTONE_GROUP));
		// TODO Auto-generated constructor stub
	}

}
