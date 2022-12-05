package com.mg105.use_cases.inventory;

import com.mg105.entities.GameState;
import com.mg105.use_cases.outputds.ItemDetails;
import com.mg105.utils.ItemConstants;
import com.mg105.utils.PartyConstants;
import org.jetbrains.annotations.NotNull;

/**
 * This class is the only class that should directly interact with the inventory
 * <p>
 * It is responsible for all things related to get information from the inventory and updating the
 * inventory.
 */

public class InventoryInteractor implements InventoryInteractorInputInterface {

    @NotNull private final GameState state;

    @NotNull private final ItemFactory itemFactory = new ItemFactory();

    @NotNull private final InventoryPresenterInterface response;

    /**
     * Creates a new instance of InventoryInteractor
     * @param gamestate the current state of the game
     * @param response an object the represents what data to perhaps output
     */

    public InventoryInteractor(@NotNull GameState gamestate, @NotNull InventoryPresenterInterface response) {
        this.state = gamestate;
        this.response = response;
    }

    /**
     * Attempts to add an item to the inventory based on the itemName and sends action data to the
     * appropriate class
     *
     * @param itemName the name of the item to be added
     * @see InventoryPresenterInterface
     */

    public void addItem(@NotNull String itemName) {
        if (!itemKindExists(itemName)) {
            response.addItem(false, getItemDetails(itemName));
            return;
        }

        // This uses the simple factory design patterns

        boolean addedItem = state.getInventory().addItem(itemFactory.createItem(itemName));

        response.addItem(addedItem, getItemDetails(itemName));
    }

    /**
     * Attempts to remove an item of itemName to the inventory and sends action data to the
     * appropriate class
     *
     * @param itemName the name of the item to be removed
     * @see InventoryPresenterInterface
     */

    @Override
    public void removeItem(@NotNull String itemName) {

        boolean isRemoved = state.getInventory().removeItem(itemName);

        response.removeItem(isRemoved, getItemDetails(itemName));

    }

    /**
     * Attempts to use an item of itemName and sends action data to the
     * appropriate class
     *
     * @param itemName      the name of the item to be used
     * @param characterName the name of character the item should be used on
     * @see InventoryPresenterInterface
     */

    @Override
    public void useItem(@NotNull String itemName, @NotNull String characterName) {

        if (!(inParty(characterName)) || state.hasCurrEncounter()) {
            response.useItem(false, characterName, getItemDetails(itemName));
            return;
        }
        boolean isUsed = state.getInventory().useItem(state, itemName,characterName);

        response.useItem(isUsed, characterName, getItemDetails(itemName));
    }

    /**
     * Collects data the represents the state of the inventory (i.e. the items in it and their details) and sends action
     * data to the appropriate class
     *
     * @see InventoryPresenterInterface
     */
    @Override
    public void getInventoryDetails() {
        ItemDetails[] output = new ItemDetails[ItemConstants.ALL_ITEM_NAMES.length];
        for (int i = 0; i < ItemConstants.ALL_ITEM_NAMES.length; i++) {
            String itemName = ItemConstants.ALL_ITEM_NAMES[i];
            String itemDescription = ItemConstants.ALL_ITEM_DESCRIPTIONS[i];
            int numOfItems = getItemCount(itemName);
            boolean isUsable = isItemUsable(itemName);

            output[i] = new ItemDetails(itemName, itemDescription, numOfItems, isUsable);
        }

        response.inventoryDetails(output);

    }

    /**
     * Returns the limit of the inventory
     * The limit of the inventory is the maximum possible number of items that could be placed in it
     * @return and integer representing the limit of the inventory
     */

    public int getInventoryLimit(){
        return state.getInventory().limit();
    }

    /**
     * Returns the number of items currently in the inventory
     * @return the number of items currently in the inventory
     */

    public int getNumOfItem(){
        return state.getInventory().numberOfItems();
    }

    /**
     * Removes all items from the inventory
     */
    @Override
    public void removeInventory()
    {
        this.state.getInventory().removeAll();
    }

    private @NotNull ItemDetails getItemDetails(@NotNull String itemName) {
        return new ItemDetails(itemName, getItemDescription(itemName), getItemCount(itemName), isItemUsable(itemName));
    }

    private Boolean itemKindExists(@NotNull String itemName) {
        for (String usableItemName : ItemConstants.ALL_ITEM_NAMES) {
            if (usableItemName.equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    private int getItemCount(@NotNull String itemName) {
        return state.getInventory().numberOfItems(itemName);
    }

    private boolean inParty(@NotNull String characterName) {
        for (String name : PartyConstants.ALL_PARTY_MEMBER_NAMES) {
            if (characterName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isItemUsable(@NotNull String itemName) {
        for (String usableItemName : ItemConstants.ALL_USABLE_ITEM_NAMES) {
            if (usableItemName.equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    private @NotNull String getItemDescription(@NotNull String itemName) {
        for (int i = 0; i < ItemConstants.ALL_ITEM_NAMES.length; i++) {
            if (ItemConstants.ALL_ITEM_NAMES[i].equals(itemName)) {
                return ItemConstants.ALL_ITEM_DESCRIPTIONS[i];
            }
        }

        // should only be called iff the itemName does not exist
        return "N/A";
    }
}

