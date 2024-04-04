package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.component.MutableDataStoringComponent;

import java.util.Comparator;

public class ActorPriorityComponent extends MutableDataStoringComponent<Integer> {

    public int getPriority() {
        return getValue();
    }

    public void setPriority(int priority) {
        setValue(priority);
    }

    public static class ActorPriorityComparator implements Comparator<ComponentedObject> {
        @Override
        public int compare(ComponentedObject o1, ComponentedObject o2) {
            return o1.getComponent(ActorPriorityComponent.class).getPriority() - o2.getComponent(ActorPriorityComponent.class).getPriority();
        }
    }
}
