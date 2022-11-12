package com.mg105.entities;

public interface Consumable {
    /**
     * Consumable is an interface that should only be implemented by items that can be use on a character
     *
     * Items that are consumable are items that are one time use only
     */

    /**
     * This method allow a character to use an item on themselves. It does NOT remove an item from the inventory.
     *
     * @param character the battleCharacter to use the item on
     */
    void consume(BattleCharacter character);
}
