package com.weebly.acoundou.clay.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;

@Mod(modid = "Clay", name = "Clay", version = Clay.versionNumber)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class Clay
{
	//Proxy stuffs
	@SidedProxy(clientSide = "com.weebly.acoundou.clay.client.ClientProxy", serverSide = "com.weebly.acoundou.clay.common.CommonProxy")
	public static CommonProxy proxy;

	
	//public static final Material BlockTNC = null;
	public static final String versionNumber = "1.0.0";
	//public static Item itemClayPickaxe = new ItemClayPickaxe(ModLoader.getUniqueEntityId(), EnumClayToolMaterial.CLAY).setItemName("ClayPickaxe").setsetIconIndex(setItemTexture("ClayPickaxe"));
	//public static Item itemClayShovel = new ItemClayShovel(ModLoader.getUniqueEntityId(), EnumClayToolMaterial.CLAY).setItemName("ClayShovel").setsetIconIndex(setItemTexture("ClayShovel"));
	//public static Item itemClayAxe = new ItemClayPickaxe(ModLoader.getUniqueEntityId(), EnumClayToolMaterial.CLAY).setItemName("ClayAxe").setsetIconIndex(setItemTexture("ClayShovel"));
	//public static Item itemClayHoe = new ItemClayHoe(ModLoader.getUniqueEntityId(), EnumClayToolMaterial.CLAY).setItemName("ClayHoe").setsetIconIndex(setItemTexture("ClayHoe"));
	//public static Item itemClaySword = new ItemClaySword(ModLoader.getUniqueEntityId(), EnumClayToolMaterial.KCLAY).setItemName("ClaySword").setsetIconIndex(setItemTexture("ClaySword"));
	/*public static Block blockHardener = new BlockHardener(200, false).setBlockName("Hardener").setCreativeTab(CreativeTabs.tabDeco);
	public static Block blockHardenerOn = new BlockHardener(201, true).setBlockName("HardenerOn");*/
	public static Item itemHeatElement = new ItemHeatElement(9375).setItemName("itemHeatElement").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemFiredClay = new ItemFiredClay(9376).setItemName("itemFiredClay").setCreativeTab(CreativeTabs.tabAllSearch);
	//public static Block blockTNC = new BlockTNC(202, 8).setBlockName("TNC");
	
	public static Item itemHolster = new ItemBrickMold(9377).setItemName("itemHolster").setCreativeTab(CreativeTabs.tabAllSearch);
	
	//9375-9450
	public static Item itemBrickMold = new ItemBrickMold(9378).setItemName("itemBrickMold").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemSwordHeadMold = new ItemShovelHead(9379).setItemName("itemSwordHeadMold").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemShovelHeadMold = new ItemShovelHead(9380).setItemName("itemShovelHeadMold").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemPickHeadMold = new ItemPickHeadMold(9381).setItemName("itemPickHeadMold").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemAxeHeadMold = new ItemAxeHeadMold(9382).setItemName("itemAxeHeadMold").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemHoeHeadMold = new ItemShovelHead(9383).setItemName("itemHoeHeadMold").setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static Item itemSwordHeadH = new ItemShovelHead(9384).setItemName("itemSwordHeadH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemShovelHeadH = new ItemShovelHead(9385).setItemName("itemShovelHeadH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemPickHeadH = new ItemPickHeadH(9386).setItemName("itemPickHeadH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemAxeHeadH = new ItemAxeHeadH(9387).setItemName("itemAxeHeadH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemHoeHeadH = new ItemShovelHead(9388).setItemName("itemHoeHeadH").setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static Item itemSwordH = new ItemSwordH(9389, EnumClayToolMaterial.KCLAYH).setItemName("itemSwordH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemShovelH = new ItemShovelH(9390, EnumClayToolMaterial.CLAYH).setItemName("itemShovelH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemPickH = new ItemClayPickaxe(9391, EnumClayToolMaterial.CLAYH).setItemName("itemPickH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemAxeH = new ItemClayAxe(9392, EnumClayToolMaterial.CLAYH).setItemName("itemAxeH").setCreativeTab(CreativeTabs.tabAllSearch);
	public static Item itemHoeH = new ItemHoeH(9393, EnumClayToolMaterial.CLAYH).setItemName("itemHoeH").setCreativeTab(CreativeTabs.tabAllSearch);
	
	
	
	
	//Blocks
	public static Block blockHardener.setCreativeTab(CreativeTabs.tabDecorations);;
	public static Block blockHardenerOn;

	public static Block blockSharpener;
	public static Block blockSharpenerOn;
	
	//IDs
	public static int blockHardenerID = 201;
	public static int blockHardenerIDOn = 202;
	
	public static int blockSharpenerID = 203;
	public static int blockSharpenerIDOn = 204;
	
	//TEXTUREN
	//public static int GUITexture = 
	public static int KilnBottom = 0;
	public static int KilnTop = 0;
	public static int KilnSide = 1;
	public static int KilnFront =4;
	public static int KilnFrontOn =3;
	public static int KilnSideOn =2;
	
	public static int SharpenerBottom = 5;
	public static int SharpenerSide = 6;
	public static int SharpenerFront = 7;
	public static int SharpenerFrontOn = 8;
	//FIX FRONT TEXTURE OF KILN AND SET KILNFRONT BACK TO 4 AND KILNFRONTON TO 3
	public static final int hardenerGUIid = 0;
	
	
	
	
	
	public static int TNCTop;
	public static int TNCBottom;
	public static int TNCSide;
	
	@Instance("Clay")
	public static Clay instance;
	
	@PreInit
	public void load(FMLPreInitializationEvent event)
	
	{
		proxy.registerRenderInformation(); 
		
		//Blocks
		blockHardener = new BlockHardener(blockHardenerID, false).setBlockName("Hardener").setCreativeTab(CreativeTabs.tabAllSearch);
		blockHardener = new BlockHardener(blockHardenerIDOn, true).setBlockName("HardenerOn");
		
		blockSharpener = new BlockSharpener(blockSharpenerID, false).setBlockName("Sharpener").setCreativeTab(CreativeTabs.tabAllSearch);
		blockSharpener = new BlockSharpener(blockSharpenerIDOn, true).setBlockName("SharpenerOn");
		
		LanguageRegistry.addName(blockSharpener, "Sharpener");
		GameRegistry.registerBlock(blockSharpener);
		
		
		
		
		
		/**GameRegistry.addRecipe(new ItemStack(blockSharpener,1), new Object[] {
			"*",'*', Block.cobblestone
		}); **/
		//BlockSharpener sharpenerGUIuse;
		
		
		NetworkRegistry.instance().registerGuiHandler(this, new CommonGuiHandler());
		
		
		//NetworkRegistry.instance().registerGuiHandler(this, new SharpenerGuiHandler());
		
		
		//NetworkRegistry.instance().registerGuiHandler(this, instance);
		
		
		
		
		
		
		
		
		LanguageRegistry.addName(itemHolster,"Grip");
		GameRegistry.addRecipe(new ItemStack(itemHolster,2), new Object[] {
			"^&^"," ^ ",'^',Item.stick,'&',Item.leather
		});
		GameRegistry.addRecipe(new ItemStack(itemHolster,2), new Object[] {
			"^&^"," ^ ",'^',Item.stick,'&',itemFiredClay
		});
		
		
		LanguageRegistry.addName(blockHardener, "Kiln");
		GameRegistry.registerBlock(blockHardener);
		GameRegistry.addRecipe(new ItemStack(blockHardener,1), new Object[] {
			"***","*&*","***",'*', Item.brick,'&',Item.clay
		});
		
		
		LanguageRegistry.addName(itemFiredClay,"Fired Clay Chunk");
		GameRegistry.addRecipe(new ItemStack(Block.tnt,1), new Object[] {
			"@$@","$@$","@$@", '$', itemFiredClay,'@', Item.gunpowder
		});
		
		
		LanguageRegistry.addName(itemHeatElement, "Heating Element");
		GameRegistry.addRecipe(new ItemStack(itemHeatElement,1), new Object[] {
			"&*&","*^*", "&*&", '*', Block.planks, '&',Item.coal,'^',Item.bucketLava
		});
		GameRegistry.addRecipe(new ItemStack(itemHeatElement, 1), new Object[] {
			"&*&","*^*", "&*&", '*', Block.planks, '&',Item.coal,'^',Item.blazeRod
		});
		
		LanguageRegistry.addName(itemBrickMold, "Brick Mold");
		GameRegistry.addRecipe(new ItemStack(itemBrickMold, 6), new Object[] {
			"***","***",'*',Item.clay
		});
		
		LanguageRegistry.addName(itemPickHeadMold, "Pickaxe Head Mold");
		GameRegistry.addRecipe(new ItemStack(itemPickHeadMold, 1), new Object[] {
			"***","   ","   ",'*', Item.clay
		});
		
		LanguageRegistry.addName(itemPickHeadH, "Fired Pickaxe Head");
		
		LanguageRegistry.addName(itemPickH, "Fired Clay Pickaxe");
		GameRegistry.addRecipe(new ItemStack(itemPickH, 1), new Object[] {
			"*","^",'*', itemPickHeadH,'^',Item.stick
		});
		
		LanguageRegistry.addName(itemAxeHeadMold, "Axe Head Mold");
		GameRegistry.addRecipe(new ItemStack(itemAxeHeadMold, 1), new Object[] {
			"**"," *","  ",'*',Item.clay
		});
		
		LanguageRegistry.addName(itemAxeHeadH, "Fired Axe Head");
		
		LanguageRegistry.addName(itemAxeH, "Fired Clay Axe");
		GameRegistry.addRecipe(new ItemStack(itemAxeH, 1), new Object[] {
			"*","^",'*',itemAxeHeadH,'^',Item.stick
		});
		
		LanguageRegistry.addName(itemShovelHeadMold, "Shovel Head Mold");
		GameRegistry.addRecipe(new ItemStack(itemShovelHeadMold, 1), new Object[] {
				"*"," "," ",'*',Item.clay
		});
		
		LanguageRegistry.addName(itemShovelHeadH,"Fired Shovel Head");
		
		LanguageRegistry.addName(itemShovelH, "Fired Clay Shovel");
		GameRegistry.addRecipe(new ItemStack(itemShovelH,1), new Object[] {
			"*","^",'*',itemShovelHeadH,'^',Item.stick
		});
		
		LanguageRegistry.addName(itemSwordHeadMold,"Sword Head Mold");
		LanguageRegistry.addName(itemSwordHeadH,"Fired Sword Head");
		LanguageRegistry.addName(itemSwordH,"Fired Clay Sword");
		GameRegistry.addRecipe(new ItemStack(itemSwordHeadMold, 1), new Object[] {
			"*","*"," ",'*',Item.clay
		});
		GameRegistry.addRecipe(new ItemStack(itemSwordH,1),new Object[] {
			"*","^",'*',itemSwordHeadH,'^',itemHolster
		});
		
		LanguageRegistry.addName(itemHoeHeadMold,"Hoe Head Mold");
		LanguageRegistry.addName(itemHoeHeadH,"Fired Hoe Head");
		LanguageRegistry.addName(itemHoeH, "Fired Clay Hoe");
		GameRegistry.addRecipe(new ItemStack(itemHoeHeadMold, 1), new Object[] {
			"**",'*', Item.clay
		});
		GameRegistry.addRecipe(new ItemStack(itemHoeH,1), new Object[] {
			"*","^",'*',itemHoeHeadH,'^',Item.stick
		});
		
		GameRegistry.registerTileEntity(TileEntityHardener.class, "Hardener");
		
		//Overrides
		/*blockHardener.blockIndexInTexture = 1;
		blockHardenerOn.blockIndexInTexture = 2;
		
		HardenerSide = 1;
		HardenerTop = 0;
		HardenerOn2 = 3;
		HardenerOff = 4;*/
		
		itemHolster.setIconIndex(9);
		
		itemHeatElement.setIconIndex(13);
		itemBrickMold.setIconIndex(15);
		itemPickHeadMold.setIconIndex(6);
		itemPickHeadH.setIconIndex (7);
		itemPickH.setIconIndex (8);
		itemAxeHeadMold.setIconIndex (16);
		itemAxeHeadH.setIconIndex (17);
		itemAxeH.setIconIndex (18);
		itemFiredClay.setIconIndex (14);
		itemShovelHeadMold.setIconIndex (3);
		itemShovelHeadH.setIconIndex (4);
		itemShovelH.setIconIndex(5);
		itemSwordHeadMold.setIconIndex (0);
		itemSwordHeadH.setIconIndex (1);
		itemSwordH.setIconIndex (2);
		itemHoeHeadMold.setIconIndex (10);
		itemHoeHeadH.setIconIndex (11);
		itemHoeH.setIconIndex (12);
		//Create KilnSide, KilnSide2, KilnTop, KilnOn, KilnOff, KilnGUI
	}
	
	


}
