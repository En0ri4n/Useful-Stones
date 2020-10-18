package fr.eno.usefulstones;

import net.minecraft.util.ResourceLocation;

public class References
{
	public static final String MOD_ID = "usefulstones";
	public static final String MOD_NAME = "Useful Stones";
	public static final String VERSION = "1.0.0";
	
	public static ResourceLocation getLoc(String path)
	{
		return new ResourceLocation(MOD_ID, path);
	}
}