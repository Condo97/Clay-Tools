package com.weebly.acoundou.clay.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.src.*;

public class SharpenerRecipes
{
    private static final SharpenerRecipes hardeningBase = new SharpenerRecipes();

    /** The list of hardening results. */
    private Map hardeningList = new HashMap();
    private Map metaHardeningList = new HashMap();

    /**
     * Used to call methods addHardening and getHardeningResult.
     */
    public static final SharpenerRecipes hardening()
    {
        return hardeningBase;
    }

    private SharpenerRecipes()
    {
        this.addHardening(Clay.itemBrickMold.shiftedIndex, new ItemStack(Item.brick));
        this.addHardening(Clay.itemPickHeadMold.shiftedIndex, new ItemStack(Clay.itemPickHeadH));
        this.addHardening(Clay.itemAxeHeadMold.shiftedIndex, new ItemStack(Clay.itemAxeHeadH));
        this.addHardening(Item.clay.shiftedIndex, new ItemStack(Clay.itemFiredClay));
        this.addHardening(Block.sand.blockID, new ItemStack(Block.glass));
        this.addHardening(Block.oreIron.blockID, new ItemStack(Item.ingotIron));
        this.addHardening(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
        this.addHardening(Block.gravel.blockID, new ItemStack(Item.flint));
        this.addHardening(Clay.itemShovelHeadMold.shiftedIndex, new ItemStack(Clay.itemShovelHeadH));
        this.addHardening(Clay.itemSwordHeadMold.shiftedIndex, new ItemStack(Clay.itemSwordHeadH));
        this.addHardening(Clay.itemHoeHeadMold.shiftedIndex, new ItemStack(Clay.itemHoeHeadH));
        
    }

    /**
     * Adds a hardening recipe.
     */
    public void addHardening(int par1, ItemStack par2ItemStack)
    {
        this.hardeningList.put(Integer.valueOf(par1), par2ItemStack);
    }

    /**
     * Returns the hardening result of an item.
     * Deprecated in favor of a metadata sensitive version
     */
    @Deprecated
    public ItemStack getHardeningResult(int par1)
    {
        return (ItemStack)this.hardeningList.get(Integer.valueOf(par1));
    }

    public Map getHardeningList()
    {
        return this.hardeningList;
    }
    
    /**
     * Add a metadata-sensitive kilm recipe
     * @param itemID The Item ID
     * @param metadata The Item Metadata
     * @param itemstack The ItemStack for the result
     */
    public void addHardening(int itemID, int metadata, ItemStack itemstack)
    {
        metaHardeningList.put(Arrays.asList(itemID, metadata), itemstack);
    }
    
    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getHardeningResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaHardeningList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)hardeningList.get(Integer.valueOf(item.itemID));
    }

	public float func_77601_c(int itemID) {
		// TODO Auto-generated method stub
		return 0;
	}
}
