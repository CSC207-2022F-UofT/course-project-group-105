package com.mg105.use_cases;

import com.mg105.entities.Doorway;
import com.mg105.entities.GameState;
import com.mg105.entities.OpponentSet;
import com.mg105.entities.TreasureChest;
import com.mg105.use_cases.outputds.RoomLayout;
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
        List<Point> closedChests = new ArrayList<>();
        List<Point> openChests = new ArrayList<>();
        for (TreasureChest chest : state.getCurrentRoom().getChests()) {
            if (chest.isOpened()) {
                openChests.add(chest.getPosition());
            } else {
                closedChests.add(chest.getPosition());
            }
        }

        List<Point> opponents = new ArrayList<>(state.getCurrentRoom().getOpponents().size());
        for (OpponentSet opponent : state.getCurrentRoom().getOpponents()) {
            opponents.add(opponent.getPosition());
        }

        List<Point> doorways = new ArrayList<>(state.getCurrentRoom().getDoorways().size());
        for (Doorway doorway : state.getCurrentRoom().getDoorways()) {
            doorways.add(doorway.getPosition());
        }

        return new RoomLayout(closedChests, openChests, opponents, doorways, state.getWalkingCharacter().getCharPosition());
    }

    /**
     * Retrieves the WalkingCharacter sprite name String, returns a file name/location corresponding to the name.
     * @return a String representing the file name/location for the selected character sprite.
     */
    public String getWalkingSprite() {
        return "/sprites/" + state.getWalkingCharacter().getSpriteName() + ".png";
    }
}
