package net.thefallenmoon.fmc.ui.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.thefallenmoon.fmc.ui.handlers.KeyInputHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeybinds() {
        super.registerKeybinds();
        ClientRegistry.registerKeyBinding(KeyInputHandler.openGui);
    }
}
