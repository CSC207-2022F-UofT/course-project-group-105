package com.mg105.use_cases.inventory;

import com.mg105.entities.Item;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.MegaPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.utils.ItemConstants;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

/**
 * This class's sole responsibility is to create new items.
 * This is done by using the simple factory design pattern
 */

public class ItemFactory {

    /**
     * Returns a new instance of an item given the item's name
     *
     * @param itemName the name of the item to make a new instance of
     * @return the instance of the new item
     * @throws NoSuchElementException if no item of the name itemName can be created
     */

    public @NotNull Item createItem(@NotNull String itemName) throws NoSuchElementException {
        switch (itemName) {
            case ItemConstants.HEALTH_POTION_NAME:
                return new HealthPotion();
            case ItemConstants.UPGRADE_TOKEN_NAME:
                return new UpgradeToken();
            case ItemConstants.MEGA_POTION_NAME:
                return new MegaPotion();
        }
        throw new NoSuchElementException("No item of this type exists");
    }
}
