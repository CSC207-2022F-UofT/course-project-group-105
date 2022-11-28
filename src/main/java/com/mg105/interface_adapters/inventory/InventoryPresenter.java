package com.mg105.interface_adapters.inventory;

import com.mg105.outputds.ItemDetails;
import com.mg105.use_cases.Inventory.InventoryPresenterInterface;
import org.jetbrains.annotations.NotNull;

/**
 * A class whose purpose is to update the view with the current state of the inventory and update it based on
 * a user's actions
 */

public class InventoryPresenter implements InventoryPresenterInterface {

    InventoryViewInterface display;

    /**
     * Sets the view of the of this presenter
     * This should be called right after the presenter is created
     *
     * @param view
     */
    public void setView(@NotNull InventoryViewInterface view) {
        this.display = view;
    }

    /**
     * Send the information to update the exact state of the inventory to the view
     * Should only be called right when the view is displayed
     *
     * @param allItemsDetails a list of object that represent a state of single item type in the inventory
     */
    @Override
    public void inventoryDetails(ItemDetails[] allItemsDetails) {
        for (ItemDetails itemDetails : allItemsDetails) {
            if (itemDetails.getCount() > 0) {
                this.display.addItemView(itemDetails.getName(), itemDetails.getDescription(), itemDetails.isUsable(),
                    String.valueOf(itemDetails.getCount()));
            }

        }

    }

    /**
     * Sends an alert the view about the status of the potentially added item
     *
     * @param isSuccessful if an item of itemName was added to the inventory
     * @param itemDetails  the state of the item that was potentially used
     * @see ItemDetails
     * @see InventoryViewInterface
     */
    @Override
    public void addItem(boolean isSuccessful, ItemDetails itemDetails) {
        if (isSuccessful) {
            this.display.alert("Successfully added a " + itemDetails.getName() + ".");
            return;
        }

        this.display.alert(itemDetails.getName() +
            " could not be added. The inventory might be full, try removing an item.");
    }

    /**
     * Sends an alert the view about the status of the potentially removed item.
     * Also updates the view to display the appropriate amount of items left fo that type
     *
     * @param isSuccessful if an item of itemName was removed from the inventory
     * @param itemDetails  the state of the item that was potentially used
     * @see ItemDetails
     * @see InventoryViewInterface
     */
    @Override
    public void removeItem(boolean isSuccessful, ItemDetails itemDetails) {

        if (!isSuccessful) {
            this.display.alert("Could not remove a " + itemDetails.getDescription() + ".");
            return;

        }

        this.display.alert("Successfully removed a " + itemDetails.getName() + ".");

        if (itemDetails.getCount() == 0) {

            this.display.removeItemView(itemDetails.getName());
            return;

        }

        this.display.changeItemView(itemDetails.getName(), itemDetails.getDescription(), itemDetails.isUsable(),
            String.valueOf(itemDetails.getCount()));

    }

    /**
     * Sends an alert the view about the status of the attempted use of an item
     * Also updates the view to display the appropriate amount of items left fo that type
     *
     * @param isSuccessful if an item of itemName was removed from the inventory
     * @param itemDetails  the state of the item that was potentially used
     * @see ItemDetails
     * @see InventoryViewInterface
     */
    @Override
    public void useItem(boolean isSuccessful, String characterName, ItemDetails itemDetails) {
        if (!isSuccessful) {
            this.display.alert("Could not use " + itemDetails.getName() + " on " + characterName + "!");
            return;
        }

        this.display.alert("Successfully used " + itemDetails.getName() + " on " + characterName + "!");

        if (itemDetails.getCount() == 0) {

            this.display.removeItemView(itemDetails.getName());
            return;

        }

        this.display.changeItemView(itemDetails.getName(), itemDetails.getDescription(), itemDetails.isUsable(),
            String.valueOf(itemDetails.getCount()));

    }
}
