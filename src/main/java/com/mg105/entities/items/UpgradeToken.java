package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;

public class UpgradeToken extends Item implements Consumable {

    /**
     * An item that is used upgrade a characters stat
     *
     * <p>One upgrade token boosts every stat of a single character</p>
     */

    public UpgradeToken() {
        super(ItemConstants.UPGRADE_TOKEN_NAME, ItemConstants.UPGRADE_TOKEN_DESCRIPTION);
    }

    public void consume(BattleCharacter character){
        character.modifyDamage(1);
        character.modifyMaxHp(1);
        character.modifySpeed(1);
    }
}
