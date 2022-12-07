package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * An inventory is a class that manages items
 */
public class Inventory {
    private final static int INVENTORY_LIMIT = 10;
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Returns the maximum amount items allowed in the inventory
     *
     * @return the maximum number of items the inventory can hold
     */
    public int limit() {
        return INVENTORY_LIMIT;
    }

    /**
     * Returns the number of items in the inventory currently
     *
     * @return the number of items in the inventory
     */
    public int numberOfItems() {
        return this.items.size();
    }

    /**
     * Returns the total number of items in the inventory that have the itemName as there name
     *
     * @param itemName The name of items that should be counted
     * @return the number of items in inventory with that name
     * @see Item
     */
    public int numberOfItems(@NotNull String itemName) {
        int count = 0;
        for (Item item : this.items) {
            if (item.getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns if the inventory is full. A full inventory cannot have any items added to ti.
     *
     * @return if the inventory is full.
     */
    public boolean isFull() {
        return numberOfItems() >= INVENTORY_LIMIT;
    }

    /**
     * Adds an item to the inventory. An item is not added to the inventory iff the inventory is full.
     *
     * @param item The item to be added to the inventory
     * @return whether of not the item was added to the inventory
     */

    public boolean addItem(@NotNull Item item) {
        if (isFull()) {
            return false;
        }
        this.items.add(item);

        return true;
    }

    /**
     * Removes ONE instance of an item from the inventory that has the same itemName.
     *
     * @param itemName The name of item that should be removed
     * @return true iff an item with itemName was removed from the inventory
     * @see Item
     */
    public boolean removeItem(@NotNull String itemName) {
        for (int i = 0; i < numberOfItems(); i++) {
            if (this.items.get(i).getName().equals(itemName)) {
                this.items.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Removes all the items from the inventory.
     */
    public void removeAll() {
        for (int i = numberOfItems() - 1; i >=0; i--) {
            this.items.remove(i);
        }
    }

    /**
     * Uses the item on the given character
     *
     * @param state The current state of the game
     * @param itemName  The name of the item to use
     * @param characterName the character to use the item on
     * @return true iff the item with a name of itemName was used
     * @see Item
     * @see Consumable
     */

    public boolean useItem(@NotNull GameState state, @NotNull String itemName, @NotNull String characterName) {
        for (int i = 0; i < numberOfItems(); i++) {
            Item item = this.items.get(i);

            if (item.getName().equals(itemName)) {

                if (item instanceof Consumable consumableItem) {
                    consumableItem.consume(state, characterName);
                    this.items.remove(i);
                    return true;

                } else {

                    return false;

                }
            }
        }

        return false;

    }

    /**
     * Returns if there is at LEAST one item in inventory with itemName
     *
     * @param itemName the name of item that needs to be searched for
     * @return if there is an item in the inventory with a name that is itemName
     */
    public boolean has(@NotNull String itemName) {
        for (Item item : this.items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}
