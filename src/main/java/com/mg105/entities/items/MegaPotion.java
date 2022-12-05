package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.GameState;
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
     * @param state the state of the game
     * @param characterName the name of the character to use the item on
     */
    @Override
    public void consume(@NotNull GameState state, @NotNull String characterName) {
        BattleCharacter character = state.getFaintedPartyMember(characterName);
        if(character != null){
            state.removeFaintedPartyMember(characterName);
        }

        character = state.getPartyAliveMember(characterName);

        if(character ==  null){
            return;
        }
        character.modifyHealth(HEALING_POINTS);

    }
}
