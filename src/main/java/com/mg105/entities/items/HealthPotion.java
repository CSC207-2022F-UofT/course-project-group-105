package com.mg105.entities.items;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Consumable;
import com.mg105.entities.GameState;
import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;
import org.jetbrains.annotations.NotNull;

/**
 * A health potion is an item that can be used to heal a character by a certain amount of points.
 */
public class HealthPotion extends Item implements Consumable {

    // This number could change later

    private final static int HEALING_POINTS = 25;

    /**
     * Creates a new instance of health potion
     */
    public HealthPotion() {
        super(ItemConstants.HEALTH_POTION_NAME);
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
        BattleCharacter character = state.getPartyMember(characterName);
        if(character.getHp() == 0){
            state.removeFaintedPartyMember(characterName);
            state.addPartyMemberToAlive(character);
        }
        character.modifyHealth(HEALING_POINTS);

    }
}
