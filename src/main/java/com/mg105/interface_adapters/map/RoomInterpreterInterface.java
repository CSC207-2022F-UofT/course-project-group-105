package com.mg105.interface_adapters.map;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Layout the current room.
 */
public interface RoomInterpreterInterface {
    /**
     * Lay out the current room as a square of tiles to be displayed.
     *
     * @return the state of the current room.
     */
    RoomTileType[][] getCurrentRoom();

    /**
     * Get the position of the player within the room.
     *
     * @return the position of the player within the room.
     */
    @NotNull Point getPlayer();

    /**
     * Get the path of the current character sprite.
     *
     * @return the path of the current character sprite
     */
    @NotNull String getCharacterSprite();
}
