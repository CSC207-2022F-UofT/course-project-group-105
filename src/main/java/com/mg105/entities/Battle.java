package com.mg105.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Battle is a class which contains information about the status of a combat encounter, the participating characters,
 * and the order in which they would move, along with a number of upgrade tokens upon victory.
 */
public class Battle {
    //battleStatus: 0 for in progress, -1 for loss, 1 for win
    private int battleStatus;
    private final ArrayList<BattleCharacter> opponents;
    private final ArrayList<BattleCharacter> playerCharacters;
    private final ArrayList<BattleCharacter> moveQueue = new ArrayList<BattleCharacter>();

    //not sure if numTokens should be randomly generated on creation, or after battleStatus has changed (this
    // implementation will assume the latter).
    private int numTokens;

    public Battle(ArrayList<BattleCharacter> opponents, ArrayList<BattleCharacter> party) {
        this.battleStatus = 0;
        this.opponents = opponents;
        this.playerCharacters = party;
        this.numTokens = 0;

        this.moveQueue.addAll(this.opponents);
        this.moveQueue.addAll(playerCharacters);
        Collections.sort(moveQueue);
    }

    /**
     * Returns whether the encounter has been won, lost or neither.
     *
     * @return an integer in {-1, 0, 1} representing the current status of the battle.
     */
    public int getBattleStatus() {
        return battleStatus;
    }

    /**
     * Returns the list of active opponent characters.
     *
     * @return an ArrayList of opponent BattleCharacters.
     */
    public ArrayList<BattleCharacter> getOpponents() {
        return opponents;
    }

    /**
     * Returns the list of active player characters.
     *
     * @return an ArrayList of player BattleCharacters.
     */
    public ArrayList<BattleCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    /**
     * Returns the number of tokens rewarded to the player upon victory
     *
     * @return the number of upgrade tokens the player receives if they win the Battle.
     */
    public int getNumTokens() {
        return numTokens;
    }

    /**
     * Modifies the number of tokens rewarded to the player upon victory
     *
     * @param reward the number of upgrade tokens the player receives if they win the Battle.
     */
    public void setNumTokens(int reward) {
        this.numTokens = reward;
    }

    /**
     * Removes the character at the front of the moveQueue, replaces it at the end, and returns the character.
     *
     * @return the next BattleCharacter to move.
     */
    public BattleCharacter getMovingCharacter() {
        BattleCharacter currentChar = this.moveQueue.get(this.moveQueue.size() - 1);
        this.moveQueue.remove(currentChar);
        this.moveQueue.add(0, currentChar);
        return currentChar;
    }

    /**
     * Removes the given fainted character from the ArrayList corresponding to which side it is on, and the moveQueue.
     *
     * @param fainted the BattleCharacter to be removed from the game.
     */
    public void removeChar(BattleCharacter fainted) {
        if (fainted.isOpponent()) {
            this.opponents.remove(fainted);
        } else {
            this.playerCharacters.remove(fainted);
        }
        this.moveQueue.remove(fainted);

        if (this.opponents.isEmpty()) {
            this.battleStatus = 1;
        } else if (this.playerCharacters.isEmpty()) {
            this.battleStatus = -1;
        }
    }

    /**
     * Returns the character in the encounter based on the given name
     *
     * @param name
     * @return the BattleCharacter in the encounter with the given name
     * @throws NoSuchElementException if no character in the party has the given name
     */
    //Same implementation as that in P.R. #45
    public BattleCharacter getCharacter(String name) throws NoSuchElementException {
        for (BattleCharacter c : this.moveQueue) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        throw new NoSuchElementException("No Battle Character with this name exists");
    }
}
