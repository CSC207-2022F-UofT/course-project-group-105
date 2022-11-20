package com.mg105.entities;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * GameState is a class that represents the state of the game.
 *
 * Contains all attributes used by the game.
 */
public class GameState {

    private Battle currEncounter = null;

    private final ArrayList<BattleCharacter> party;

    //Potentially useless. Keeps track of party characters who faint in battle.
    private  ArrayList<BattleCharacter> fainted = new ArrayList<BattleCharacter>();

    public GameState() {
        //Create four characters (names, stats can be discussed later) and add them to the party.
        BattleCharacter alan = new BattleCharacter(30, "Alan", 4, 5, false,
            new Move(-3, 0, "Slow swing", false),
            new Move(0, -1, "Nullify", false));

        BattleCharacter beatrice = new BattleCharacter(20, "Beatrice", 6, 8, false,
            new Move(-4, 0, "Strong swing", false),
            new Move(3, 0, "Weak heal", true));

        BattleCharacter callum = new BattleCharacter(25, "Callum", 3, 6, false,
            new Move(6, 0, "Strong heal", true),
            new Move(2, 2, "Reinforce", true));

        BattleCharacter diana = new BattleCharacter(15, "Diana", 9, 10, false,
            new Move(-5, 0, "Surprise attack", false),
            new Move(-2, -2, "Sabotage", false));

        this.party = new ArrayList<BattleCharacter>();
        party.add(alan);
        party.add(beatrice);
        party.add(callum);
        party.add(diana);
    }

    /**
     * Returns the active Battle, or null if there is none.
     *
     * @return the current Battle, or null if there is no active encounter.
     */
    public Battle getCurrEncounter() {
        return currEncounter;
    }

    /**
     * Set currEncounter to the given active Battle.
     *
     * @param currEncounter the Battle to set currEncounter to.
     */
    public void setCurrEncounter(Battle currEncounter) {
        this.currEncounter = currEncounter;
    }

    /**
     * Returns an ArrayList of the player's characters.
     *
     * @return An ArrayList of the player's characters.
     */
    public ArrayList<BattleCharacter> getParty() {
        return this.party;
    }

    /**
     * Returns an ArrayList of the player's fainted characters.
     *
     * @return An ArrayList of the player's fainted characters.
     */
    public ArrayList<BattleCharacter> getFainted() {
        return this.fainted;
    }

    public Room[][] map;
}
