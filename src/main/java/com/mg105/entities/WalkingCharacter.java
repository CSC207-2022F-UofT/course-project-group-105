package com.mg105.entities;

import java.awt.*;

public class WalkingCharacter {
    private Point charPosition;
    private String spriteName;

    public WalkingCharacter(Point position) {
        this.charPosition = position;
        this.spriteName = "A";
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

    /**
     * Returns the name of the character whose sprite is currently being used.
     * @return a sprite name String.
     */
    public String getSpriteName() {
        return spriteName;
    }

    /**
     * Sets the name of the character whose sprite is currently being used.
     * @param spriteName name of the character to change the sprite to.
     */
    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }
}

