package com.weebly.acoundou.clay.common;
import com.weebly.acoundou.clay.client.GuiHardener;

import net.minecraft.src.*;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonGuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		return new ContainerHardener(player.inventory, (TileEntityHardener)tileEntity);
		
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		return new GuiHardener(player.inventory, (TileEntityHardener)tileEntity);
		
	}

}
