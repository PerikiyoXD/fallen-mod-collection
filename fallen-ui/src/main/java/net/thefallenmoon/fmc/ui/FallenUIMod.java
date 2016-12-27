package net.thefallenmoon.fmc.ui;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.thefallenmoon.fmc.ui.handlers.KeyInputHandler;
import net.thefallenmoon.fmc.ui.handlers.PlayerTickHandler;
import net.thefallenmoon.fmc.ui.network.NetworkHandler;
import net.thefallenmoon.fmc.ui.proxy.CommonProxy;

@Mod(modid = FallenUIMod.MODID, version = FallenUIMod.VERSION)
public class FallenUIMod {
    public static final String MODID = "fmc-ui";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(FallenUIMod.MODID)
    public static FallenUIMod instance;

    @SidedProxy(
            clientSide = "net.thefallenmoon.fmc.ui.proxy.ClientProxy",
            serverSide = "net.thefallenmoon.fmc.ui.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkHandler.registerMessages();
        proxy.registerKeybinds();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    }
}
