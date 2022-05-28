package org.gamenet.dkienenb.interactivefiction.game1;

import org.gamenet.dkienenb.bundle.Bundle;
import org.gamenet.dkienenb.component.ImmutableDataStoringComponent;

public class ContainableComponent extends ImmutableDataStoringComponent<Bundle<Integer, Integer>> {

	public ContainableComponent(int size, int weight) {
		super(new Bundle<>(size, weight));
	}

	public int getSize() {
		return getValue().first();
	}

	public int getWeight() {
		return getValue().second();
	}
}
