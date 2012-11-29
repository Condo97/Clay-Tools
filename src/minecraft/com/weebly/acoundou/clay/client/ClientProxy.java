package com.weebly.acoundou.clay.client;

import net.minecraftforge.client.MinecraftForgeClient;

import com.weebly.acoundou.clay.common.CommonProxy;

public class ClientProxy extends CommonProxy {
	public void registerRenderInformation() {
	
		MinecraftForgeClient.preloadTexture("/clay/items.png");
		MinecraftForgeClient.preloadTexture("/clay/terrain.png");
		
		
		
	}
}
