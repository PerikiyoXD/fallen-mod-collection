package net.thefallenmoon.fmc.core.utils.modeldump;

import net.minecraft.util.EnumFacing;

public enum QuadFacing {
    UP(EnumFacing.UP),
    DOWN(EnumFacing.DOWN),
    NORTH(EnumFacing.NORTH),
    SOUTH(EnumFacing.SOUTH),
    EAST(EnumFacing.EAST),
    WEST(EnumFacing.WEST),
    ANY(null);

    public final EnumFacing dir;

    private QuadFacing(EnumFacing dir) {
        this.dir = dir;
    }
}
