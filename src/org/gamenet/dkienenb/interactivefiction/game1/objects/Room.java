package org.gamenet.dkienenb.interactivefiction.game1.objects;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.interactivefiction.game1.components.ConnectionsComponent;
import org.gamenet.dkienenb.interactivefiction.game1.components.ContainerComponent;
import org.gamenet.dkienenb.interactivefiction.game1.components.ShortDescriptionComponent;

public class Room extends ComponentedObject {

	public Room(String name) {
		addComponent(new ConnectionsComponent());
		addComponent(new ContainerComponent(ContainerComponent.UNLIMITED, ContainerComponent.UNLIMITED));

		addComponent(new ShortDescriptionComponent());
		getComponent(ShortDescriptionComponent.class).setDescription(name);
	}

}
