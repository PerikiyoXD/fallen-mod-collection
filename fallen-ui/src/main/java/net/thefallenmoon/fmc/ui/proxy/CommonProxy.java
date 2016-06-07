package net.thefallenmoon.fmc.ui.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.thefallenmoon.fmc.ui.gui.GuiTest;

public class CommonProxy implements IGuiHandler {
    public void registerKeybinds() {
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == GuiTest.GUI_ID) {
            return new GuiTest();
        } else {
            return null;
        }
    }
}
