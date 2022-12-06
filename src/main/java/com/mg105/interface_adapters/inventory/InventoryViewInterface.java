package com.mg105.interface_adapters.inventory;

import com.mg105.interface_adapters.Alert;

/**
 * The interface defines all the functions that should be called to update the ui to add or
 * modify the information of the Inventory
 */

public interface InventoryViewInterface extends Alert {

    /**
     * Removes the item with the given name of the Inventory ui
     *
     * @param name the name of the item to be removed
     */
    void removeItemView(String name);

    /**
     * Add this item and its details to the Inventory ui
     *
     * @param name        the name of the item to add
     * @param description the description of the item to add
     * @param isUsable    true iff the item can be used by a user
     * @param quantity    the number of items of this type in the Inventory
     */
    void addItemView(String name, String description, boolean isUsable, String quantity);

    /**
     * Updates the Inventory ui based on changes to this items status
     *
     * @param name        the name of the item to change details about
     * @param description the description of the item
     * @param isUsable    true iff the item is usable
     * @param quantity    the number of items of this type in the Inventory
     */
    void changeItemView(String name, String description, boolean isUsable, String quantity);

}
