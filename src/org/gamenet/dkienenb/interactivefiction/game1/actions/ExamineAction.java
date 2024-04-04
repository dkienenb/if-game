package org.gamenet.dkienenb.interactivefiction.game1.actions;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.interactivefiction.game1.components.NameComponent;

import java.util.List;

public class ExamineAction extends Action {
    @Override
    public boolean canBeDone(ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects) {
        if (!indirectObjects.isEmpty()) {
            sendInvalidArgsMessage(actor, false, false);
            return false;
        } else if (directObjects.isEmpty()) {
            sendInvalidArgsMessage(actor, true, true);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onAct(ComponentedObject actor, List<ComponentedObject> directObjects, List<ComponentedObject> indirectObjects) {
        for (ComponentedObject directObject : directObjects) {
            examine(actor, directObject);
        }
    }

    private void examine(ComponentedObject actor, ComponentedObject thingToExamine) {
        String name = thingToExamine.getComponent(NameComponent.class).getName().toLowerCase();
        boolean interestingInformation = false;
        if (!interestingInformation) {
            sendFeedback(actor, "There is nothing interesting about the " + name + ".");
        }
    }

    @Override
    public String getActionName() {
        return "examine";
    }
}
