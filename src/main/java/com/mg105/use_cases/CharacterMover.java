package com.mg105.use_cases;

import com.mg105.entities.GameState;

import java.awt.*;

public class CharacterMover {
    private final GameState state;

    /**
     * TODO
     *
     * @param state
     */
    public CharacterMover(GameState state) {
        this.state = state;
    }

    /**
     * TODO
     *
     * @param direction the direction to move. The direction must have a magnitude of 1 and be in exactly one
     *                  cardinal direction.
     *
     * @return
     */
    public boolean generateMapMoveBy(Point direction) {
        // Because direction.x and direction.y are integers, the precondition for direction is satisfied iff the
        // magnitude of the vector is 1 which is true iff the sum of the absolute value of the components is 1.
        assert Math.abs(direction.x) + Math.abs(direction.y) == 1;

        return true;
    }
}
