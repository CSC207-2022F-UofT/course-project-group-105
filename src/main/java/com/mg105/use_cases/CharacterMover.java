package com.mg105.use_cases;

import com.mg105.entities.*;
import com.mg105.utils.MapConstants;
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

        Point currentPosition = state.getWalkingCharacter().getCharPosition();
        Point nextPosition = new Point(
            currentPosition.x + direction.x,
            currentPosition.y + direction.y
        );
        boolean nextPositionValid = true;

        Room room = state.getCurrentRoom();

        // walls are not impassible
        if (nextPosition.x <= 0 || nextPosition.x >= MapConstants.ROOM_SIZE-1 ||
            nextPosition.y <= 0 || nextPosition.y >= MapConstants.ROOM_SIZE-1) {
            nextPositionValid = false;
        }

        // treasure chests are impassible
        for (TreasureChest chest : room.getChests()) {
            if (chest.getPosition().equals(nextPosition)) {
                nextPositionValid = false;
                break;
            }
        }

        // opponentsets are impassible
        for (OpponentSet opponents : room.getOpponents()) {
            if (opponents.getPosition().equals(nextPosition)) {
                nextPositionValid = false;
                break;
            }
        }

        // doorways are can be passed and are embedded in a wall
        for (Doorway doorway : room.getDoorways()) {
            if (doorway.getPosition().equals(nextPosition)) {
                // TODO this is not supposed to be in this use case, but for time a hacky version is included here
                Room nextRoom = doorway.getNextRoom();
                state.setCurrentRoom(nextRoom);
                for (Doorway nextDoorway : nextRoom.getDoorways()) {
                    if (nextDoorway.getNextRoom() == room) {
                        nextPosition = new Point(nextDoorway.getPosition());
                        break;
                    }
                }
                nextPositionValid = true;
                break;
            }
        }

        if (nextPositionValid) {
            state.getWalkingCharacter().setCharPosition(nextPosition);
            updater.updateRoom();
        }
    }
}
