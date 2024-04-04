package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.MutableDataStoringComponent;

public class ExternalDescriptionComponent extends MutableDataStoringComponent<String> {

	public ExternalDescriptionComponent(String description) {
		setDescription(description);
	}

	public String getDescription() {
		return getValue();
	}

	public void setDescription(String name) {
		setValue(name);
	}

}
