package com.mg105.entities;

public class InventoryInteractor {

    public InventoryInteractor() {

    }

    /**
     *
     * Adds an item to the inventory. An item is not added to the inventory iff the inventory is full.
     *
     * @param item The item to be added to the inventory
     * @return whether the item was added to the inventory
     */
    public static boolean addItem(Item item) {
        return GameState.inventory.addItem(item);
        // unsure how to fix these static issues because I don't know what GameState's going to look like
    }

    /**
     * Returns if the inventory is full. A full inventory cannot have any items added to it.
     *
     * @return if the inventory is full.
     */
    public static boolean isInventoryFull() {
        return GameState.inventory.isFull();
    }

    /**
     * Removes ONE instance of an item from the inventory that has the same itemName.
     *
     * @param itemName The name of item that should be removed
     * @return if an item was removed from the inventory
     * @see Item
     */
    public boolean removeItem(String itemName) {
        return GameState.inventory.removeItem();
    }


}
