package org.gamenet.dkienenb.interactivefiction.game1;

import org.gamenet.dkienenb.event.EventBus;
import org.gamenet.dkienenb.interactivefiction.game1.components.EyesComponent;
import org.gamenet.dkienenb.interactivefiction.game1.components.PlayerControlledComponent;
import org.gamenet.dkienenb.interactivefiction.game1.events.MainLoopEvent;
import org.gamenet.dkienenb.interactivefiction.game1.grammer.Parser;
import org.gamenet.dkienenb.interactivefiction.game1.objects.Human;
import org.gamenet.dkienenb.interactivefiction.game1.objects.Room;
import org.gamenet.dkienenb.textgamecomponents.Inputer;
import org.gamenet.dkienenb.textgamecomponents.Outputer;

public class Main {

	public static final EventBus MAIN_BUS = new EventBus();
	public static final Outputer OUT = new Outputer();
	public static final Inputer IN = new Inputer(OUT, "> ");
	public static final Parser PARSER = new Parser();

	private static boolean running = true;

	public static void killProgram() {
		running = false;
	}

	public static void main(String[] args) {
		Room startingRoom = new Room("Starting room", "You are in a test room, full of bugs.");
		Human player = new Human("Player", "A cretin is standing here.", startingRoom);
		player.addComponent(new EyesComponent());
		player.addComponent(new PlayerControlledComponent());
		while (running) {
			if (MAIN_BUS.hasNextEvent()) {
				MAIN_BUS.callNextEvent();
			} else {
				MAIN_BUS.addEvent(new MainLoopEvent());
			}
		}
	}

	/* 
	 * loop, once everything has decided upon a course of action, then move according to priority. 
	 * handle multi-move actions
	 * */
}
