package com.minecraft2.client.gui;

import com.minecraft2.furnaceBlockContainer;
import com.minecraft2.minecraft2mod;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import com.minecraft2.furnaceBlockTile;


public class furnaceBlockScreen extends ContainerScreen<furnaceBlockContainer> {

    private ResourceLocation GUI = new ResourceLocation(minecraft2mod.MOD_ID, "textures/gui/furnaceblock_gui.png");

    furnaceBlockTile tile;

    public furnaceBlockScreen(furnaceBlockContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        tile = container.getTileEntity();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
        this.blit(relX + 87, relY + 26, 181, 0, tile.getEnergy(), 21);
        this.blit(relX + 47, relY + 29 + 13 - (int)(((13.0/1280.0)*(double)tile.getCount())), 210, 13 - (int)(((13.0/1280.0)*tile.getCount())), 14, 14);
        //minecraft2mod.logger.info((int)(((28.0/160.0)*(double)tile.getEnergy())));
       // minecraft2mod.logger.info(tile.getEnergy());
       // minecraft2mod.logger.info(tile.cooking);
        //this.drawRightAlignedString(minecraft.fontRenderer, "Alloy Furnace", relX + 167, relY + 3, 16730371);
        minecraft.fontRenderer.drawString("Alloy Furnace", relX + 93, relY +3, 16730371);
    }


}
