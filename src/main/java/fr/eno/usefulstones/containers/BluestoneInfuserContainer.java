package fr.eno.usefulstones.containers;

import fr.eno.usefulstones.containers.slot.SlotOutput;
import fr.eno.usefulstones.containers.slot.SlotSingle;
import fr.eno.usefulstones.init.InitContainers;
import fr.eno.usefulstones.init.InitItems;
import fr.eno.usefulstones.packets.NetworkRegistryHandler;
import fr.eno.usefulstones.packets.SyncTileEntityDataPacket;
import fr.eno.usefulstones.tileentity.TileBluestoneInfuser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public class BluestoneInfuserContainer extends Container
{
	private TileBluestoneInfuser tile;
	private PlayerInventory playerInv;

	private int timeLeft;
	private int timeLeftFromLastCall;

	public BluestoneInfuserContainer(int windowId, PlayerInventory playerInv, PacketBuffer data)
	{
		super(InitContainers.BLUESTONE_INFUSER.get(), windowId);

		tile = (TileBluestoneInfuser) playerInv.player.world.getTileEntity(data.readBlockPos());
		this.playerInv = playerInv;

		this.addSlot(new Slot(tile, 0, 15, 35));
		this.addSlot(new SlotSingle(tile, 1, 66, 35, () -> InitItems.BLUESTONE.get()));
		this.addSlot(new SlotOutput(tile, 2, 138, 35));

		int index = 0;

		for (int slot = 0; slot < 9; slot++)
		{
			this.addSlot(new Slot(playerInv, index++, 8 + slot * 18, 142));
		}

		for (int h = 0; h < 3; h++)
		{
			for (int w = 0; w < 9; w++)
			{
				this.addSlot(new Slot(playerInv, index++, 8 + 18 * w, 84 + 18 * h));
			}
		}
	}

	@Override
	public void detectAndSendChanges()
	{
		timeLeft = this.tile.getTimeLeft();

		if (timeLeft != timeLeftFromLastCall)
		{
			NetworkRegistryHandler.network.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerInv.player), new SyncTileEntityDataPacket(windowId, timeLeft));
			timeLeftFromLastCall = timeLeft;
		}
		
		super.detectAndSendChanges();
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(index < 3)
			{
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if(!this.mergeItemStack(itemstack1, 0, 3, false))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}
		
		return itemstack;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return this.tile.isUsableByPlayer(playerIn);
	}

	@OnlyIn(Dist.CLIENT)
	public int getTimeLeft()
	{
		return timeLeft;
	}

	@OnlyIn(Dist.CLIENT)
	public void setTimeLeft(int timeLeft)
	{
		this.timeLeft = timeLeft;
	}
}
