package net.thefallenmoon.fmc.ui.material.components;

import lombok.Getter;
import lombok.Setter;
import net.thefallenmoon.fmc.ui.material.ComponentBase;
import net.thefallenmoon.fmc.ui.material.ComponentBounds;

public class Paper extends ComponentBase {
    private float elevation;

    public Paper(ComponentBounds bounds, float elevation) {
        super(bounds);
        this.elevation = elevation;
    }

    @Override
    public void render() {
        ComponentBounds bounds = getBounds();
    }


}
