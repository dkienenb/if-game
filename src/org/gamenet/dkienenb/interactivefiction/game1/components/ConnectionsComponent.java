package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.MapStoringComponent;
import org.gamenet.dkienenb.interactivefiction.game1.objects.Direction;
import org.gamenet.dkienenb.interactivefiction.game1.objects.Room;

public class ConnectionsComponent extends MapStoringComponent<Direction, Room> {

	public ConnectionsComponent() {
		super();
	}

	public void connect(Direction direction, Room room) {
		put(direction, room);
	}

	public void disconnect(Direction direction) {
		remove(direction);
	}

}
