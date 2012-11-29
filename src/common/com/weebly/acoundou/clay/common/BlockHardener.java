package com.weebly.acoundou.clay.common;
import net.minecraft.src.*;

import java.util.Random;
public class BlockHardener extends BlockContainer {
		
        private boolean isActive;
        protected BlockHardener(int par1, boolean par2)
        {
            super(par1, Material.rock);
            
            this.isActive = par2;
            this.blockIndexInTexture = 1;
            
            
        }
        private static boolean keepHardenerInventory = false;
		/*protected BlockHardener (int id) {
                super(id, Material.wood);
                setHardness(2.0F);
                setResistance(5.0F);
                setBlockName("blockHardener");
        }*/
        private Random hardenerRand = new Random();
        @Override
        public boolean onBlockActivated(World world, int x, int y, int z,
                        EntityPlayer player, int idk, float what, float these, float are) {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                
                if (tileEntity == null || player.isSneaking()) {
                        return false;
                }
                //opens gui, to be implemented later
                //player.openGui();
                player.openGui(Clay.instance, 0, world, x, y, z);
                return true;
        }
        public int idDropped(int par1, Random par2Random, int par3)
        {
            return Clay.blockHardener.blockID;
        }
        public void onBlockAdded(World par1World, int par2, int par3, int par4)
        {
            super.onBlockAdded(par1World, par2, par3, par4);
            
            this.setDefaultDirection(par1World, par2, par3, par4);
        }
        private void setDefaultDirection(World par1World, int par2, int par3, int par4)
        {
            if (!par1World.isRemote)
            {
                int var5 = par1World.getBlockId(par2, par3, par4 - 1);
                int var6 = par1World.getBlockId(par2, par3, par4 + 1);
                int var7 = par1World.getBlockId(par2 - 1, par3, par4);
                int var8 = par1World.getBlockId(par2 + 1, par3, par4);
                byte var9 = 3;

                if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
                {
                    var9 = 3;
                }

                if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
                {
                    var9 = 2;
                }

                if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
                {
                    var9 = 5;
                }

                if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
                {
                    var9 = 4;
                }

                par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
            }
        }

        
        /*private void dropItems(World world, int x, int y, int z){
                Random rand = new Random();

                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if (!(tileEntity instanceof IInventory)) {
                        return;
                }
                IInventory inventory = (IInventory) tileEntity;

                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack item = inventory.getStackInSlot(i);

                        if (item != null && item.stackSize > 0) {
                                float rx = rand.nextFloat() * 0.8F + 0.1F;
                                float ry = rand.nextFloat() * 0.8F + 0.1F;
                                float rz = rand.nextFloat() * 0.8F + 0.1F;

                                EntityItem entityItem = new EntityItem(world,
                                                x + rx, y + ry, z + rz,
                                                new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                                if (item.hasTagCompound()) {
                                        entityItem.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                                }

                                float factor = 0.05F;
                                entityItem.motionX = rand.nextGaussian() * factor;
                                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                                entityItem.motionZ = rand.nextGaussian() * factor;
                                world.spawnEntityInWorld(entityItem);
                                item.stackSize = 0;
                        }
                }
        }
        */
        @Override
        public int getBlockTexture(IBlockAccess world, int x, int y, int z, int par5)
        {
        	
        	
        		if (par5 == 0)
        		{
        			return this.isActive ? Clay.KilnBottom : Clay.KilnBottom;
        		}
        		if (par5 == 1)
        		{
        			return this.isActive ?  Clay.KilnTop : Clay.KilnTop;
        		}
        		if (par5 == 2)
        		{
        			return this.isActive ?  Clay.KilnSide : Clay.KilnSideOn;
        		}
        		if (par5 == 3)
        		{
        			return this.isActive ?  Clay.KilnFront : Clay.KilnFrontOn;
        		}
        		if (par5 == 4)
        		{
        			return this.isActive ?  Clay.KilnSide : Clay.KilnSideOn;
        		}
        		if (par5== 5)
        		{
        			return this.isActive ?  Clay.KilnSide : Clay.KilnSideOn;
        		}
				return par5;
        	
        	}
        	
        
    		
        
        public int getBlockTextureFromSide(int par1)
        {
            return par1 == 1 ? Clay.KilnBottom : (par1 == 0 ? Clay.KilnBottom : (par1 == 3 ? Clay.KilnSide : Clay.KilnFront));
        }
        @Override
        public TileEntity createNewTileEntity(World world) {
                return new TileEntityHardener();
        }
        public static void updateHardenerBlockState(boolean par0, World par1World, int par2, int par3, int par4)
        {
            int var5 = par1World.getBlockMetadata(par2, par3, par4);
            TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
            keepHardenerInventory = true;

            if (par0)
            {
                par1World.setBlockWithNotify(par2, par3, par4, Clay.blockHardenerID);
            }
            else
            {
                par1World.setBlockWithNotify(par2, par3, par4, Clay.blockHardenerIDOn);
            }

            keepHardenerInventory = false;
            par1World.setBlockMetadataWithNotify(par2, par3, par4, var5);

            if (var6 != null)
            {
                var6.validate();
                par1World.setBlockTileEntity(par2, par3, par4, var6);
            }
        }
        public String getTextureFile()
    	{
    		
    		return "/clay/terrain.png";
    	}
        public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
        {
            if (!keepHardenerInventory)
            {
                TileEntityHardener var7 = (TileEntityHardener)par1World.getBlockTileEntity(par2, par3, par4);

                if (var7 != null)
                {
                    for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                    {
                        ItemStack var9 = var7.getStackInSlot(var8);

                        if (var9 != null)
                        {
                            float var10 = this.hardenerRand.nextFloat() * 0.8F + 0.1F;
                            float var11 = this.hardenerRand.nextFloat() * 0.8F + 0.1F;
                            float var12 = this.hardenerRand.nextFloat() * 0.8F + 0.1F;

                            while (var9.stackSize > 0)
                            {
                                int var13 = this.hardenerRand.nextInt(21) + 10;

                                if (var13 > var9.stackSize)
                                {
                                    var13 = var9.stackSize;
                                }

                                var9.stackSize -= var13;
                                EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                                if (var9.hasTagCompound())
                                {
                                    var14.item.setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                                }

                                float var15 = 0.05F;
                                var14.motionX = (double)((float)this.hardenerRand.nextGaussian() * var15);
                                var14.motionY = (double)((float)this.hardenerRand.nextGaussian() * var15 + 0.2F);
                                var14.motionZ = (double)((float)this.hardenerRand.nextGaussian() * var15);
                                par1World.spawnEntityInWorld(var14);
                            }
                        }
                    }
                }
            }

            super.breakBlock(par1World, par2, par3, par4, par5, par6);
        }
}