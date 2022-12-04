package com.mg105.use_cases.inventory;

import org.jetbrains.annotations.NotNull;

/**
 * This interface defines the input structure to make calls that change the state of the inventory
 */
public interface InventoryInteractorInputInterface {

    /**
     * Attempts to remove an item of itemName to the inventory
     *
     * @param itemName the name of the item to be removed
     */
    void removeItem(@NotNull String itemName);

    /**
     * Attempts to use an item of itemName on a character
     *
     * @param itemName      the name of the item to be used
     * @param characterName the name of character the item should be used on
     */

    void useItem(@NotNull String itemName, @NotNull String characterName);

    /**
     * Collects and sends data the represents the state of the inventory
     */
    void getInventoryDetails();

    /**
     * Removes all items from the inventory
     */
    void removeInventory();
}
