package net.thefallenmoon.fmc.ui.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thefallenmoon.fmc.ui.handlers.KeyInputHandler;
import net.thefallenmoon.fmc.ui.handlers.PlayerTickHandler;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeybinds() {
        super.registerKeybinds();
        ClientRegistry.registerKeyBinding(KeyInputHandler.openGui);
    }
}
