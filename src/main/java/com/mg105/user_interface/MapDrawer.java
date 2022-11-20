package com.mg105.user_interface;

import com.mg105.interface_adapters.RoomInterpreter;
import com.mg105.interface_adapters.TileType;
import com.mg105.use_cases.RoomUpdater;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * MapDrawer draws the map as a grid of tiles.
 */
public class MapDrawer implements RoomUpdater {
    private @NotNull RoomInterpreter interpreter;

    private @NotNull Scene scene;
    private @NotNull Group group;

    private @NotNull Map<TileType, Image> tiles;

    /**
     * Create an instance of MapDrawer.
     *
     * @param interpreter the room interpreter used to format the data in an acceptable way for this class.
     */
    public MapDrawer(@NotNull RoomInterpreter interpreter) {
        this.interpreter = interpreter;

        group = new Group();
        scene = new Scene(group, 8*32, 8*32);

        tiles = new HashMap<>(6);
        tiles.put(TileType.FLOOR, new Image(RoomUpdater.class.getResourceAsStream("/tiles/floor.png")));
        tiles.put(TileType.WALL, new Image(RoomUpdater.class.getResourceAsStream("/tiles/wall.png")));
        tiles.put(TileType.EXIT, new Image(RoomUpdater.class.getResourceAsStream("/tiles/exit.png")));
        tiles.put(TileType.CHEST, new Image(RoomUpdater.class.getResourceAsStream("/tiles/chest.png")));
        tiles.put(TileType.PLAYER, new Image(RoomUpdater.class.getResourceAsStream("/tiles/player.png")));
        tiles.put(TileType.OPPONENT_SET, new Image(RoomUpdater.class.getResourceAsStream("/tiles/battle.png")));
    }

    /**
     * Get the scene that will be used to draw to.
     *
     * @return the scene that the MapDrawer will draw to.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Redraw the current room.  This method only needs to be called if something has changed in the underlying
     * current room.
     */
    @Override
    public void updateRoom() {
        TileType[][] room = interpreter.getCurrentRoom();

        group.getChildren().clear();

        for (int y = 0; y < room.length; y++) {
            for (int x = 0; x < room.length; x++) {
                ImageView tile = new ImageView(tiles.get(room[y][x]));

                tile.setPreserveRatio(true);
                tile.setX(x * 32);
                tile.setY(y * 32);

                group.getChildren().add(tile);
            }
        }
    }
}
