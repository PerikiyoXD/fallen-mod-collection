package net.thefallenmoon.fmc.core.proxy;

import net.minecraftforge.client.ClientCommandHandler;
import net.thefallenmoon.fmc.core.commands.DumpModelCommand;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerCommands() {
        super.registerCommands();
        ClientCommandHandler.instance.registerCommand(new DumpModelCommand());
    }
}
