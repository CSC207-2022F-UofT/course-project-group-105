package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReplayGenerator {
    private final @NotNull GameState state;
    private final @NotNull Resetable[] resetables;

    /**
     * Create a new replaygenerator
     *
     * @param state the game state.
     */
    public ReplayGenerator(@NotNull GameState state, @NotNull Resetable... resetables) {
        this.state = state;
        this.resetables = resetables;
    }

    public ArrayList<BattleCharacter> attributeInheritance() {
        return state.getParty();
    }

    /**
     * Clean all the items in the inventory
     */
    public void inventoryClean() {
        state.getInventory().removeAll();
    }

    /**
     * Replay the game by interacting with the MapGenerator class
     */
    public void replay() {
        // incomplete remake, need to amend later. exactly implementation should depend on other use cases' implementation
        MapGenerator isekai = new MapGenerator(state);
        isekai.generateMap();
        for (Resetable resetable : resetables) {
            resetable.reset();
        }
    }
}
