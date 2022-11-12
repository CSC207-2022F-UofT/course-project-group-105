package com.mg105.usecase.Inventory;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.outputds.ItemDetails;
import com.mg105.presenterinterfaces.InventoryPresenterInterface;
import com.mg105.utils.ItemConstants;
import com.mg105.utils.PartyConstants;

import java.util.NoSuchElementException;

public class InventoryInteractor {

    /**
     * This class is the only class that should directly interact with the inventory
     * <p>
     * It is responsible for all things related to get information from the inventory and updating the
     * inventory.
     */

    private final GameState gameState;

    private final ItemFactory itemFactory = new ItemFactory();

    private final InventoryPresenterInterface response;

    public InventoryInteractor(GameState gamestate, InventoryPresenterInterface response) {
        this.gameState = gamestate;
        this.response = response;
    }

    /**
     * Returns an object that represents if an item was successfully added
     *
     * @param itemName the name of the item to be added
     * @return the state of the item in the inventory and a description on if the item was added
     * @see InventoryPresenterInterface
     */

    public String[] addItem(String itemName) {
        if (!itemKindExists(itemName)) {
            return response.addItemView(false, itemName, getItemCount(itemName));
        }

        boolean addedItem = gameState.getInventory().addItem(itemFactory.createItem(itemName));

        if (!addedItem) {
            return response.addItemView(false, itemName, getItemCount(itemName));
        }

        return response.addItemView(true, itemName, getItemCount(itemName));
    }

    /**
     * Returns an object that represents if the item was removed
     *
     * @param itemName the name of the item to be removed
     * @return the state of the item in the inventory and a description on if the item was removed
     * @see InventoryPresenterInterface
     */

    public String[] removeItem(String itemName) {

        if (!gameState.getInventory().has(itemName)) {
            return response.removeItemView(false, itemName, getItemCount(itemName));
        }

        gameState.getInventory().removeItem(itemName);

        return response.removeItemView(true, itemName, getItemCount(itemName));

    }

    /**
     * Returns an object that represents if an item was used
     *
     * @param itemName      the name of the item to be used
     * @param characterName the name of character the item should be used on
     * @return the state of the item in the inventory and a description on if the item was used
     * @see InventoryPresenterInterface
     */

    public String[] useItem(String itemName, String characterName) {

        if (!(isItemUsable(itemName) && inParty(characterName) && gameState.getInventory().has(itemName))) {
            return response.useItemView(false, itemName, characterName, getItemCount(itemName));
        }
        BattleCharacter partyMember = gameState.getPartyMember(characterName);
        gameState.getInventory().useItem(partyMember, itemName);

        return response.useItemView(true, itemName, characterName, getItemCount(itemName));
    }

    /**
     * Returns an object that represents the state of the inventory
     *
     * @return an array of arrays of strings that state of each item type in the inventory
     * @see InventoryPresenterInterface
     */
    public String[][] getInventoryDetails() {
        Inventory inventory = gameState.getInventory();
        ItemDetails[] output = new ItemDetails[ItemConstants.ALL_ITEM_NAMES.length];
        for (int i = 0; i < ItemConstants.ALL_ITEM_NAMES.length; i++) {
            String itemName = ItemConstants.ALL_ITEM_NAMES[i];
            String itemDescription = ItemConstants.ALL_ITEM_DESCRIPTIONS[i];
            int numOfItems = getItemCount(itemName);
            boolean isUsable = isItemUsable(itemName);

            output[i] = new ItemDetails(itemName, itemDescription, numOfItems, isUsable);
        }

        return response.inventoryDetailsView(output);

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
        return gameState.getInventory().numberOfItems(itemName);
    }

    private boolean inParty(String characterName) {
        for (String name : PartyConstants.ALL_PARTY_MEMBER_NAMES) {
            if (characterName.equals(name)) {
                return true;
            }
        }

        return false;
    }

    private String getItemDescription(String itemName) throws NoSuchElementException {
        for (int i = 0; i < ItemConstants.ALL_ITEM_NAMES.length; i++) {
            if (ItemConstants.ALL_ITEM_NAMES[i].equals(itemName)) {
                return ItemConstants.ALL_ITEM_DESCRIPTIONS[i];
            }
        }

        throw new NoSuchElementException("Given Item Name does not exist");
    }

    private boolean isItemUsable(String itemName) {
        for (String usableItemName : ItemConstants.ALL_USABLE_ITEM_NAMES) {
            if (usableItemName.equals(itemName)) {
                return true;
            }
        }

        return false;
    }
}
