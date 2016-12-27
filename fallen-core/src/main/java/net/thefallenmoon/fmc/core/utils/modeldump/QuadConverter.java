package net.thefallenmoon.fmc.core.utils.modeldump;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.pipeline.QuadGatheringTransformer;
import net.thefallenmoon.fmc.core.FallenCoreMod;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class QuadConverter extends QuadGatheringTransformer{
    public DumpedQuad dumpedQuad = new DumpedQuad();

    @Override
    protected void processQuad() {
        int posIndex = -1;
        int normalIndex = -1;
        int colorIndex = -1;
        int uvIndex = -1;
        VertexFormat format = getVertexFormat();
        for (VertexFormatElement element : format.getElements()) {
            switch (element.getUsage()) {
                case POSITION:
                    posIndex = element.getIndex();
                    break;
                case NORMAL:
                    normalIndex = element.getIndex();
                    break;
                case COLOR:
                    colorIndex = element.getIndex();
                    break;
                case UV:
                    uvIndex = element.getIndex();
                    break;
                default:
            }
        }

        float[][] position = this.quadData[posIndex];
        float[][] normal = this.quadData[normalIndex];
        float[][] color = this.quadData[colorIndex];
        float[][] uv = this.quadData[colorIndex];

        if (posIndex < 0 || normalIndex < 0 || colorIndex < 0 || uvIndex < 0) {
            FallenCoreMod.logger.error("Missing a required vertex attribute; not dumping");
            return;
        }

        for (float[] p : position) {
            dumpedQuad.verts.add(new Vector3f(p[0], p[1], p[2]));
        }
        for (float[] n : normal) {
            dumpedQuad.normals.add(new Vector3f(n[0], n[1], n[2]));
        }
        for (float[] c : color) {
            dumpedQuad.colors.add(new Color4f(c[0], c[1], c[2]));
        }
        for (float[] p : uv) {
            dumpedQuad.uv.add(new Vector2f(p[0], p[1]));
        }
    }

    @Override
    public void setQuadTint(int i) {

    }

    @Override
    public void setQuadOrientation(EnumFacing enumFacing) {

    }

    @Override
    public void setApplyDiffuseLighting(boolean b) {

    }

	@Override
	public void setTexture(TextureAtlasSprite texture) {
		// TODO Auto-generated method stub
		
	}
}
