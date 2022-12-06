package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.use_cases.map.MapGenerator;
import org.jetbrains.annotations.NotNull;


/**
 * A class that implement the game restart and replay function.
 * this class have players' attribute inheritance method and players' inventory clean method.
 */
public class ReplayGenerator {
    private final @NotNull GameState state;
    private final @NotNull Resetable[] resetables;

    /**
     * Create a new replaygenerator
     *
     * @param state      the game state.
     * @param resetables resetable things.
     */
    public ReplayGenerator(@NotNull GameState state, @NotNull Resetable... resetables) {
        this.state = state;
        this.resetables = resetables;
    }

    /**
     * let players inherit their attribute in the last game and recover their HP to their max HP.
     */
    private void attributeInheritance() {
        for (BattleCharacter bc : state.getParty()) {
            bc.modifyHealth(bc.getMaxHp());
        }
    }

    /**
     * Clean all the items in the inventory
     */
    private void inventoryClean() {
        state.getInventory().removeAll();
    }

    /**
     * Replay the game by interacting with the MapGenerator class
     */
    public void replay() {
        this.inventoryClean();
        this.attributeInheritance();
        // incomplete remake, need to amend later. exactly implementation should depend
        // on other use cases' implementation
        MapGenerator isekai = new MapGenerator(state);
        isekai.generateMap();
        for (Resetable resetable : resetables) {
            resetable.reset();
        }
    }
}
