package net.thefallenmoon.fmc.ui.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.thefallenmoon.fmc.ui.FallenUIMod;

public class NetworkHandler {
    public static SimpleNetworkWrapper uiChannel = NetworkRegistry.INSTANCE.newSimpleChannel(FallenUIMod.MODID);

    public static void registerMessages() {
    }

}
