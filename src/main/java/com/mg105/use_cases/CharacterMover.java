package com.mg105.use_cases;

import com.mg105.entities.GameState;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class CharacterMover {
    private final @NotNull GameState state;
    private final @NotNull RoomUpdater updater;

    /**
     * TODO
     *
     * @param state
     * @param updater
     */
    public CharacterMover(@NotNull GameState state, @NotNull RoomUpdater updater) {
        this.state = state;
        this.updater = updater;
    }

    /**
     * TODO
     *
     * @param direction the direction to move. The direction must have a magnitude of 1 and be in exactly one
     *                  cardinal direction.
     */
    public void generateMapMoveBy(Point direction) {
        // Because direction.x and direction.y are integers, the precondition for direction is satisfied iff the
        // magnitude of the vector is 1 which is true iff the sum of the absolute value of the components is 1.
        assert Math.abs(direction.x) + Math.abs(direction.y) == 1;

        state.currentPosition = new Point(
            state.currentPosition.x + direction.x,
            state.currentPosition.y + direction.y
        );

        updater.updateRoom();
    }
}
