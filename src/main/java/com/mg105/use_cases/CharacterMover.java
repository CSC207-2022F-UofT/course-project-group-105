package com.mg105.use_cases;

import com.mg105.entities.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Move the player within a room.
 */
public class CharacterMover {
    private final @NotNull GameState state;
    private final @NotNull RoomUpdater updater;

    /**
     * Create a CharacterMover to operate on state and to update the room visuals via the updater
     *
     * @param state   the current game state.
     * @param updater something that will update the visual representation of the room on change.
     */
    public CharacterMover(@NotNull GameState state, @NotNull RoomUpdater updater) {
        this.state = state;
        this.updater = updater;
    }

    /**
     * Attempt to move by direction.  Taking to account walls, chests, battles, exits, etc.
     *
     * @param direction the direction to move. The direction must have a magnitude of 1 and be in exactly one
     *                  cardinal direction.
     */
    public void generateMapMoveBy(Point direction) {
        // Because direction.x and direction.y are integers, the precondition for direction is satisfied iff the
        // magnitude of the vector is 1 which is true iff the sum of the absolute value of the components is 1.
        assert Math.abs(direction.x) + Math.abs(direction.y) == 1;

        Point nextPosition = new Point(
            state.currentPosition.x + direction.x,
            state.currentPosition.y + direction.y
        );
        boolean nextPositionValid = true;

        Room room = state.getCurrentRoom();

        if (nextPosition.x == 0 || nextPosition.x == 7 ||
            nextPosition.y == 0 || nextPosition.y == 7) {
            nextPositionValid = false;
        }

        for (Doorway doorway : room.getDoorways()) {
            if (doorway.getPosition().equals(nextPosition)) {
                nextPositionValid = true;
                break;
            }
        }

        for (TreasureChest chest : room.getChests()) {
            if (chest.getPosition().equals(nextPosition)) {
                nextPositionValid = false;
                break;
            }
        }

        for (OpponentSet opponents : room.getOpponents()) {
            if (opponents.getPosition().equals(nextPosition)) {
                nextPositionValid = false;
                break;
            }
        }

        if (nextPositionValid) {
            state.currentPosition = nextPosition;
            updater.updateRoom();
        }
    }
}
