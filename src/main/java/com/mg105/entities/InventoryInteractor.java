package com.mg105.entities;

public class InventoryInteractor {

    public InventoryInteractor() {

    }

    public boolean addItem(Item item) {
        /**
         *
         * Adds an item to the inventory. An item is not added to the inventory iff the inventory is full.
         *
         * @param item The item to be added to the inventory
         * @return whether of not the item was added to the inventory
         */
        boolean success = GameState.inventory.addItem(item);
        return success;
    }

    public boolean isInventoryFull() {
        /**
         * Returns if the inventory is full. A full inventory cannot have any items added to it.
         *
         * @return if the inventory is full.
         */
        return GameState.inventory.isFull();
    }

    public boolean removeItem(String itemName) {
        /**
         * Removes ONE instance of an item from the inventory that has the same itemName.
         *
         * @param itemName The name of item that should be removed
         * @return if an item was removed from the inventory
         * @see Item
         */
        return GameState.inventory.removeItem();
    }


}
