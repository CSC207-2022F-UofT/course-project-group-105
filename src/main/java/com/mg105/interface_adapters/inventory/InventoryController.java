package com.mg105.interface_adapters.inventory;

import com.mg105.use_cases.inventory.InventoryInteractor;
import com.mg105.use_cases.inventory.InventoryInteractorInputInterface;
import org.jetbrains.annotations.NotNull;

/**
 * A class that serves the UI and allows users to make the desired changes to the Inventory
 */

public class InventoryController {

    @NotNull
    private final InventoryInteractorInputInterface interactor;


    /**
     * Creates a new instance of inventory controller
     *
     * @param interactor an object that interacts directly with the inventory
     */

    public InventoryController(@NotNull InventoryInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Attempts to the remove the item from the inventory.
     * Item should exist in the inventory before calling this.
     *
     * @param itemName the item to remove from the inventory
     * @see InventoryInteractor
     */

    public void removeItem(@NotNull String itemName) {
        interactor.removeItem(itemName);
    }

    /**
     * Attempts to use an item in the inventory on the specified character
     * Item should exist in the inventory before calling this.
     *
     * @param itemName      the item to use
     * @param characterName the character to use the item on
     * @see InventoryInteractor
     */
    public void useItem(@NotNull String itemName, @NotNull String characterName) {
        interactor.useItem(itemName, characterName);
    }

    /**
     * Makes a call that will send the necessary details to the Inventory ui about the inventory.
     *
     * @see InventoryInteractor
     */

    public void getInventoryDetails() {
        interactor.getInventoryDetails();
    }
}
