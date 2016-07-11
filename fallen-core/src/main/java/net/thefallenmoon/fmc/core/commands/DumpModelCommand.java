package net.thefallenmoon.fmc.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.thefallenmoon.fmc.core.utils.modeldump.ModelDumper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DumpModelCommand implements ICommand{

    @Override
    public String getCommandName() {
        return "fmc-dump-models";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "fmc-dump-models <path>";
    }

    @Override
    public List<String> getCommandAliases() {
        return new ArrayList<>();
    }

    @Override
    public boolean checkPermission(MinecraftServer minecraftServer, ICommandSender iCommandSender) {
        return true;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings, @Nullable BlockPos blockPos) {
        return new ArrayList<>();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return o.getCommandName().compareTo(getCommandName());
    }

    @Override
    public void execute(MinecraftServer minecraftServer, final ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.addChatMessage(new TextComponentString("Must provide path"));
            return;
        }
        new Thread(() -> {
            new ModelDumper(sender, Arrays.stream(args).collect(Collectors.joining(" "))).dumpModels();
        }).start();
    }
}
