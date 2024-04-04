package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.Component;
import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.event.EventListener;
import org.gamenet.dkienenb.event.EventListenerPriorityLevel;
import org.gamenet.dkienenb.interactivefiction.game1.Main;
import org.gamenet.dkienenb.interactivefiction.game1.actions.Action;
import org.gamenet.dkienenb.interactivefiction.game1.events.MainLoopEvent;
import org.gamenet.dkienenb.interactivefiction.game1.grammer.Parser;

import java.util.List;

public class PlayerControlledComponent extends Component {
    public List<Class<? extends Component>> getDependencies() {
        List<Class<? extends Component>> dependencies = super.getDependencies();
        dependencies.add(ActorComponent.class);
        dependencies.add(ActorFeedbackComponent.class);
        return dependencies;
    }

    public PlayerControlledComponent() {
        Main.MAIN_BUS.addListener(new EventListener(MainLoopEvent.class, EventListenerPriorityLevel.MODIFY, event -> {
            Parser parser = Main.PARSER;
            parser.loadNext(getAttached());
            Action action = parser.getAction();
            List<ComponentedObject> directObjects = parser.getDirectObjects();
            List<ComponentedObject> indirectObjects = parser.getIndirectObjects();
            getAttached().getComponent(ActorComponent.class).setAction(action, directObjects, indirectObjects);
        }));
    }

    @Override
    public void setAttached(ComponentedObject attached) {
        super.setAttached(attached);
        getAttached().getComponent(ActorFeedbackComponent.class).setOut(Main.OUT);
    }
}
