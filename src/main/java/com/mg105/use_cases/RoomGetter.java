package com.mg105.use_cases;

import com.mg105.entities.Doorway;
import com.mg105.entities.GameState;
import com.mg105.entities.OpponentSet;
import com.mg105.entities.TreasureChest;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
     * <p>
     * By definition this only gets the environment of the room.
     *
     * @return the current room.
     */
    public @NotNull RoomLayout getCurrentRoomLayout() {
        List<Point> chests = new ArrayList<>(state.getCurrentRoom().getChests().size());
        for (TreasureChest chest : state.getCurrentRoom().getChests()) {
            chests.add(chest.getPosition());
        }

        List<Point> opponents = new ArrayList<>(state.getCurrentRoom().getOpponents().size());
        for (OpponentSet opponent : state.getCurrentRoom().getOpponents()) {
            opponents.add(opponent.getPosition());
        }

        List<Point> doorways = new ArrayList<>(state.getCurrentRoom().getDoorways().size());
        for (Doorway doorway : state.getCurrentRoom().getDoorways()) {
            doorways.add(doorway.getPosition());
        }

        return new RoomLayout(chests, opponents, doorways, state.getWalkingCharacter().getCharPosition());
    }
}
