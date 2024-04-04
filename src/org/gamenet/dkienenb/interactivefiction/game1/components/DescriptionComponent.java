package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.MutableDataStoringComponent;

public class DescriptionComponent extends MutableDataStoringComponent<String> {

	public DescriptionComponent(String description) {
		setDescription(description);
	}

	public String getDescription() {
		return getValue();
	}

	public void setDescription(String name) {
		setValue(name);
	}

}
