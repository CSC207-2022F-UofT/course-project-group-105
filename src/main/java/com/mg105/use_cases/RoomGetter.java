package com.mg105.use_cases;

import com.mg105.entities.GameState;
import com.mg105.entities.Room;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * RoomGetter is responsible for getting the state current room.
 */
public class RoomGetter {
    private final @NotNull GameState state;

    /**
     * Create a new RoomGetter for a given sate.
     *
     * @param state the game state that this room getter operates on.
     */
    public RoomGetter(@NotNull GameState state) {
        this.state = state;
    }

    /**
     * Get the current room.
     *
     * By definition this only gets the environment of the room.
     *
     * @return the current room.
     */
    public Room getCurrentRoom() {
        return state.getCurrentRoom();
    }

    /**
     * Get the position of the player in the room.
     *
     * @return the position of the player in the room.
     */
    public Point getPositionInRoom() {
        return state.currentPosition;
    }
}