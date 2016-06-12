package net.thefallenmoon.fmc.ui.material.bounds;

import net.thefallenmoon.fmc.ui.material.ComponentBounds;

public class StartEndComponentBounds implements ComponentBounds {
    private final float x;
    private final float y;
    private final float endX;
    private final float endY;

    public StartEndComponentBounds(float x, float y, float endX, float endY) {
        this.x = x;
        this.y = y;
        this.endY = endY;
        this.endX = endX;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return endX - x;
    }

    @Override
    public float getHeight() {
        return endY - y;
    }

    @Override
    public float getEndX() {
        return endX;
    }

    @Override
    public float getEndY() {
        return endY;
    }
}
