package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

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
     * Create a new game state.
     *
     * @param inventory        the player's inventory.
     * @param walkingCharacter the player's character data.
     */

    public GameState(Inventory inventory, WalkingCharacter walkingCharacter) {
        this.inventory = inventory;
        this.party = new ArrayList<>();
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
     * Only returns a party member that is not fainted
     * return null iff the name is not of an non-fainted party member
     * @param characterName the name of the character
     * @return a character in party
     */
    public BattleCharacter getPartyAliveMember(@NotNull String characterName) {
        for (BattleCharacter character : this.party) {
            if (character.getName().equals(characterName)) {
                return character;
            }
        }

        return null;
    }

    /**
     * Adds the battleCharacter to the party
     * @param character the ALIVE character to add to the party
     */
    public void addPartyMemberToAlive(BattleCharacter character){
        this.party.add(character);
    }

    /**
     * Returns the character in the party based on the given name
     * Only returns a party member that is fainted
     * return null iff the name is not of a fainted party member
     * @param characterName the name of the character to be returned
     * @return a character in party
     */
    public BattleCharacter getFaintedPartyMember(@NotNull String characterName) throws NoSuchElementException {
        for (BattleCharacter character : this.fainted) {
            if (character.getName().equals(characterName)) {
                return character;
            }
        }
        return null;
    }


    /**
     * Removes the given fainted party
     * This should only be done to fainted party members who's hp is about to become above zero
     * @param characterName the name of the party member that is fainted
     */
    public void removeFaintedPartyMember(@NotNull String characterName){
        BattleCharacter character = getFaintedPartyMember(characterName);
        if(character == null){
            return;
        }
        this.fainted.remove(character);
    }

    /**
     * Returns the correct party member given a name
     * @param characterName the name of the party member to return
     * @return the correct party member given a name
     * @throws NoSuchElementException iff there is no party member of this name
     */

    public @NotNull BattleCharacter getPartyMember(String characterName) throws NoSuchElementException{
        BattleCharacter member = getPartyAliveMember(characterName);
        if(member != null){
            return member;
        }

        member = getFaintedPartyMember(characterName);

        if(member != null){
            return member;
        }

        throw new NoSuchElementException("Could not find party member of this name");
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
     * Removes the current active battle from the game state.
     */

    public void removeCurrEncounter(){
        this.currEncounter = null;
    }

    /**
     * Returns true iff the player is currently in a battle
     * @return true iff the player is currently in a battle
     */

    public boolean hasCurrEncounter(){
        return this.currEncounter != null;
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

    /**
     * Sets these BattleCharacters as the party
     * This function should really only ever be called once
     *
     * @param party the battles character to the set the party to
     */
    public void setParty(@NotNull BattleCharacter[] party) {
        this.party.clear();
        this.party.addAll(Arrays.asList(party));
    }
}
