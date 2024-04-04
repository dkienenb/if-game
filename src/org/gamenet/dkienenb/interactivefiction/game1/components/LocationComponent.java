package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.component.MutableDataStoringComponent;

public class LocationComponent extends MutableDataStoringComponent<ComponentedObject> {

    public void setLocation(ComponentedObject container) {
        if(container == null || container.hasComponent(ContainerComponent.class)) {
            setValue(container);
            if (container != null) {
                ContainerComponent containerComponent = container.getComponent(ContainerComponent.class);
                ComponentedObject attached = getAttached();
                if (!containerComponent.contains(attached)) {
                    containerComponent.forceAddItem(attached);
                }
            }
        } else {
            throw new IllegalArgumentException("Attempted to put " + this + " in an object that wasn't a container (" + container + ").");
        }
    }

    public ComponentedObject getLocation() {
        return getValue();
    }
}
