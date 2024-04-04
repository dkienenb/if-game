package org.gamenet.dkienenb.interactivefiction.game1.components;

import org.gamenet.dkienenb.component.ComponentedObject;
import org.gamenet.dkienenb.component.ListStoringComponent;

import java.util.stream.Stream;

public class ContainerComponent extends ListStoringComponent<ComponentedObject> {

	public static class Constants {
		public static final int HUMAN_STRENGTH = 100;
		public static final int HUMAN_CARRYING_LIMIT = 8;
		public static final int UNLIMITED = -143;
	}

	public enum ItemAcceptanceState {
		YES,
		TOO_HEAVY,
		TOO_BIG
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

	public Stream<ComponentedObject> streamItems() {
		return stream();
	}

	public int availableSpace() {
		if (hasSizeLimit()) {
			int usedSpace = usedSpace();
			return spaceCapacity - usedSpace;
		}
		return Integer.MAX_VALUE;
	}

	public int usedSpace() {
		return streamItems().mapToInt(object -> object.getComponent(ContainableComponent.class).getSize()).sum();
	}

	public int availableWeight() {
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
			if (availableSpace() < item.getComponent(ContainableComponent.class).getSize()) {
				return ItemAcceptanceState.TOO_BIG;
			}
		}
		if (hasWeightLimit()) {
			if (availableWeight() < item.getComponent(ContainableComponent.class).getWeight()) {
				return ItemAcceptanceState.TOO_HEAVY;
			}			
		}
		return ItemAcceptanceState.YES;
	}

	public boolean contains(ComponentedObject item) {
		return getValue().contains(item);
	}

	public ItemAcceptanceState addItem(ComponentedObject item) {
		ItemAcceptanceState state = canAcceptItem(item);
		if (state.equals(ItemAcceptanceState.YES)) {
			forceAddItem(item);
		}
		return state;
	}

	public void forceAddItem(ComponentedObject item) {
		getValue().add(item);
		LocationComponent locationComponent = item.getComponent(LocationComponent.class);
		if (!locationComponent.getLocation().equals(getAttached())) {
			locationComponent.setLocation(getAttached());
		}
	}
	public void forceRemoveItem(ComponentedObject item) {
		getValue().remove(item);
		LocationComponent locationComponent = item.getComponent(LocationComponent.class);
		if (locationComponent.getLocation() != null) {
			locationComponent.setLocation(null);
		}
	}

}
