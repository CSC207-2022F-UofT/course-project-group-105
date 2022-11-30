package com.mg105.interface_adapters;

import com.mg105.entities.*;
import com.mg105.use_cases.RoomGetter;
import com.mg105.utils.MapConstants;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * RoomInterpreter interprets the current room in a format that is easier to draw from.
 */
public class RoomInterpreter {
    private final @NotNull RoomGetter getter;

    /**
     * Create a new RoomInterpreter that will get its information from getter.
     *
     * @param getter the information source for the RoomInterpreter.
     */
    public RoomInterpreter(@NotNull RoomGetter getter) {
        this.getter = getter;
    }

    /**
     * Get the current room as represented in an easier to draw grid.
     *
     * @return the room as a 2 dimension array of TileType representing the current state of the room. Note that (0, 0)
     * represents the top-left corner and (MapConstants.ROOM_SIZE, MapConstants.ROOM_SIZE) represents the bottom
     * right corner.
     */
    public TileType[][] getCurrentRoom() {
        TileType[][] canvas = new TileType[MapConstants.ROOM_SIZE][MapConstants.ROOM_SIZE];

        for (int y = 1; y < MapConstants.ROOM_SIZE - 1; y++) {
            for (int x = 1; x < MapConstants.ROOM_SIZE - 1; x++) {
                canvas[y][x] = TileType.FLOOR;
            }
        }

        for (int i = 0; i < MapConstants.ROOM_SIZE; i++) {
            canvas[0][i] = TileType.WALL;
            canvas[i][0] = TileType.WALL;
            canvas[MapConstants.ROOM_SIZE - 1][i] = TileType.WALL;
            canvas[i][MapConstants.ROOM_SIZE - 1] = TileType.WALL;
        }

        Room room = getter.getCurrentRoom();

        for (Doorway doorway : room.getDoorways()) {
            canvas[doorway.getPosition().y][doorway.getPosition().x] = TileType.EXIT;
        }

        for (TreasureChest chest : room.getChests()) {
            canvas[chest.getPosition().y][chest.getPosition().x] = TileType.CHEST;
        }

        for (OpponentSet opponents : room.getOpponents()) {
            canvas[opponents.getPosition().y][opponents.getPosition().x] = TileType.OPPONENT_SET;
        }

        WalkingCharacter player = getter.getWalkingCharacter();
        Point playerPosition = player.getCharPosition();
        canvas[playerPosition.y][playerPosition.x] = TileType.PLAYER;

        return canvas;
    }
}
