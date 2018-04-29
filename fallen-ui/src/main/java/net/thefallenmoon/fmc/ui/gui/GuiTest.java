package net.thefallenmoon.fmc.ui.gui;

import net.minecraft.client.gui.GuiScreen;
import net.thefallenmoon.fmc.core.utils.ResourceUtils;
import net.thefallenmoon.fmc.ui.FallenUIMod;
import net.thefallenmoon.fmc.ui.material.bounds.StartEndComponentBounds;
import net.thefallenmoon.fmc.ui.material.bounds.WidthHeightComponentBounds;
import net.thefallenmoon.fmc.ui.material.components.Paper;

import java.util.Random;

public class GuiTest extends GuiScreen {
    public static final int GUI_ID = 1;
    private Paper backgroundPaper;
    private Paper paperTest;
    private Paper colorShiftingPaper;
    private Random rng = new Random();

    @Override
    public void initGui() {
        String vertSrc = ResourceUtils.loadResourceAsString(FallenUIMod.instance, "/assets/fmc-ui/shaders/material-shadow-square.vert");
        String fragSrc = ResourceUtils.loadResourceAsString(FallenUIMod.instance, "/assets/fmc-ui/shaders/material-shadow-square.frag");
        Paper.shader.compileShaderSource(
                vertSrc,
                fragSrc
        );

        backgroundPaper = new Paper(new StartEndComponentBounds(10, 10, width / 2 - 10, height - 10), 1f);
        paperTest = new Paper(new WidthHeightComponentBounds(40, 40, 40, 40), 1f);
        backgroundPaper.addSubComponent(paperTest);

        colorShiftingPaper = new Paper(new WidthHeightComponentBounds(90, 190, 130, 90), 1f);
        colorShiftingPaper.setColor(127,127,255);
        backgroundPaper.addSubComponent(colorShiftingPaper);


        backgroundPaper.addSubComponent(paperTest);

        for (int height = 0; height < 24; height++) {
            int col = height % 6;
            int row = height / 6;
            Paper staticPaper = new Paper(new WidthHeightComponentBounds(100 + col * 20, 100 + row * 20, 10, 10), height);
            backgroundPaper.addSubComponent(staticPaper);
            staticPaper = new Paper(new WidthHeightComponentBounds(100 + col * 20, 200 + row * 20, 10, 10), height);
            staticPaper.setColor(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255));
            backgroundPaper.addSubComponent(staticPaper);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        paperTest.setElevation((float) Math.abs((Math.sin(System.currentTimeMillis() / 1000.0) * 32)));

        fontRenderer.drawString("elevation " + paperTest.getElevation(), 0, 5, 0xFFFFFFFF);
        drawRect(0, 0, (int)paperTest.getElevation(), 3, 0xFFFFFFFF);

        //drawDefaultBackground();
        backgroundPaper.render();
    }
}
