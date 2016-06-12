package net.thefallenmoon.fmc.ui.material.bounds;

import net.thefallenmoon.fmc.ui.material.Component;
import net.thefallenmoon.fmc.ui.material.ComponentBounds;

public class RelativeComponentBounds implements ComponentBounds {
    public enum TopBottom {
        TOP, BOTTOM
    }

    public enum LeftRight {
        LEFT, RIGHT
    }

    private final float width;
    private final float height;
    private final Component other;
    private final TopBottom myVertical;
    private final TopBottom otherVertical;
    private final LeftRight myHorizontal;
    private final LeftRight otherHorizontal;

    public RelativeComponentBounds(float width, float height, LeftRight myHorizontal, TopBottom myVertical, Component other, TopBottom otherVertical, LeftRight otherHorizontal) {
        this.width = width;
        this.height = height;
        this.other = other;
        this.myVertical = myVertical;
        this.otherVertical = otherVertical;
        this.myHorizontal = myHorizontal;
        this.otherHorizontal = otherHorizontal;
    }

    @Override
    public float getX() {
        float myOffset;
        if (myHorizontal == LeftRight.LEFT) {
            myOffset = 0;
        } else {
            myOffset = -getWidth();
        }

        float otherOffset;
        if (otherHorizontal == LeftRight.LEFT) {
            otherOffset = other.getBounds().getX();
        } else {
            otherOffset = other.getBounds().getX() + other.getBounds().getWidth();
        }

        return myOffset + otherOffset;
    }

    @Override
    public float getY() {
        float myOffset;
        if (myVertical == TopBottom.TOP) {
            myOffset = 0;
        } else {
            myOffset = -getHeight();
        }

        float otherOffset;
        if (otherVertical == TopBottom.TOP) {
            otherOffset = other.getBounds().getY();
        } else {
            otherOffset = other.getBounds().getY() + other.getBounds().getHeight();
        }

        return myOffset + otherOffset;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getEndX() {
        return getX() + getWidth();
    }

    @Override
    public float getEndY() {
        return getY() + getHeight();
    }
}
