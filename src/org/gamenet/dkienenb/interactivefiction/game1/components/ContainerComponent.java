package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.component.ListStoringComponent;

public class ContainerComponent extends ListStoringComponent<ComponentedObject> {

	public static final int UNLIMITED = -143;

	public static enum ItemAcceptanceState {
		YES,
		TOO_HEAVY,
		TOO_BIG;
	}

	/**
	 * negative number = no limits
	 */
	private final int weightCapacity;
	private final int spaceCapacity;


	public ContainerComponent(int weightCapacity, int spaceCapacity) {
		this.weightCapacity = weightCapacity;
		this.spaceCapacity = spaceCapacity;
	}

	public boolean hasWeightLimit() {
		return weightCapacity >= 0;
	}

	public boolean hasSizeLimit() {
		return spaceCapacity >= 0;
	}

	public int availibleSpace() {
		if (hasSizeLimit()) {
			int usedSpace = usedSpace();
			return spaceCapacity - usedSpace;
		}
		return Integer.MAX_VALUE;
	}

	public int usedSpace() {
		return stream().mapToInt(object -> object.getComponent(ContainableComponent.class).getSize()).sum();
	}

	public int availibleWeight() {
		if (hasSizeLimit()) {
			int usedWeight = usedWeight();
			return weightCapacity - usedWeight;
		}
		return Integer.MAX_VALUE;
	}

	public int usedWeight() {
		return stream().mapToInt(object -> object.getComponent(ContainableComponent.class).getWeight()).sum();
	}

	public ItemAcceptanceState canAcceptItem(ComponentedObject item) {
		if (hasSizeLimit()) {
			if (availibleSpace() < item.getComponent(ContainableComponent.class).getSize()) {
				return ItemAcceptanceState.TOO_BIG;
			}
		}
		if (hasWeightLimit()) {
			if (availibleSpace() < item.getComponent(ContainableComponent.class).getWeight()) {
				return ItemAcceptanceState.TOO_HEAVY;
			}			
		}
		return ItemAcceptanceState.YES;
	}

	public ItemAcceptanceState addItem(ComponentedObject item) {
		ItemAcceptanceState state = canAcceptItem(item);
		if (state.equals(ItemAcceptanceState.YES)) {
			forceAddItem(item);
		}
		return state;
	}

	public void forceAddItem(ComponentedObject item) {
		add(item);
	}

}
