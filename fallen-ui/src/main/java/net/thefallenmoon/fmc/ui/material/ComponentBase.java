package net.thefallenmoon.fmc.ui.material;


import java.util.ArrayList;
import java.util.List;

public abstract class ComponentBase implements Component {
    private List<Component> subComponents;
    private ComponentBounds bounds;

    public ComponentBase(ComponentBounds bounds) {
        this.bounds = bounds;
        subComponents = new ArrayList<>();
    }

    public void render() {
        subComponents.forEach(Component::render);
    }

    public List<Component> getSubComponents() {
        return subComponents;
    }

    public void addSubComponent(Component subComponent) {
        this.subComponents.add(subComponent);
    }

    @Override
    public ComponentBounds getBounds() {
        return bounds;
    }

    public void setBounds(ComponentBounds bounds) {
        this.bounds = bounds;
    }
}
