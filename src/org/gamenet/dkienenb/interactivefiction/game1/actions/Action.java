package org.gamenet.dkienenb.interactivefiction.game1.actions;

import java.util.List;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.event.EventListener;
import org.gamenet.dkienenb.event.EventListenerPriorityLevel;
import org.gamenet.dkienenb.interactivefiction.game1.Main;
import org.gamenet.dkienenb.interactivefiction.game1.components.ActorFeedbackComponent;
import org.gamenet.dkienenb.interactivefiction.game1.events.ActionEvent;

public abstract class Action {

	public abstract boolean canBeDone(ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects);
	public void act(ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects) {
		Main.MAIN_BUS.addEvent(new ActionEvent(this, actor, directObjects, indirectObjects));
		Main.MAIN_BUS.addListener(new EventListener(ActionEvent.class, EventListenerPriorityLevel.REACT_LOW, event -> {
			ActionEvent actionEvent = (ActionEvent) event;
			Action action = actionEvent.getAction();
			if (this.equals(action)) {
				ComponentedObject localActor = actionEvent.getActor();
				List<ComponentedObject> localDirectObjects = actionEvent.getDirectObjects();
				List<ComponentedObject> localIndirectObjects = actionEvent.getIndirectObjects();
				if (canBeDone(localActor, localDirectObjects, localIndirectObjects)) {
					onAct(localActor, localDirectObjects, localIndirectObjects);
				}
			}
		}));
	}

	protected abstract void onAct(ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects);
	public abstract String getActionName();

	protected void sendFeedback(ComponentedObject actor, String feedback) {
		actor.getComponent(ActorFeedbackComponent.class).sendFeedback(feedback);
	}

	protected void sendInvalidArgsMessage(ComponentedObject actor, boolean directObjects, boolean needed) {
		StringBuilder sb = new StringBuilder();
		sb.append("You cannot use \"").append(getActionName()).append("\" with");
		if (needed) {
			sb.append("out");
		}
		sb.append(" ");
		if (!directObjects) {
			sb.append("in");
		}
		sb.append("direct objects.");
		sendFeedback(actor, sb.toString());
	}
}
