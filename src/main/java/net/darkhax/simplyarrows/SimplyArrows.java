package net.darkhax.simplyarrows;

import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.OreDictUtils;
import net.darkhax.simplyarrows.creativetab.CreativeTabSimplyArrows;
import net.darkhax.simplyarrows.items.ItemArrowBase;
import net.darkhax.simplyarrows.logic.ArrowLogicPlaceBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "simplyarrows", name = "Simply Arrows", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.2.523,);", certificateFingerprint = "@FINGERPRINT@")
public class SimplyArrows {

    public static final LoggingHelper LOG = new LoggingHelper("Simply Arrows");
    public static final RegistryHelper REGISTRY = new RegistryHelper("simplyarrows").enableAutoRegistration().setTab(new CreativeTabSimplyArrows());

    public static Item itemArrowBone;
    public static Item itemArrowFlint;
    public static Item itemArrowIron;
    public static ItemArrowBase itemArrowFlame;
    public static Item itemArrowCake;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        itemArrowBone = REGISTRY.registerItem(new ItemArrowBase().setDamage(1f).setInfinity(true), "arrow_bone");
        itemArrowFlint = REGISTRY.registerItem(new ItemArrowBase().setDamage(2f).setInfinity(true), "arrow_flint");
        itemArrowIron = REGISTRY.registerItem(new ItemArrowBase().setDamage(3f).setInfinity(true), "arrow_iron");
        itemArrowFlame = (ItemArrowBase) REGISTRY.registerItem(new ItemArrowBase().setDamage(2f).setFlaming(true), "arrow_flame");
        itemArrowCake = REGISTRY.registerItem(new ItemArrowBase().setDamage(0).setLogic(new ArrowLogicPlaceBlock(Blocks.CAKE.getDefaultState(), new ItemStack(Items.CAKE))), "arrow_cake");

        REGISTRY.addShapedRecipe("arrow_bone", new ItemStack(itemArrowBone, 6), "  i", "br ", "fb ", 'i', OreDictUtils.BONE, 'b', OreDictUtils.STRING, 'r', OreDictUtils.STICK_WOOD, 'f', OreDictUtils.FEATHER);
        REGISTRY.addShapedRecipe("arrow_flint", new ItemStack(itemArrowFlint, 6), "  i", "br ", "fb ", 'i', Items.FLINT, 'b', OreDictUtils.STRING, 'r', OreDictUtils.STICK_WOOD, 'f', OreDictUtils.FEATHER);
        REGISTRY.addShapedRecipe("arrow_iron", new ItemStack(itemArrowIron, 6), "  i", "br ", "fb ", 'i', OreDictUtils.INGOT_IRON, 'b', OreDictUtils.STRING, 'r', OreDictUtils.STICK_WOOD, 'f', OreDictUtils.FEATHER);
        REGISTRY.addShapedRecipe("arrow_flame", new ItemStack(itemArrowFlame, 8), "aaa", "afa", "aaa", 'a', itemArrowFlint, 'f', Items.FIRE_CHARGE);
        REGISTRY.addShapelessRecipe("arrow_cake", new ItemStack(itemArrowCake), Items.CAKE, itemArrowFlint);
    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {

        // TODO set up custom arrow pickups
        itemArrowFlame.setDropItem(ItemStack.EMPTY);
    }
}