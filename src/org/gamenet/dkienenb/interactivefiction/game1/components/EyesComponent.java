package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.Component;
import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.component.MutableDataStoringComponent;
import org.gamenet.dkienenb.event.EventListener;
import org.gamenet.dkienenb.event.EventListenerPriorityLevel;
import org.gamenet.dkienenb.interactivefiction.game1.Main;
import org.gamenet.dkienenb.interactivefiction.game1.events.MainLoopEvent;
import org.gamenet.dkienenb.textgamecomponents.Outputer;

import java.util.List;

public class EyesComponent extends MutableDataStoringComponent<Boolean> {

    @Override
    public List<Class<? extends Component>> getDependencies() {
        List<Class<? extends Component>> dependencies = super.getDependencies();
        dependencies.add(LocationComponent.class);
        return dependencies;
    }

    public EyesComponent() {
        setWatching(true);
        Main.MAIN_BUS.addListener(new EventListener(MainLoopEvent.class, EventListenerPriorityLevel.REACT, event -> lookAround()));
    }

    public void lookAround() {
        if (isWatching()) {
            ComponentedObject location = getAttached().getComponent(LocationComponent.class).getLocation();
            Outputer out = Main.OUT;
            out.outputLine("");
            out.outputLine(location.getComponent(NameComponent.class).getName());
            out.outputLine(location.getComponent(InternalDescriptionComponent.class).getDescription());
            location.getComponent(ContainerComponent.class).streamItems()
                    .filter(object -> object.hasComponent(VisibleComponent.class))
                    .filter(object -> object.getComponent(VisibleComponent.class).isCurrentlyVisible())
                    .filter(object -> !object.equals(getAttached()))
                    .forEach(object -> out.outputLine(object.getComponent(ExternalDescriptionComponent.class).getDescription()));
        }
    }

    public boolean isWatching() {
        return getValue();
    }

    public void setWatching(boolean watching) {
        setValue(watching);
    }

}
