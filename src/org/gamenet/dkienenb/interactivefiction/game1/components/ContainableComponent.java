package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.bundle.Bundle;
import org.gamenet.dkienenb.component.Component;
import org.gamenet.dkienenb.component.ImmutableDataStoringComponent;

import java.util.List;

public class ContainableComponent extends ImmutableDataStoringComponent<Bundle<Integer, Integer>> {

	public static class Constants {
		public static final int HUMAN_WEIGHT = 100;
	}

	public ContainableComponent(int weight) {
		super(new Bundle<>(1, weight));
	}

	@Override
	public List<Class<? extends Component>> getDependencies() {
		List<Class<? extends Component>> dependencies = super.getDependencies();
		dependencies.add(LocationComponent.class);
		return dependencies;
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
