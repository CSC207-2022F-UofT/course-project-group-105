package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;

/**
 * A mega health potion is larger health potion that can be used to heal a character by a greater number of points.
 */
public class MegaPotion extends Item implements Consumable {

    private final static int HEALING_POINTS = 40;

    public MegaPotion() {
        super(ItemConstants.MEGA_POTION_NAME, ItemConstants.MEGA_POTION_DESCRIPTION);
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
