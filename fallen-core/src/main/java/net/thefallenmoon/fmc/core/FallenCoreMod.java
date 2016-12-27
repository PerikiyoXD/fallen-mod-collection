package net.thefallenmoon.fmc.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.thefallenmoon.fmc.core.proxy.CommonProxy;

@Mod(modid = FallenCoreMod.MODID, version = FallenCoreMod.VERSION)
public class FallenCoreMod {
    public static final Logger logger = LogManager.getLogger(FallenCoreMod.class);
    public static final String MODID = "fmc-core";
    public static final String VERSION = "1.0.0";

    @SidedProxy(
            clientSide = "net.thefallenmoon.fmc.core.proxy.ClientProxy",
            serverSide = "net.thefallenmoon.fmc.core.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerCommands();

        // some example code
        System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());
    }
}
