package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.MutableDataStoringComponent;

public class InternalDescriptionComponent extends MutableDataStoringComponent<String> {

	public InternalDescriptionComponent(String description) {
		setDescription(description);
	}

	public String getDescription() {
		return getValue();
	}

	public void setDescription(String name) {
		setValue(name);
	}

}
