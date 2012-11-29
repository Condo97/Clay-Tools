package com.weebly.acoundou.clay.client;
import net.minecraft.src.*;

import org.lwjgl.opengl.GL11;

import com.weebly.acoundou.clay.common.ContainerSharpener;
import com.weebly.acoundou.clay.common.TileEntitySharpener;

public class GuiSharpener extends GuiContainer {
	
		private TileEntitySharpener sharpenerInventory;
		public GuiSharpener(InventoryPlayer par1InventoryPlayer, TileEntitySharpener par2TileEntitySharpener)
	    {
	        super(new ContainerSharpener(par1InventoryPlayer, par2TileEntitySharpener));
	        this.sharpenerInventory = par2TileEntitySharpener;
	    }
        /*public GuiSharpener (InventoryPlayer inventoryPlayer,
                        TileEntitySharpener tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerSharpener(inventoryPlayer, tileEntity));
        }
*/		
        @Override
        protected void drawGuiContainerForegroundLayer(int par1, int par2)
        {
            this.fontRenderer.drawString(StatCollector.translateToLocal("Kiln"), 60, 6, 4210752);
            this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        }

        /**
         * Draw the background layer for the GuiContainer (everything behind the items)
         */
        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
        {
        	int var4 = mc.renderEngine.getTexture("/clay/KilnGUI.png");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture(var4);
            int var5 = (this.width - this.xSize) / 2;
            int var6 = (this.height - this.ySize) / 2;
            this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
            int var7;

            if (sharpenerInventory.isBurning())
            {
                var7 = sharpenerInventory.getBurnTimeRemainingScaled(12);
                this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
            }

            var7 = sharpenerInventory.getCookProgressScaled(24);
            this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
        }

}