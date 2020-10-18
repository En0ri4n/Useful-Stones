package fr.eno.usefulstones.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.eno.usefulstones.References;
import fr.eno.usefulstones.containers.BluestoneInfuserContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BluestoneInfuserScreen extends ContainerScreen<BluestoneInfuserContainer>
{
	private static final ResourceLocation INFUSER_TEXTURE = new ResourceLocation(References.MOD_ID, "textures/gui/container/bluestone_infuser.png");
	
	public BluestoneInfuserScreen(BluestoneInfuserContainer screenContainer, PlayerInventory inv, ITextComponent titleIn)
	{
		super(screenContainer, inv, titleIn);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.minecraft.getTextureManager().bindTexture(INFUSER_TEXTURE);
	    int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
	    this.blit(i, j, 0, 0, this.xSize, 27 * 18 + 17);

	    double width = 29D - ((double) this.container.getTimeLeft()) * 29D / 100D;
	    this.minecraft.getTextureManager().bindTexture(INFUSER_TEXTURE);
		this.blit(i + 93, j + 33, 176, 0, (int) width, 19);
	}
}
