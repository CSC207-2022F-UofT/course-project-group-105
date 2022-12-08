package com.mg105.use_cases.map;

import java.awt.*;

/**
 * An interface that represents a class that moves a character.
 */
public interface CharacterMoverInterface {
    /**
     * Move the character by direction.
     *
     * @param direction the direction to move in.  The magnitude of direction must be 1 and must be in exactly one
     *                  direction.
     */
    void generateMapMoveBy(Point direction);
}
