package fr.eno.usefulstones.tileentity;

import fr.eno.usefulstones.containers.BluestoneInfuserContainer;
import fr.eno.usefulstones.init.InitItems;
import fr.eno.usefulstones.init.InitTileEntities;
import fr.eno.usefulstones.recipes.InfuserRecipes;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileBluestoneInfuser extends LockableLootTileEntity implements ITickableTileEntity
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private int timeLeft = 100;
	
	public TileBluestoneInfuser()
	{
		super(InitTileEntities.BLUESTONE_INFUSER.get());
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
		compound.putInt("TimeLeft", this.timeLeft);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound)
	{
		super.read(compound);	
		this.timeLeft = compound.getInt("TimeLeft");
		ItemStackHelper.loadAllItems(compound, this.inventory);
	}

	@Override
	public void tick()
	{
		if(canCraft())
		{
			timeLeft--;
			
			if(timeLeft <= 0)
			{
				this.decrementOrRemove(0);
				this.decrementOrRemove(1);				
				this.incrementOrSet(2, InfuserRecipes.getRecipeByIngredient(this.getStackInSlot(0).getItem()).getItemOutput());
				
				this.timeLeft = 100;
			}
		}
		else
		{
			this.timeLeft = 100;
		}
	}
	
	private void incrementOrSet(int index, Item item)
	{
		ItemStack stack = this.getStackInSlot(index);
		
		if(stack.isEmpty() || stack == null)
		{
			this.setInventorySlotContents(index, new ItemStack(item, 1));
			return;
		}
		
		if(stack.getCount() < 64)
		{
			stack.setCount(stack.getCount() + 1);
		}
	}
	
	private void decrementOrRemove(int index)
	{
		ItemStack stack = this.getStackInSlot(index);
		
		if(stack.getCount() == 1)
		{
			this.setInventorySlotContents(index, ItemStack.EMPTY);
			return;
		}
		else
		{
			stack.setCount(stack.getCount() - 1);
		}
	}
	
	public int getTimeLeft()
	{
		return timeLeft;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void setTimeLeft(int timeLeft)
	{
		this.timeLeft = timeLeft;
	}
	
	private boolean canCraft()
	{
		if(!this.getStackInSlot(2).isEmpty())
		{
			if(getStackInSlot(2).getCount() >= 64)
				return false;
		}
		
		if(!getStackInSlot(0).isEmpty() && !getStackInSlot(1).isEmpty())
		{
			if(InfuserRecipes.exists(getStackInSlot(0).getItem()) && getStackInSlot(1).getItem() == InitItems.BLUESTONE.get())
			{
				if(getStackInSlot(2).isEmpty() || InfuserRecipes.getRecipeByIngredient(getStackInSlot(0).getItem()).getItemOutput() == getStackInSlot(2).getItem())
				{
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public int getSizeInventory()
	{
		return this.inventory.size();
	}

	@Override
	protected NonNullList<ItemStack> getItems()
	{
		return this.inventory;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn)
	{
		this.inventory = itemsIn;
	}

	@Override
	protected ITextComponent getDefaultName()
	{
		return new TranslationTextComponent("tile.bluestone.bluestone_infuser");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return new BluestoneInfuserContainer(id, player, new PacketBuffer(Unpooled.buffer()).writeBlockPos(this.getPos()));
	}
}
