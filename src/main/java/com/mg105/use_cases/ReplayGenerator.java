package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;

public class ReplayGenerator {

    //   No need for constructor as we would not initialize an instance for ReplayGenerator

    /**
     * let a new BattleCharacter that inherit all the previous player's attributes
     *
     * @param battleCharacter the battleCharacter in the previous game
     * @return a new BattleCharacter that inherit all the previous player's attributes
     */
    public BattleCharacter attributeInheritance(BattleCharacter battleCharacter) {
        BattleCharacter reincarnation_character = new BattleCharacter(battleCharacter.getHp(), battleCharacter.getName(), battleCharacter.getDmg(), battleCharacter.getSpeed(), battleCharacter.getMoveOne(), battleCharacter.getMoveTwo());

        return reincarnation_character;
    }

    /**
     * Clean all the items in the inventory
     *
     * @param inventory a previous player's inventory that contains items
     */
    public void inventoryClean(Inventory inventory) {
        inventory.removeAll();
    }

    /**
     * Replay the game by interacting with the MapGenerator class
     *
     * @param state           a Gamestate type that contains all the state for game.
     * @param battleCharacter a battleCharacter who lost in the previous game and need to restart.
     */
    public void replay(GameState state, BattleCharacter battleCharacter) {
        // incomplete remake, need to amend later. exactly implementation should depend on other use cases' implementation
        MapGenerator isekai = new MapGenerator(state);
        BattleCharacter reincarnationPlayer = attributeInheritance(battleCharacter);
    }
}
