package org.gamenet.dkienenb.interactivefiction.game1.grammer;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.interactivefiction.game1.Main;
import org.gamenet.dkienenb.interactivefiction.game1.actions.Action;
import org.gamenet.dkienenb.interactivefiction.game1.actions.ExamineAction;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private ComponentedObject actor;
    private Action action;
    private List<ComponentedObject> directObjects;
    private List<ComponentedObject> indirectObjects;

    public void loadNext(ComponentedObject actor) {
        this.actor = actor;
        boolean valid = false;
        while (!valid) {
            directObjects = new ArrayList<>();
            indirectObjects = new ArrayList<>();
            List<String> input = Main.IN.input();
            String command = input.get(0);
            if (command.equals("quit")) {
                Main.killProgram();
                break;
            } else if (command.equals("examine") || command.equals("x")) {
                action = new ExamineAction();
            } else {
                Main.OUT.outputLine("I don't know what that means.");
                continue;
            }
            for (int index = 1; index < input.size(); index++) {
                String next = input.get(index);
                ComponentedObject foundThing = parseNoun(next);
                if (foundThing == null) {
                    Main.OUT.outputLine("There is no such " + next + " here.");
                    valid = true;
                    break;
                } else {
                    directObjects.add(foundThing);
                }
            }
            if (action.canBeDone(actor, directObjects, indirectObjects)) {
                valid = true;
            }
        }
    }

    private ComponentedObject parseNoun(String next) {
        if (next.equals("me")) {
            return actor;
        }
        return null;
    }

    public ComponentedObject getActor() {
        return actor;
    }

    public Action getAction() {
        return action;
    }

    public List<ComponentedObject> getDirectObjects() {
        return directObjects;
    }

    public List<ComponentedObject> getIndirectObjects() {
        return indirectObjects;
    }
}
