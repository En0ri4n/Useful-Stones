package fr.eno.usefulstones.recipes;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.NonNullLazy;

public class InfuserRecipe
{
	private NonNullLazy<Item> ingredient;
	private NonNullLazy<Item> output;
	
	public InfuserRecipe(NonNullLazy<Item> firstIngredient, NonNullLazy<Item> output)
	{
		this.ingredient = firstIngredient;
		this.output = output;
	}

	public Item getIngredient()
	{
		return ingredient.get();
	}

	public Item getItemOutput()
	{
		return output.get();
	}
}
