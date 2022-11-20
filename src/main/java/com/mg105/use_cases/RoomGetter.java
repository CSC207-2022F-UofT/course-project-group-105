package com.mg105.use_cases;

import com.mg105.entities.GameState;
import com.mg105.entities.Room;
import org.jetbrains.annotations.NotNull;

/**
 * TODO
 */
public class RoomGetter {
    private final @NotNull GameState state;

    /**
     * TODO
     *
     * @param state
     */
    public RoomGetter(@NotNull GameState state) {
        this.state = state;
    }

    /**
     * TODO
     *
     * @return
     */
    public Room getCurrentRoom() {
        return state.getCurrentRoom();
    }
}
