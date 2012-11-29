package com.weebly.acoundou.clay.common;
import net.minecraft.src.*;

import net.minecraft.src.EntityPlayer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ContainerHardener extends Container
{
    private TileEntityHardener hardener;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerHardener(InventoryPlayer par1InventoryPlayer, TileEntityHardener par2TileEntityHardener)
    {
        this.hardener = par2TileEntityHardener;
        this.addSlotToContainer(new Slot(par2TileEntityHardener, 0, 56, 17));
        this.addSlotToContainer(new Slot(par2TileEntityHardener, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, par2TileEntityHardener, 2, 116, 35));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.hardener.hardenerCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.hardener.hardenerBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.hardener.currentItemBurnTime);
    }

    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void updateCraftingResults()
    {
        super.updateCraftingResults();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastCookTime != this.hardener.hardenerCookTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.hardener.hardenerCookTime);
            }

            if (this.lastBurnTime != this.hardener.hardenerBurnTime)
            {
                var2.sendProgressBarUpdate(this, 1, this.hardener.hardenerBurnTime);
            }

            if (this.lastItemBurnTime != this.hardener.currentItemBurnTime)
            {
                var2.sendProgressBarUpdate(this, 2, this.hardener.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.hardener.hardenerCookTime;
        this.lastBurnTime = this.hardener.hardenerBurnTime;
        this.lastItemBurnTime = this.hardener.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.hardener.hardenerCookTime = par2;
        }

        if (par1 == 1)
        {
            this.hardener.hardenerBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.hardener.currentItemBurnTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.hardener.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(var5, 3, 39, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (HardenerRecipes.hardening().getHardeningResult(var5) != null)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityHardener.isItemFuel(var5))
                {
                    if (!this.mergeItemStack(var5, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 3, 39, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
}
