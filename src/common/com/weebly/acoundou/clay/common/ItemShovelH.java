package com.weebly.acoundou.clay.common;
import net.minecraft.src.*;

public class ItemShovelH extends ItemClayTool
{
    /** an array of the blocks this spade is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};

    public ItemShovelH(int par1, EnumClayToolMaterial par2EnumClayToolMaterial)
    {
        super(par1, 1, par2EnumClayToolMaterial, blocksEffectiveAgainst);
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }


    public String getTextureFile() {
		return "/clay/items.png";
	}
}
