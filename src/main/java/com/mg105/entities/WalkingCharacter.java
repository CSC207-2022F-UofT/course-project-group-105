package com.mg105.entities;

public class WalkingCharacter {
    public RoomPosition charPosition;

    public WalkingCharacter(RoomPosition position) {
        this.charPosition = position;
    }

    /**
     * Set the walking character's coordinates.
     */
    public void setCharPosition(RoomPosition position) {
        charPosition = position;
    }

    /**
     * @return the coordinates that the walking character is at.
     */
    public RoomPosition getCharPosition() {
        return this.charPosition;
    }
}

