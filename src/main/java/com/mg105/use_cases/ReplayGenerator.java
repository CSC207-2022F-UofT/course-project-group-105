package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.items.MegaPotion;
import com.mg105.use_cases.map.MapGenerator;
import com.mg105.utils.PartyConstants;
import org.jetbrains.annotations.NotNull;


/**
 * A class that implement the game restart and replay function.
 * this class have players' attribute inheritance method and players' inventory clean method.
 */
public class ReplayGenerator {
    private final @NotNull GameState state;
    private final @NotNull Resetable[] resetables;

    /**
     * Create a new ReplayGenerator
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
     * Revive all characters and add them to the list of alive characters
     */
    public void reviveCharacters() {
        // revive the characters
        MegaPotion potion = new MegaPotion();
        potion.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[0]);

        MegaPotion potion2 = new MegaPotion();
        potion2.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[1]);

        MegaPotion potion3 = new MegaPotion();
        potion3.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[2]);

        MegaPotion potion4 = new MegaPotion();
        potion4.consume(state, PartyConstants.ALL_PARTY_MEMBER_NAMES[3]);

        // set the characters to max health
        for (int i = 0; i < state.getParty().size(); i++) {
            state.getParty().get(i).fullHealCharacter();
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
        //Handles the forfeit case, where party is not empty yet.
        state.getFainted().addAll(state.getParty());
        state.setParty(new BattleCharacter[] {});

        //Party is empty, fainted is full.
        state.getParty().addAll(state.getFainted());
        state.getFainted().removeAll(state.getParty());

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
