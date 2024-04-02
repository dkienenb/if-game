package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.bundle.Bundle;
import org.gamenet.dkienenb.component.ImmutableDataStoringComponent;

public class ContainableComponent extends ImmutableDataStoringComponent<Bundle<Integer, Integer>> {

	public ContainableComponent(int weight) {
		super(new Bundle<>(1, weight));
	}

	public ContainableComponent(int size, int weight) {
		super(new Bundle<>(size, weight));
	}

	public int getSize() {
		return getValue().first();
	}

	public int getWeight() {
		int containedWeight = 0;
		if (getAttached().hasComponent(ContainerComponent.class)) {
			containedWeight = getAttached().getComponent(ContainerComponent.class).usedWeight();
		}
		return getValue().second() + containedWeight;
	}

}
