package fr.eno.usefulstones.containers.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.NonNullLazy;

public class SlotSingle extends Slot
{
	private NonNullLazy<Item> validItem;
	
	public SlotSingle(IInventory inventoryIn, int index, int xPosition, int yPosition, NonNullLazy<Item> validItem)
	{
		super(inventoryIn, index, xPosition, yPosition);
		this.validItem = validItem;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack.getItem() == validItem.get();
	}
}
