package org.gamenet.dkienenb.interactivefiction.game1.events;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.event.Event;
import org.gamenet.dkienenb.interactivefiction.game1.actions.Action;

import java.util.List;

public class ActionEvent extends Event {
    private final Action action;
    private final ComponentedObject actor;
    private final List<ComponentedObject> directObjects;
    private final List<ComponentedObject> indirectObjects;

    public ActionEvent(Action action, ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects) {
        this.action = action;
        this.actor = actor;
        this.directObjects = directObjects;
        this.indirectObjects = indirectObjects;
    }

    public Action getAction() {
        return action;
    }

    public ComponentedObject getActor() {
        return actor;
    }

    public List<ComponentedObject> getDirectObjects() {
        return directObjects;
    }

    public List<ComponentedObject> getIndirectObjects() {
        return indirectObjects;
    }
}
