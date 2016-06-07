package net.thefallenmoon.fmc.core;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = FallenCoreMod.MODID, version = FallenCoreMod.VERSION)
public class FallenCoreMod {
    public static final String MODID = "fmc.core";
    public static final String VERSION = "1.0.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());
    }
}
