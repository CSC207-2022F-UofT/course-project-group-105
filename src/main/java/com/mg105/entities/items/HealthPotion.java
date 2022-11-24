package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.Item;
import com.mg105.entities.items.utils.ItemConstants;

/**
 * A health potion is an item that can be used to heal a character by a certain amount of points.
 */
public class HealthPotion extends Item implements Consumable {

    // This number could change later

    private final static int HEALING_POINTS = 25;

    public HealthPotion() {
        super(ItemConstants.HEALTH_POTION_NAME, ItemConstants.HEALTH_POTION_DESCRIPTION);
    }

    public static int getHealingPoints() {
        return HEALING_POINTS;
    }

    /**
     * Heals the battleCharacter provided
     *
     * @param character the battleCharacter to use the item on
     * @see Consumable
     */
    @Override
    public void consume(BattleCharacter character) {
        character.modifyHealth(HEALING_POINTS);

    }
}
