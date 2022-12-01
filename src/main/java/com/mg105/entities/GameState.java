package com.mg105.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;

/**
 * A class that represents the state of the game
 * <p>
 * This class has all attributes that the game relies on
 */

public class GameState {
    private Room firstRoom;
    private Room lastRoom;
    private Room currentRoom;

    private OpponentSet currOpponent;
    private final ArrayList<BattleCharacter> party;
    private final WalkingCharacter walkingCharacter;
    private final Inventory inventory;
    private Battle currEncounter = null;

    //Potentially useless. Keeps track of party characters who faint in battle.
    private final ArrayList<BattleCharacter> fainted = new ArrayList<>();

    /**
     * Create a new game state.
     *
     * @param inventory        the player's inventory.
     * @param party            the player's party.
     * @param walkingCharacter the player's character data.
     */
    public GameState(Inventory inventory, BattleCharacter[] party, WalkingCharacter walkingCharacter) {
        this.inventory = inventory;
        this.party = new ArrayList<>();

        this.party.addAll(Arrays.asList(party));
        this.walkingCharacter = walkingCharacter;
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
     * @param characterName the name of the character
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

    /**
     * Get the current WalkingCharacter the user is controlling.
     *
     * @return the walkingCharacter.
     */
    public @NotNull WalkingCharacter getWalkingCharacter() {
        return walkingCharacter;
    }

    /**
     * Swap out the map of the game state to a new one defined by an interconnected graph between firstRoom and
     * lastRoom.  It should be noted that the interconnectedness is not checked here.
     * <p>
     * Additionally, the current room is also set as the first room.
     *
     * @param firstRoom the first room of the map (where the tutorial is played).
     * @param lastRoom  the end room of the map (where the game is won).
     */
    public void setMap(@NotNull Room firstRoom, @NotNull Room lastRoom) {
        this.firstRoom = firstRoom;
        this.currentRoom = firstRoom;
        this.lastRoom = lastRoom;
    }

    /**
     * Get the room the player happens to currently be in.
     *
     * @return the current room.
     */
    public @NotNull Room getCurrentRoom() {
        return currentRoom;
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

    /**
     * Returns the currently selected OpponentSet.
     * @return the currently selected OpponentSet.
     */
    public OpponentSet getCurrOpponent() {
        return currOpponent;
    }

    /**
     * Sets the current OpponentSet to be faced.
     * @param currOpponent the currently selected OpponentSet.
     */
    public void setCurrOpponent(OpponentSet currOpponent) {
        this.currOpponent = currOpponent;
    }

    /**
     * Get if the player is in the first room.
     *
     * @return true if the player is in the first room, false otherwise.
     */
    public boolean isCurrentRoomFirstRoom() {
        // NOTE: In this case it is actually ok to directly compare the two via == and not .equals()... This is because
        // we actually care that this is exactly the same instance of the room, not just two rooms that happen to have
        // the same configuration.
        return currentRoom == firstRoom;
    }

    /**
     * Get if the player is in the last room.
     *
     * @return true if the player is in the last room, false otherwise.
     */
    public boolean isCurrentRoomLastRoom() {
        // NOTE: In this case it is actually ok to directly compare the two via == and not .equals()... This is because
        // we actually care that this is exactly the same instance of the room, not just two rooms that happen to have
        // the same configuration.
        return currentRoom == lastRoom;
    }

    /**
     * Set the current room.  room must be graph-connected to the rest of the map.
     *
     * @param room the room to be set.
     */
    public void setCurrentRoom(@NotNull Room room) {
        this.currentRoom = room;
    }
}
