package com.mg105.entities;

import java.util.NoSuchElementException;

public class GameState {
    private Room firstRoom;
    private Room lastRoom;
    private Room currentRoom;
    private WalkingCharacter walkingCharacter;
    private final BattleCharacter[] party;
    private final Inventory inventory;

    public GameState(Inventory inventory, BattleCharacter[] party, WalkingCharacter walkingCharacter) {
        this.inventory = inventory;
        this.party = party;
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
     * Swap out the map of the game state to a new one defined by an interconnected graph between firstRoom and
     * lastRoom.  It should be noted that the interconnectedness is not checked here.
     *
     * Additionally, the current room is also set as the first room.
     *
     * Both firstRoom and lastRoom should not be null.
     *
     * @param firstRoom the first room of the map (where the tutorial is played).
     * @param lastRoom  the end room of the map (where the game is won).
     */
    public void setMap(Room firstRoom, Room lastRoom) {
        this.firstRoom = firstRoom;
        this.currentRoom = firstRoom;
        this.lastRoom = lastRoom;
    }

    /**
     * Get the room the player happens to currently be in.
     *
     * @return the current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Get the room the player happes to currently be in.
     *
     * @return the current room.
     */
    public WalkingCharacter getWalkingCharacter() {
        return walkingCharacter;
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
}
