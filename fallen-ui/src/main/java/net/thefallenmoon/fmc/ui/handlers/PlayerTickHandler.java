package net.thefallenmoon.fmc.ui.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thefallenmoon.fmc.ui.FallenUIMod;
import net.thefallenmoon.fmc.ui.gui.GuiTest;

public class PlayerTickHandler {

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent tick) {
        if (tick.side.isClient() && KeyInputHandler.openGui.isPressed()) {
            EntityPlayer player = tick.player;
            player.openGui(FallenUIMod.instance, GuiTest.GUI_ID, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
    }
}
