package org.gamenet.dkienenb.interactivefiction.game1.objects;

import java.util.List;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.interactivefiction.game1.actions.Action;
import org.gamenet.dkienenb.interactivefiction.game1.components.*;

public class Human extends ComponentedObject {

	public Human(String trueName, String description, ComponentedObject location) {
		this(ContainableComponent.Constants.HUMAN_WEIGHT, ContainerComponent.Constants.HUMAN_STRENGTH, ContainerComponent.Constants.HUMAN_CARRYING_LIMIT, trueName, location, description);
	}

	public Human(int weight, int carryingCapacityWeight, int carryingCapacityItems, String name, ComponentedObject location, String description) {
		addComponent(new LocationComponent());
		getComponent(LocationComponent.class).setLocation(location);
		addComponent(new ContainableComponent(weight));
		addComponent(new ContainerComponent(carryingCapacityWeight, carryingCapacityItems));
		addComponent(new NameComponent(name));
		addComponent(new ActorPriorityComponent());
		addComponent(new ActorComponent());
		addComponent(new ExternalDescriptionComponent(description));
		addComponent(new VisibleComponent());
	}

	public void setAction(Action action, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects) {
		getComponent(ActorComponent.class).setAction(action, directObjects, indirectObjects);
	}
}
