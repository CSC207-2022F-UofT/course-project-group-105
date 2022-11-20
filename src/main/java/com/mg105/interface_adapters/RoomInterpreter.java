package com.mg105.interface_adapters;

import com.mg105.entities.Doorway;
import com.mg105.entities.OpponentSet;
import com.mg105.entities.Room;
import com.mg105.entities.TreasureChest;
import com.mg105.use_cases.RoomGetter;
import org.jetbrains.annotations.NotNull;

/**
 * TODO
 */
public class RoomInterpreter {
    private final @NotNull RoomGetter getter;

    /**
     * TODO
     *
     * @param getter
     */
    public RoomInterpreter(@NotNull RoomGetter getter) {
        this.getter = getter;
    }

    /**
     * TODO
     *
     * @return
     */
    public TileType[][] getCurrentRoom() {
        TileType[][] canvas = new TileType[8][8];

        for (int y = 1; y < 7; y++) {
            for (int x = 1; x < 7; x++) {
                canvas[y][x] = TileType.FLOOR;
            }
        }

        for (int i = 0; i < 8; i++) {
            canvas[0][i] = TileType.WALL;
            canvas[7][i] = TileType.WALL;
            canvas[i][0] = TileType.WALL;
            canvas[i][7] = TileType.WALL;
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

        return canvas;
    }
}
