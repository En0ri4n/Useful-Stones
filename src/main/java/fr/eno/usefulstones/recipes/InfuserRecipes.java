package fr.eno.usefulstones.recipes;

import java.util.ArrayList;
import java.util.List;

import fr.eno.usefulstones.init.InitItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class InfuserRecipes
{
	private static final List<InfuserRecipe> RECIPES = new ArrayList<InfuserRecipe>();
	public static final InfuserRecipe EMPTY_RECIPE = new InfuserRecipe(() -> Items.AIR, () -> Items.AIR);
	
	public static void registerRecipes()
	{
		addRecipe(new InfuserRecipe(() -> Items.ACACIA_DOOR, () -> InitItems.BLUESTONE_ACACIA_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.ACACIA_BUTTON, () -> InitItems.BLUESTONE_ACACIA_BUTTON.get()));
		addRecipe(new InfuserRecipe(() -> Items.ACACIA_PRESSURE_PLATE, () -> InitItems.BLUESTONE_ACACIA_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.BIRCH_DOOR, () -> InitItems.BLUESTONE_BIRCH_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.BIRCH_BUTTON, () -> InitItems.BLUESTONE_BIRCH_BUTTON.get()));
		addRecipe(new InfuserRecipe(() -> Items.BIRCH_PRESSURE_PLATE, () -> InitItems.BLUESTONE_BIRCH_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.OAK_DOOR, () -> InitItems.BLUESTONE_OAK_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.OAK_BUTTON, () -> InitItems.BLUESTONE_OAK_BUTTON.get()));
		addRecipe(new InfuserRecipe(() -> Items.OAK_PRESSURE_PLATE, () -> InitItems.BLUESTONE_OAK_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.DARK_OAK_DOOR, () -> InitItems.BLUESTONE_DARK_OAK_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.DARK_OAK_BUTTON, () -> InitItems.BLUESTONE_DARK_OAK_BUTTON.get()));
		addRecipe(new InfuserRecipe(() -> Items.DARK_OAK_PRESSURE_PLATE, () -> InitItems.BLUESTONE_DARK_OAK_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.SPRUCE_DOOR, () -> InitItems.BLUESTONE_SPRUCE_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.SPRUCE_BUTTON, () -> InitItems.BLUESTONE_SPRUCE_BUTTON.get()));
		addRecipe(new InfuserRecipe(() -> Items.SPRUCE_PRESSURE_PLATE, () -> InitItems.BLUESTONE_SPRUCE_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.JUNGLE_DOOR, () -> InitItems.BLUESTONE_JUNGLE_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.JUNGLE_BUTTON, () -> InitItems.BLUESTONE_JUNGLE_BUTTON.get()));
		addRecipe(new InfuserRecipe(() -> Items.JUNGLE_PRESSURE_PLATE, () -> InitItems.BLUESTONE_JUNGLE_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.IRON_DOOR, () -> InitItems.BLUESTONE_IRON_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.STONE_BUTTON, () -> InitItems.BLUESTONE_STONE_BUTTON.get()));
		addRecipe(new InfuserRecipe(() -> Items.LEVER, () -> InitItems.BLUESTONE_LEVER.get()));addRecipe(new InfuserRecipe(() -> Items.ACACIA_DOOR, () -> InitItems.BLUESTONE_ACACIA_DOOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.LIGHT_WEIGHTED_PRESSURE_PLATE, () -> InitItems.BLUESTONE_LIGHT_WEIGHTED_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.HEAVY_WEIGHTED_PRESSURE_PLATE, () -> InitItems.BLUESTONE_HEAVY_WEIGHTED_PRESSURE_PLATE.get()));
		addRecipe(new InfuserRecipe(() -> Items.REPEATER, () -> InitItems.BLUESTONE_REPEATER.get()));
		addRecipe(new InfuserRecipe(() -> Items.TRIPWIRE_HOOK, () -> InitItems.BLUESTONE_TRIPWIRE_HOOK.get()));
		addRecipe(new InfuserRecipe(() -> Items.STRING, () -> InitItems.BLUESTONE_STRING.get()));
		addRecipe(new InfuserRecipe(() -> Items.COMPARATOR, () -> InitItems.BLUESTONE_COMPARATOR.get()));
		addRecipe(new InfuserRecipe(() -> Items.TORCH, () -> InitItems.BLUESTONE_TORCH.get()));
	}
	
	public static boolean exists(Item ingredient)
	{
		for(InfuserRecipe recipe : RECIPES)
		{
			if(recipe.getIngredient() == ingredient)
				return true;
		}
		
		return false;
	}
	
	public static InfuserRecipe getRecipeByIngredient(Item ingredient)
	{
		for(InfuserRecipe recipe : RECIPES)
		{
			if(recipe.getIngredient() == ingredient)
				return recipe;
		}
		
		return EMPTY_RECIPE;
	}
	
	public static InfuserRecipe getRecipeByOutput(Item output)
	{
		for(InfuserRecipe recipe : RECIPES)
		{
			if(recipe.getItemOutput() == output)
				return recipe;
		}
		
		return EMPTY_RECIPE;
	}
	
	public static List<InfuserRecipe> getRecipes()
	{
		return RECIPES;
	}
	
	private static void addRecipe(InfuserRecipe recipe)
	{
		RECIPES.add(recipe);
	}
}
