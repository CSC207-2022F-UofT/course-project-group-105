package com.mg105.interface_adapters.map;

/**
 * Types of tiles that can appear within a room.
 */
public enum RoomTileType {
    /**
     * Empty floor that can be walked on
     */
    FLOOR,
    /**
     * Wall that cannot be walked on.  Mainly used for the border
     */
    WALL,
    /**
     * A wall where you can see the side
     */
    WALL_WITH_FACE,
    /**
     * A doorway
     */
    EXIT,
    /**
     * A treasure chest
     */
    CHEST,
    /**
     * A treasure chest that has been opened
     */
    CHEST_OPEN,
    /**
     * A potential battle
     */
    OPPONENT_SET,
}
