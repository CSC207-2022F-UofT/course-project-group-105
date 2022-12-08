package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.GameState;
import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;
import org.jetbrains.annotations.NotNull;

/**
 * An item that is used upgrade a characters stat
 *
 * <p>One upgrade token boosts every stat of a single character</p>
 */
public class UpgradeToken extends Item implements Consumable {

    /**
     * Creates a new instance of upgrade token
     */
    public UpgradeToken() {
        super(ItemConstants.UPGRADE_TOKEN_NAME);
    }

    /**
     * Upgrades the stats of the given character
     *
     * @param state         the state of the game
     * @param characterName the name of the character to use the item on
     */
    @Override
    public void consume(@NotNull GameState state, @NotNull String characterName) {
        BattleCharacter character = state.getPartyMember(characterName);
        character.modifyDamage(1);
        character.modifyMaxHp(1);
        character.modifySpeed(1);
    }
}
