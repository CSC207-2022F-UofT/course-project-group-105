package com.mg105.use_cases.Inventory;

import com.mg105.entities.HealthPotion;
import com.mg105.entities.Item;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.utils.ItemConstants;

import java.util.NoSuchElementException;

/**
 * This classes sole responsibility is to create new items
 */

public class ItemFactory {

    /**
     * Returns a new instance of an item given the item's name
     *
     * @param itemName the name of the item to make a new instance of
     * @return the instance of the new item
     * @throws RuntimeException if no item of the name itemName can be created
     */

    public Item createItem(String itemName) throws NoSuchElementException {
        if (itemName.equals(ItemConstants.HEALTH_POTION_NAME)) {
            return new HealthPotion();
        } else if (itemName.equals(ItemConstants.UPGRADE_TOKEN_NAME)) {
            return new UpgradeToken();
        }

        throw new NoSuchElementException("No item of this type exists");
    }
}
