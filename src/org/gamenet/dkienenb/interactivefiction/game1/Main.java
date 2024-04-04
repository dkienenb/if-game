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

import java.util.List;
import java.util.Random;

public class Main {

	public static final EventBus MAIN_BUS = new EventBus();
	public static final Outputer OUT = new Outputer();
	public static final Inputer IN = new Inputer(OUT, "> ");
	public static final Parser PARSER = new Parser();

	private static boolean running = true;
	public static Random random_for_world_generation;

	public static void killProgram() {
		running = false;
	}

	public static void main(String[] args) {
		init();
		while (running) {
			if (MAIN_BUS.hasNextEvent()) {
				MAIN_BUS.callNextEvent();
			} else {
				MAIN_BUS.addEvent(new MainLoopEvent());
			}
		}
	}

	private static void init() {
		long seed = seedRandom();
		OUT.outputLine("Commencing world creation with seed " + seed + "...");

		Room startingRoom = new Room("Starting room", "You are in a test room, full of bugs.");

		Human player = new Human("Player", "A cretin is standing here.", startingRoom);
		player.addComponent(new EyesComponent());
		player.addComponent(new PlayerControlledComponent());

		OUT.outputLine("World creation complete.");
	}

	private static long seedRandom() {
		OUT.outputLine("Enter seed (leave blank for random, the intended experience):");
		long seed;
		List<String> input = IN.input();
		try {
			seed = Long.parseLong(input.get(0));
		}
		catch (NumberFormatException e) {
			if (input.get(0).equals("")) {
				seed = new Random().nextLong();
			} else {
				seed = input.hashCode();
			}
		}
		random_for_world_generation = new Random(seed);
		return seed;
	}

	/* 
	 * loop, once everything has decided upon a course of action, then move according to priority. 
	 * handle multi-move actions
	 * */
}
