package org.gamenet.dkienenb.interactivefiction.game1.objects;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.interactivefiction.game1.components.*;

public class Room extends ComponentedObject {

	public Room(String name, String description) {
		addComponent(new ConnectionsComponent());
		addComponent(new ContainerComponent(ContainerComponent.Constants.UNLIMITED, ContainerComponent.Constants.UNLIMITED));

		addComponent(new NameComponent(name));
		addComponent(new InternalDescriptionComponent(description));
	}

}
