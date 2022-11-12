package com.mg105.entities;

import java.util.NoSuchElementException;

public class GameState {

    private final BattleCharacter[] party;
    private final Inventory inventory;
    /**
     * A class that represents the state of the game
     * <p>
     * This class has all attributes that the game relies on
     */

    private Room[][] map;

    public GameState(Inventory inventory, BattleCharacter[] party) {
        this.inventory = inventory;
        this.party = party;
    }

    /**
     * Returns the inventory of GameState
     * The Inventory is class that manages the items in a game
     *
     * @return the inventory of GameState
     * @see Inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Returns the character in party based on the given name
     *
     * @param characterName
     * @return a character in party
     * @throws NoSuchElementException if no character in the party has the given name
     */
    public BattleCharacter getPartyMember(String characterName) throws NoSuchElementException {
        for (BattleCharacter character : this.party) {
            if (character.getName().equals(characterName)) {
                return character;
            }
        }

        throw new NoSuchElementException("No Battle Character with this name exists");
    }
}
