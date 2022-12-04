package com.mg105.interface_adapters;

/**
 * Types of tiles that can appear within a room.
 */
public enum TileType {
    /** Empty floor that can be walked on */
    FLOOR,
    /** Wall that cannot be walked on.  Mainly used for the border */
    WALL,
    WALL_WITH_FACE,
    /** A doorway */
    EXIT,
    /** A treasure chest */
    CHEST,
    /** A treasure chest that has been opened */
    CHEST_OPEN,
    /** A potential battle */
    OPPONENT_SET,
}
