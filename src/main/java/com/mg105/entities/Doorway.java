package com.mg105.entities;

public class Doorway {
    private RoomPosition position;
    private Room nextRoom;

    public Doorway(RoomPosition position, Room nextRoom) {
        this.position = position;
        this.nextRoom = nextRoom;
    }

    public RoomPosition getPosition() {
        return position;
    }

    public void setPosition(RoomPosition position) {
        this.position = position;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }
}
