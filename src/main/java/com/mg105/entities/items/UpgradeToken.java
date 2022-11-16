package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;

/**
 * An item that is used upgrade a characters stat
 *
 * <p>One upgrade token boosts every stat of a single character</p>
 */
public class UpgradeToken extends Item implements Consumable {

    public UpgradeToken() {
        super(ItemConstants.UPGRADE_TOKEN_NAME, ItemConstants.UPGRADE_TOKEN_DESCRIPTION);
    }

    /**
     * Upgrades the stats of the given character
     *
     * @param character the battleCharacter to use the item on
     */
    @Override
    public void consume(BattleCharacter character) {
        character.modifyDamage(1);
        character.modifyMaxHp(1);
        character.modifySpeed(1);
    }
}
