package org.gamenet.dkienenb.interactivefiction.game1.components;

import java.util.List;

import org.gamenet.dkienenb.component.Component;
import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.component.MutableDataStoringComponent;
import org.gamenet.dkienenb.event.EventListener;
import org.gamenet.dkienenb.event.EventListenerPriorityLevel;
import org.gamenet.dkienenb.interactivefiction.game1.Main;
import org.gamenet.dkienenb.interactivefiction.game1.actions.Action;
import org.gamenet.dkienenb.interactivefiction.game1.events.MainLoopEvent;

public class ActorComponent extends MutableDataStoringComponent<Action> {

	private List<ComponentedObject> directObjects;
	private List<ComponentedObject> indirectObjects;

	public ActorComponent() {
		this(EventListenerPriorityLevel.REACT);
	}

	public ActorComponent(EventListenerPriorityLevel priorityLevel) {
		Main.MAIN_BUS.addListener(new EventListener(MainLoopEvent.class, priorityLevel, event -> {
			if (hasAction()) {
				executeAction();
			}
		}));
	}

	@Override
	public List<Class<? extends Component>> getDependencies() {
		List<Class<? extends Component>> dependencies = super.getDependencies();
		dependencies.add(ActorPriorityComponent.class);
		dependencies.add(ActorFeedbackComponent.class);
		return dependencies;
	}

	public void setAction(Action action, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects) {
		setValue(action);
		this.directObjects = directObjects;
		this.indirectObjects = indirectObjects;
	}

	public void executeAction() {
		try {
			getAction().act(getAttached(), directObjects, indirectObjects);
		} catch (NullPointerException e) {
			throw new IllegalStateException("Action is not set, but was executed anyways", e);
		}
		setAction(null, null, null);
	}

	public boolean hasAction() {
		return getAction() != null;
	}

	public Action getAction() {
		return getValue();
	}

}
