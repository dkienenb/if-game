package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.Component;
import org.gamenet.dkienenb.component.MutableDataStoringComponent;

import java.util.List;

public class VisibleComponent extends MutableDataStoringComponent<Boolean> {
    @Override
    public List<Class<? extends Component>> getDependencies() {
        List<Class<? extends Component>> dependencies = super.getDependencies();
        dependencies.add(ExternalDescriptionComponent.class);
        return dependencies;
    }

    public VisibleComponent() {
        setCurrentlyVisible(true);
    }

    public boolean isCurrentlyVisible() {
        return getValue();
    }

    public void setCurrentlyVisible(boolean currentlyVisible) {
        setValue(currentlyVisible);
    }
}
