package com.weebly.acoundou.clay.common;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;



import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class TileEntityHardener extends TileEntity implements IInventory, ISidedInventory
{
    /**
     * The ItemStacks that hold the items currently being used in the hardener
     */
    private ItemStack[] hardenerItemStacks = new ItemStack[3];

    /** The number of ticks that the hardener will keep burning */
    public int hardenerBurnTime = 0;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the hardener burning for
     */
    public int currentItemBurnTime = 0;

    /** The number of ticks that the current item has been cooking for */
    public int hardenerCookTime = 0;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.hardenerItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.hardenerItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.hardenerItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.hardenerItemStacks[par1].stackSize <= par2)
            {
                var3 = this.hardenerItemStacks[par1];
                this.hardenerItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.hardenerItemStacks[par1].splitStack(par2);

                if (this.hardenerItemStacks[par1].stackSize == 0)
                {
                    this.hardenerItemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.hardenerItemStacks[par1] != null)
        {
            ItemStack var2 = this.hardenerItemStacks[par1];
            this.hardenerItemStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.hardenerItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.hardener";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.hardenerItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.hardenerItemStacks.length)
            {
                this.hardenerItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.hardenerBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.hardenerCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.hardenerItemStacks[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.hardenerBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.hardenerCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.hardenerItemStacks.length; ++var3)
        {
            if (this.hardenerItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.hardenerItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return this.hardenerCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
        	
            this.currentItemBurnTime = 200;
        }

        return this.hardenerBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the hardener is currently burning
     */
    public boolean isBurning()
    {
    	
        return this.hardenerBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.hardenerBurnTime > 0;
        boolean var2 = false;

        if (this.hardenerBurnTime > 0)
        {
            --this.hardenerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.hardenerBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.hardenerBurnTime = getItemBurnTime(this.hardenerItemStacks[1]);

                if (this.hardenerBurnTime > 0)
                {
                    var2 = true;

                    if (this.hardenerItemStacks[1] != null)
                    {
                        --this.hardenerItemStacks[1].stackSize;

                        if (this.hardenerItemStacks[1].stackSize == 0)
                        {
                            this.hardenerItemStacks[1] = this.hardenerItemStacks[1].getItem().getContainerItemStack(hardenerItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.hardenerCookTime;

                if (this.hardenerCookTime == 200)
                {
                    this.hardenerCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.hardenerCookTime = 0;
            }

            if (var1 != this.hardenerBurnTime > 0)
            {
                var2 = true;
                BlockHardener.updateHardenerBlockState(this.hardenerBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    /**
     * Returns true if the hardener can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.hardenerItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = HardenerRecipes.hardening().getHardeningResult(this.hardenerItemStacks[0]);
            if (var1 == null) return false;
            if (this.hardenerItemStacks[2] == null) return true;
            if (!this.hardenerItemStacks[2].isItemEqual(var1)) return false;
            int result = hardenerItemStacks[2].stackSize + var1.stackSize;
            return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
        }
    }

    /**
     * Turn one item from the hardener source stack into the appropriate smelted item in the hardener result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = HardenerRecipes.hardening().getHardeningResult(this.hardenerItemStacks[0]);

            if (this.hardenerItemStacks[2] == null)
            {
                this.hardenerItemStacks[2] = var1.copy();
            }
            else if (this.hardenerItemStacks[2].isItemEqual(var1))
            {
                hardenerItemStacks[2].stackSize += var1.stackSize;
            }

            --this.hardenerItemStacks[0].stackSize;

            if (this.hardenerItemStacks[0].stackSize <= 0)
            {
                this.hardenerItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the hardener burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int var1 = par0ItemStack.getItem().shiftedIndex;
            Item var2 = par0ItemStack.getItem();

            
            
            if (var1 == Item.coal.shiftedIndex) return 800;
            if (var1 == Item.bucketLava.shiftedIndex) return 2400;
            if (var1 == Item.blazeRod.shiftedIndex) return 1600;
            if (var1 == Clay.itemHeatElement.shiftedIndex) return 40000;
            return GameRegistry.getFuelValue(par0ItemStack);
            
        }
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    @Override
    public int getStartInventorySide(ForgeDirection side)
    {
        if (side == ForgeDirection.DOWN) return 1;
        if (side == ForgeDirection.UP) return 0; 
        return 2;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection side)
    {
        return 1;
    }
}

