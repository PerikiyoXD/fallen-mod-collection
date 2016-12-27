package net.thefallenmoon.fmc.core.utils.modeldump;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockModel {
    public String name;
    public String stateName;
    public boolean opaque;
    public int meta;
    public Map<QuadFacing, List<DumpedQuad>> quads = new HashMap<>();
}
