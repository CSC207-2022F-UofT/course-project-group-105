package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;
import org.jetbrains.annotations.NotNull;

/**
 * A mega health potion is larger health potion that can be used to heal a character by a greater number of points.
 */
public class MegaPotion extends Item implements Consumable {

    private final static int HEALING_POINTS = 40;

    /**
     * Creates a new instance of mega potion
     */
    public MegaPotion() {
        super(ItemConstants.MEGA_POTION_NAME);
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
    public void consume(@NotNull BattleCharacter character) {
        character.modifyHealth(HEALING_POINTS);

    }
}
