package net.thefallenmoon.fmc.ui.material.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.thefallenmoon.fmc.core.render.ShaderProgram;
import net.thefallenmoon.fmc.core.utils.ResourceUtils;
import net.thefallenmoon.fmc.ui.FallenUIMod;
import net.thefallenmoon.fmc.ui.material.ComponentBase;
import net.thefallenmoon.fmc.ui.material.ComponentBounds;
import net.thefallenmoon.fmc.ui.material.bounds.StartEndComponentBounds;

public class Paper extends ComponentBase {
    private float elevation;
    private boolean drawShadow = true;
    private int r = 255;
    private int g = 255;
    private int b = 255;

    public static final ShaderProgram shader = new ShaderProgram(
            ResourceUtils.loadResourceAsString(FallenUIMod.instance, "/assets/fmc-ui/shaders/material-shadow-square.vert"),
            ResourceUtils.loadResourceAsString(FallenUIMod.instance, "/assets/fmc-ui/shaders/material-shadow-square.frag")
    );

    public Paper(ComponentBounds bounds, float elevation) {
        super(bounds);
        this.elevation = elevation;
    }

    public boolean willDrawShadow() {
        return drawShadow;
    }

    public void setDrawShadow(boolean drawShadow) {
        this.drawShadow = drawShadow;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void render() {
        //Paper box
    	//TODO: Are these necessary?
    	//
        //GlStateManager.disableLighting();
    	//GlStateManager.disableFog();
        //
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);


        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();

        if (drawShadow) {
            shader.enable();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
            drawShadow(buffer);
            tess.draw();
            shader.disable();
        }

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        drawPaperBackground(buffer);
        tess.draw();
                
        //Reenable these two as without these FontRenderers wont render properly!
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        
        super.render();
    }

    private void drawPaperBackground(BufferBuilder buffer) {
        ComponentBounds bounds = getBounds();
        buffer.pos(bounds.getX(), bounds.getEndY(), 0).color(r, g, b, 255).endVertex();
        buffer.pos(bounds.getEndX(), bounds.getEndY(), 0).color(r, g, b, 255).endVertex();
        buffer.pos(bounds.getEndX(), bounds.getY(), 0).color(r, g, b, 255).endVertex();
        buffer.pos(bounds.getX(), bounds.getY(), 0).color(r, g, b, 255).endVertex();
    }

    private void drawShadow(BufferBuilder buffer) {
        float shadowSize = 50;
        float blur = elevation;

        /*
         * TODO: Unused stuff, what to do?
         * 
        
        int startAlpha = 255;
        int endAlpha = 0;
        
        */
        
        ComponentBounds bounds = getBounds();
        ComponentBounds shadowBounds = new StartEndComponentBounds(
                bounds.getX() - shadowSize,
                bounds.getY() - shadowSize,
                bounds.getEndX() + shadowSize,
                bounds.getEndY() + shadowSize
        );

        GL20.glUniform4f(shader.getFragUniformLocation("bounds"),
                bounds.getX(),
                bounds.getEndX(),
                bounds.getY(),
                bounds.getEndY()
        );

        GL20.glUniform1f(shader.getFragUniformLocation("blur"), blur);

        //Shading is handled by the shader
        buffer.pos(shadowBounds.getX(), shadowBounds.getEndY(), 0).endVertex();
        buffer.pos(shadowBounds.getEndX(), shadowBounds.getEndY(), 0).endVertex();
        buffer.pos(shadowBounds.getEndX(), shadowBounds.getY(), 0).endVertex();
        buffer.pos(shadowBounds.getX(), shadowBounds.getY(), 0).endVertex();
    }

}
