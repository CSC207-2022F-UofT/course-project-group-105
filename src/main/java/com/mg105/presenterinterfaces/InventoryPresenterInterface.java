package com.mg105.presenterinterfaces;

import com.mg105.outputds.ItemDetails;

public interface InventoryPresenterInterface {

    /**
     * This interface should be implemented by the InventoryPresenter class
     *
     * It represents the data that should be displayed to the user
     */

    /**
     * Returns an object that represents the action of adding the item
     *
     * @param isSuccessful if an item of itemName was added to the inventory
     * @param itemName     the name of item that was attempted to be added to the inventory
     * @param itemCount    the total number of itemName after the item was added
     * @return the strings to be displayed that represent the attempted item added
     */
    String[] addItemView(boolean isSuccessful, String itemName, int itemCount);

    /**
     * Returns an object that represents the action of removing the item
     *
     * @param isSuccessful if an item of itemName was removed from the inventory
     * @param itemName     the name of item
     * @param itemCount    the number of itemName after attempting to remove
     * @return the strings to be displayed that represent the attempted item removed
     */
    String[] removeItemView(boolean isSuccessful, String itemName, int itemCount);

    /**
     * Returns an object that represents the action of using the item on a character
     *
     * @param isSuccessful  if an item of itemName was removed from the inventory
     * @param itemName      the name of item
     * @param characterName the name of character the item was attempted to be used on
     * @param itemCount     the number of itemName after attempting to use it
     * @return the strings to be displayed that represent the attempted use of the item
     */
    String[] useItemView(boolean isSuccessful, String itemName, String characterName, int itemCount);

    /**
     * Returns an object representing the state of every item type in the inventory
     *
     * @param allItemsDetails a list of object that represent a state of single item type in the inventory
     * @return array of arrays of string where each array represents the state of single item type in the inventory
     */
    String[][] inventoryDetailsView(ItemDetails[] allItemsDetails);
}
