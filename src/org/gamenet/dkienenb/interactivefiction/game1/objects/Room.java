package org.gamenet.dkienenb.interactivefiction.game1.objects;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.interactivefiction.game1.components.ConnectionsComponent;
import org.gamenet.dkienenb.interactivefiction.game1.components.ContainerComponent;
import org.gamenet.dkienenb.interactivefiction.game1.components.DescriptionComponent;
import org.gamenet.dkienenb.interactivefiction.game1.components.NameComponent;

public class Room extends ComponentedObject {

	public Room(String name, String description) {
		addComponent(new ConnectionsComponent());
		addComponent(new ContainerComponent(ContainerComponent.Constants.UNLIMITED, ContainerComponent.Constants.UNLIMITED));

		addComponent(new NameComponent(name));
		addComponent(new DescriptionComponent(description));
	}

}
