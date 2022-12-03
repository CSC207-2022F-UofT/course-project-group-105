package com.mg105.use_cases.inventory;

import com.mg105.use_cases.outputds.ItemDetails;

/**
 * This interface should be implemented by the InventoryPresenter class
 * <p>
 * It represents the data that should be formated and displayed by the view
 */

public interface InventoryPresenterInterface {

    /**
     * Sends a message to update the view about an added item
     *
     * @param isSuccessful if an item of itemName was added to the inventory
     * @param itemDetails  the state of the item that was potentially used
     * @see ItemDetails
     */
    void addItem(boolean isSuccessful, ItemDetails itemDetails);

    /**
     * Sends a message to update the view about a removed item
     *
     * @param isSuccessful if an item of itemName was removed from the inventory
     * @param itemDetails  the state of the item that was potentially used
     * @see ItemDetails
     */
    void removeItem(boolean isSuccessful, ItemDetails itemDetails);

    /**
     * Sends a message to update the view about a used item
     *
     * @param isSuccessful  if an item of itemName was used in the inventory
     * @param characterName the name of character the item was attempted to be used on
     * @param itemDetails   the state of the item that was potentially used
     * @see ItemDetails
     */
    void useItem(boolean isSuccessful, String characterName, ItemDetails itemDetails);

    /**
     * Sends a message to the view about the state of every item type in the inventory
     *
     * @param allItemsDetails a list of object that represent a state of single item type in the inventory
     * @see ItemDetails
     */
    void inventoryDetails(ItemDetails[] allItemsDetails);
}
