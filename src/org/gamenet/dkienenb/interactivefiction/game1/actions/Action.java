package org.gamenet.dkienenb.interactivefiction.game1.actions;

import java.util.List;

import org.gamenet.dkienenb.component.ComponentedObject;

public abstract class Action {

	public abstract boolean canBeDone(ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects);
	public abstract void act(ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects);

}
