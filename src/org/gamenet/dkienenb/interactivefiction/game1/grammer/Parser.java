package org.gamenet.dkienenb.interactivefiction.game1.grammer;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.interactivefiction.game1.Main;
import org.gamenet.dkienenb.interactivefiction.game1.actions.Action;

import java.util.List;

public class Parser {

    public void loadNext() {
        List<String> input = Main.IN.input();
        String command = input.get(0);
        if (command.equals("quit")) {
            Main.killProgram();
        }
    }

    public Action getAction() {
        return null;
    }

    public List<ComponentedObject> getDirectObjects() {
        return null;
    }

    public List<ComponentedObject> getIndirectObjects() {
        return null;
    }
}
