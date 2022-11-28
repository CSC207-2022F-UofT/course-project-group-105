package com.mg105.entities;

import java.awt.*;

public class WalkingCharacter {
    public Point charPosition;

    public WalkingCharacter(Point position) {
        this.charPosition = position;
    }

    /**
     * Set the walking character's coordinates.
     */
    public void setCharPosition(Point position) {
        charPosition = position;
    }

    /**
     * @return the coordinates that the walking character is at.
     */
    public Point getCharPosition() {
        return this.charPosition;
    }
}

