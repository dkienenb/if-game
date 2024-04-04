package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.ImmutableDataStoringComponent;

public class NameComponent extends ImmutableDataStoringComponent<String> {

	public NameComponent(String value) {
		super(value);
	}

	public String getName() {
		return getValue();
	}
}
