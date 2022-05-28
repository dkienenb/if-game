package org.gamenet.dkienenb.interactivefiction.game1;

import org.gamenet.dkienenb.component.MapStoringComponent;

public class ConnectionsComponent extends MapStoringComponent<Direction, Room> {

	public void connect(Direction direction, Room room) {
		put(direction, room);
	}

	public void disconnect(Direction direction) {
		remove(direction);
	}
}
