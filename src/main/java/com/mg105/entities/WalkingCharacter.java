package com.mg105.entities;

public class WalkingCharacter {
    public RoomPosition charPosition;

    public Room currentRoom;

    public WalkingCharacter(RoomPosition position, Room currentRoom) {
        this.charPosition = position;
        this.currentRoom = currentRoom;
    }

    /**
     * Set the walking character's coordinates.
     */
    public void setCharPosition(RoomPosition position) {
        charPosition = position;
    }

    /**
     * Set the room that the walking character's is currently in.
     */
    public void setCharRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * @return the coordinates that the walking character is at.
     */
    public RoomPosition getCharPosition(RoomPosition position) {
        return this.charPosition;
    }

    /**
     * @return the room that the walking character is in.
     */
    public Room getCharRoom(Room currentRoom) {
        return this.currentRoom;
    }
}
