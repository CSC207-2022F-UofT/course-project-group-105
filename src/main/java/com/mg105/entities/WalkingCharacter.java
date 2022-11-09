package com.mg105.entities;

public class WalkingCharacter {
    public RoomPosition charPosition;

    public WalkingCharacter(RoomPosition position) {
        this.charPosition = position;
    }

    public void setCharPosition(RoomPosition position) {
        //update character position on map
        charPosition = position;
    }

    public RoomPosition getCharPosition(RoomPosition position) {
        //get character position on map
        return this.charPosition;
    }
}
