package net.thefallenmoon.fmc.core.utils.modeldump;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class DumpedQuad {
    String texture;
    List<Vector3f> verts = new ArrayList<>();
    List<Vector2f> uv = new ArrayList<>();
    List<Vector3f> normals = new ArrayList<>();
    List<Color4f> colors = new ArrayList<>();
}
