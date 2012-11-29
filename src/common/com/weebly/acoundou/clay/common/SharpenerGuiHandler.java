package com.weebly.acoundou.clay.common;
import net.minecraft.src.*;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class SharpenerGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		return new ContainerSharpener(player.inventory, (TileEntitySharpener)tileEntity);
		
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		return new GuiSharpener(player.inventory, (TileEntitySharpener)tileEntity);
	}

}
