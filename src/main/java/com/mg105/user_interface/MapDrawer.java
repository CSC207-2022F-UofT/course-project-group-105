package com.mg105.user_interface;

import com.mg105.interface_adapters.map.RoomInterpreterInterface;
import com.mg105.interface_adapters.map.TileType;
import com.mg105.utils.MapConstants;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MapDrawer draws the map as a grid of tiles.
 */
public class MapDrawer implements PropertyChangeListener, Toggleable {
    private final @NotNull RoomInterpreterInterface interpreter;

    private final @NotNull Scene scene;
    private final @NotNull Group group;
    private final @NotNull Map<TileType, Image> tiles;
    private final @NotNull Map<String, Image> playerSprites;
    private final @NotNull Image missingTile;
    private boolean isVisible;

    /**
     * Create an instance of MapDrawer.
     *
     * @param interpreter the room interpreter used to format the data in an acceptable way for this class.
     */
    public MapDrawer(@NotNull RoomInterpreterInterface interpreter) {
        this.interpreter = interpreter;

        group = new Group();
        scene = new Scene(
            group,
            MapConstants.ROOM_SIZE * MapConstants.TILE_SIZE,
            MapConstants.ROOM_SIZE * MapConstants.TILE_SIZE
        );
        isVisible = false;

        missingTile = new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/missing.png")));

        tiles = new HashMap<>(6);
        tiles.put(TileType.FLOOR, new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/floor.png"))));
        tiles.put(TileType.WALL, new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/wall.png"))));
        tiles.put(TileType.WALL_WITH_FACE, new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/wall_face.png"))));
        tiles.put(TileType.EXIT, new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/exit.png"))));
        tiles.put(TileType.CHEST, new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/chest.png"))));
        tiles.put(TileType.CHEST_OPEN, new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/chest_open.png"))));
        tiles.put(TileType.OPPONENT_SET, new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/tiles/battle.png"))));

        playerSprites = new HashMap<>(4);
        playerSprites.put("/sprites/A.png", new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/sprites/A.png"))));
        playerSprites.put("/sprites/B.png", new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/sprites/B.png"))));
        playerSprites.put("/sprites/C.png", new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/sprites/C.png"))));
        playerSprites.put("/sprites/D.png", new Image(Objects.requireNonNull(MapDrawer.class.getResourceAsStream("/sprites/D.png"))));
        // While in theory getResourceAsStream can fail, in practice this will never happen because the images are
        // bundled in the Jar.  If this isn't the case then the NullPointerException is the least of your worries.
    }

    /**
     * Get the scene that will be used to draw to.
     *
     * @return the scene that the MapDrawer will draw to.
     */
    @Override
    public @NotNull Scene getScene() {
        return scene;
    }

    /**
     * Toggle the visibility of map drawer.  In this case we need to make sure the map drawing is up-to-date.
     *
     * @param isVisible true if the map is now visible, false otherwise.
     */
    @Override
    public void toggle(boolean isVisible) {
        this.isVisible = isVisible;

        if (isVisible) {
            updateRoom();
        }
    }

    /**
     * Redraw the current room.  This method only needs to be called if something has changed in the underlying
     * current room.
     */
    public void updateRoom() {
        TileType[][] room = interpreter.getCurrentRoom();

        group.getChildren().clear();

        for (int y = 0; y < MapConstants.ROOM_SIZE; y++) {
            for (int x = 0; x < MapConstants.ROOM_SIZE; x++) {
                ImageView tile = new ImageView(tiles.getOrDefault(room[y][x], missingTile));

                tile.setPreserveRatio(true);
                tile.setX(x * MapConstants.TILE_SIZE);
                tile.setY(y * MapConstants.TILE_SIZE);
                tile.setFitHeight(MapConstants.TILE_SIZE);
                tile.setFitWidth(MapConstants.TILE_SIZE);

                group.getChildren().add(tile);
            }
        }

        Point player = interpreter.getPlayer();
        ImageView playerTile = new ImageView(playerSprites.getOrDefault(interpreter.getCharacterSprite(), missingTile));
        playerTile.setPreserveRatio(true);
        playerTile.setX(player.x * MapConstants.TILE_SIZE);
        playerTile.setY(player.y * MapConstants.TILE_SIZE);
        playerTile.setFitHeight(MapConstants.TILE_SIZE);
        playerTile.setFitWidth(MapConstants.TILE_SIZE);
        group.getChildren().add(playerTile);
    }

    /**
     * Update the current room based on evt.
     * <p>
     * Note that none of the properties of evt are used.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!isVisible) {
            // As per the specification of Toggleable, we do nothing if we are not visible.
            return;
        }

        updateRoom();
    }
}
