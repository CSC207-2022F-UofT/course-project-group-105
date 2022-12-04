package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

/**
 * Consumable is an interface that should only be implemented by items that can be use on a character
 * <p>
 * Items that are consumable are items that are one time use only
 */
public interface Consumable {

    /**
     * This method allow a character to use an item on themselves. It does NOT remove an item from the inventory.
     *
     * @param character the battleCharacter to use the item on
     */
    void consume(@NotNull BattleCharacter character);
}
