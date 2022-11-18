package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

public class GameState {
    private Room firstRoom;
    private Room lastRoom;
    private Room currentRoom;

    /**
     * Swap out the map of the game state to a new one defined by an interconnected graph between firstRoom and
     * lastRoom.  It should be noted that the interconnectedness is not checked here.
     *
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
     * Get the room the player happes to currently be in.
     *
     * @return the current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
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
