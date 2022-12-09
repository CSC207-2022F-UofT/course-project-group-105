package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Information about the player being placed on the map.
 */
public class WalkingCharacter {
    private @NotNull Point charPosition;
    private @NotNull String spriteName;

    /**
     * Create a new WalkingCharacter at a given position.
     *
     * @param position the initial position of the player.
     */
    public WalkingCharacter(@NotNull Point position) {
        this.charPosition = position;
        this.spriteName = "A";
    }

    /**
     * Return the position of the walking character.
     *
     * @return the coordinates that the walking character is at.
     */
    public Point getCharPosition() {
        return new Point(this.charPosition);
        // We create copies to avoid accidental modification
    }

    /**
     * Set the walking character's coordinates.
     *
     * @param position the new position of the walking character.
     */
    public void setCharPosition(@NotNull Point position) {
        charPosition = new Point(position);
        // We create copies to avoid accidental modification
    }

    /**
     * Returns the name of the character whose sprite is currently being used.
     *
     * @return a sprite name String.
     */
    public @NotNull String getSpriteName() {
        return spriteName;
    }

    /**
     * Sets the name of the character whose sprite is currently being used.
     *
     * @param spriteName name of the character to change the sprite to.
     */
    public void setSpriteName(@NotNull String spriteName) {
        this.spriteName = spriteName;
    }
}

