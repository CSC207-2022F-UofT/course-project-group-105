package com.mg105.entities;

public class Doorway {
    private final RoomPosition position;
    private final Room nextRoom;

    /**
     * Create a doorway with its position in the room and the next room.
     *
     * @param position the in-room position of this doorway.
     * @param nextRoom the room that should be travelled to if this doorway is entered.
     */
    public Doorway(RoomPosition position, Room nextRoom) {
        this.position = position;
        this.nextRoom = nextRoom;
    }

    /**
     * Gets the position of the doorway.
     *
     * @return the position of the doorway.
     */
    public RoomPosition getPosition() {
        return position;
    }

    /**
     * Gets the next room.
     *
     * @return the next room.
     */
    public Room getNextRoom() {
        return nextRoom;
    }
}
