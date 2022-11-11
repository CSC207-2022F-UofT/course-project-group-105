package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;

public class HealthPotion extends Item implements Consumable {

    /**
     * A health potion is an item that can be used to heal a character by a certain amount of points.
     *
     */

    // This number could change later

    private final static int HEALING_POINTS = 25;

    public HealthPotion() {
        super(ItemConstants.HEALTH_POTION_NAME, ItemConstants.HEALTH_POTION_DESCRIPTION);
    }

    public static int getHealingPoints() {
        return HEALING_POINTS;
    }

    public void consume(BattleCharacter character){
        character.modifyHealth(HEALING_POINTS);

    }
}
