package net.thefallenmoon.fmc.ui.handlers;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class KeyInputHandler {
    public static KeyBinding openGui = new KeyBinding("G", Keyboard.KEY_G, "key.categories.fmc.ui.open");;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
    }
}
