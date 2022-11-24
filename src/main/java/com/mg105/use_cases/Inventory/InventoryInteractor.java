package com.mg105.use_cases.Inventory;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.outputds.ItemDetails;
import com.mg105.presenter_interfaces.InventoryPresenterInterface;
import com.mg105.entities.items.utils.ItemConstants;
import com.mg105.entities.items.utils.PartyConstants;

/**
 * This class is the only class that should directly interact with the inventory
 * <p>
 * It is responsible for all things related to get information from the inventory and updating the
 * inventory.
 */

public class InventoryInteractor {

    private final GameState state;

    private final ItemFactory itemFactory = new ItemFactory();

    private final InventoryPresenterInterface response;

    public InventoryInteractor(GameState gamestate, InventoryPresenterInterface response) {
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

    public void addItem(String itemName) {
        if (!itemKindExists(itemName)) {
            response.addItemView(false, getItemDetails(itemName));
            return;
        }

        boolean addedItem = state.getInventory().addItem(itemFactory.createItem(itemName));

        response.addItemView(addedItem, getItemDetails(itemName));
    }

    /**
     * Attempts to remove an item of itemName to the inventory and sends action data to the
     * appropriate class
     *
     * @param itemName the name of the item to be removed
     * @see InventoryPresenterInterface
     */

    public void removeItem(String itemName) {

        boolean isRemoved = state.getInventory().removeItem(itemName);

        response.removeItemView(isRemoved, getItemDetails(itemName));

    }

    /**
     * Attempts to use an item of itemName and sends action data to the
     * appropriate class
     *
     * @param itemName      the name of the item to be used
     * @param characterName the name of character the item should be used on
     * @see InventoryPresenterInterface
     */

    public void useItem(String itemName, String characterName) {

        if (!(inParty(characterName))) {
            response.useItemView(false, characterName, getItemDetails(itemName));
            return;
        }
        BattleCharacter partyMember = state.getPartyMember(characterName);
        boolean isUsed = state.getInventory().useItem(partyMember, itemName);

        response.useItemView(isUsed, characterName, getItemDetails(itemName));
    }

    /**
     * Collects data the represents the state of the inventory (i.e. the items in it and their details) and sends action
     * data to the appropriate class
     *
     * @see InventoryPresenterInterface
     */
    public void getInventoryDetails() {
        ItemDetails[] output = new ItemDetails[ItemConstants.ALL_ITEM_NAMES.length];
        for (int i = 0; i < ItemConstants.ALL_ITEM_NAMES.length; i++) {
            String itemName = ItemConstants.ALL_ITEM_NAMES[i];
            String itemDescription = ItemConstants.ALL_ITEM_DESCRIPTIONS[i];
            int numOfItems = getItemCount(itemName);
            boolean isUsable = isItemUsable(itemName);

            output[i] = new ItemDetails(itemName, itemDescription, numOfItems, isUsable);
        }

        response.inventoryDetailsView(output);

    }

    private ItemDetails getItemDetails(String itemName) {
        return new ItemDetails(itemName, getItemDescription(itemName), getItemCount(itemName), isItemUsable(itemName));
    }

    private Boolean itemKindExists(String itemName) {
        for (String usableItemName : ItemConstants.ALL_ITEM_NAMES) {
            if (usableItemName.equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    private int getItemCount(String itemName) {
        return state.getInventory().numberOfItems(itemName);
    }

    private boolean inParty(String characterName) {
        for (String name : PartyConstants.ALL_PARTY_MEMBER_NAMES) {
            if (characterName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isItemUsable(String itemName) {
        for (String usableItemName : ItemConstants.ALL_USABLE_ITEM_NAMES) {
            if (usableItemName.equals(itemName)) {
                return true;
            }
        }

        return false;
    }

    private String getItemDescription(String itemName) {
        for (int i = 0; i < ItemConstants.ALL_ITEM_NAMES.length; i++) {
            if (ItemConstants.ALL_ITEM_NAMES[i].equals(itemName)) {
                return ItemConstants.ALL_ITEM_DESCRIPTIONS[i];
            }
        }

        // should only be called iff the itemName does not exist
        return "";
    }
}
