package com.mg105.interface_adapters;

import com.mg105.use_cases.RoomGetter;
import com.mg105.use_cases.outputds.RoomLayout;
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
            canvas[i][MapConstants.ROOM_SIZE - 1] = TileType.WALL;
            canvas[i][0] = TileType.WALL;
        }
        for (int i = 1; i < MapConstants.ROOM_SIZE - 1; i++) {
            canvas[MapConstants.ROOM_SIZE - 1][i] = TileType.WALL_WITH_FACE;
            canvas[0][i] = TileType.WALL_WITH_FACE;
        }
        canvas[MapConstants.ROOM_SIZE - 1][0] = TileType.WALL_WITH_FACE;
        canvas[MapConstants.ROOM_SIZE - 1][MapConstants.ROOM_SIZE - 1] = TileType.WALL_WITH_FACE;

        RoomLayout room = getter.getCurrentRoomLayout();

        for (Point doorway : room.getDoorways()) {
            canvas[doorway.y][doorway.x] = TileType.EXIT;
            if (doorway.x == 0 || doorway.x == MapConstants.ROOM_SIZE - 1) {
                canvas[doorway.y - 1][doorway.x] = TileType.WALL_WITH_FACE;
            }
        }

        for (Point chest : room.getClosedChests()) {
            canvas[chest.y][chest.x] = TileType.CHEST;
        }

        for (Point chest : room.getOpenChests()) {
            canvas[chest.y][chest.x] = TileType.CHEST_OPEN;
        }

        for (Point opponents : room.getOpponents()) {
            canvas[opponents.y][opponents.x] = TileType.OPPONENT_SET;
        }

        return canvas;
    }

    /**
     * Get the current player position in the room.
     *
     * @return the current player position in the room.
     */
    public @NotNull Point getPlayer() {
        return getter.getCurrentRoomLayout().getPlayer();
    }

    /**
     * Retrieves the sprite String currently associated with the WalkingCharacter.
     * @return a file name/location as a String for the desired character sprite.
     */
    public String updateCharacterSprite() {
        return this.getter.getWalkingSprite();
    }
}
