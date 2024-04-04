package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.MutableDataStoringComponent;
import org.gamenet.dkienenb.textgamecomponents.Outputer;

public class ActorFeedbackComponent extends MutableDataStoringComponent<Outputer> {

    public void setOut(Outputer out) {
        setValue(out);
    }

    public void sendFeedback(String feedback) {
        Outputer value = getValue();
        if (value != null) {
            value.outputLine(feedback);
        }
    }
}
