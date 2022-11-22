package com.mg105.utils;

/**
 * Constants used in the map and map generation.
 */
public class MapConstants {
    // The width and height of each map room
    // Note: must be at least 8 (or map generation may break!)
    public static final int ROOM_SIZE = 8;
    // The size in pixels of a single tile on the screen
    public static final int TILE_SIZE = 64;

    // How large that map could possibly be if it were to be set on a grid.
    // Note: minimums are inclusive, maximums are exlusive.
    public static final int MAPGEN_MIN_MAP_WIDTH  = 6;
    public static final int MAPGEN_MAX_MAP_WIDTH  = 9;
    public static final int MAPGEN_MIN_MAP_HEIGHT = 8;
    public static final int MAPGEN_MAX_MAP_HEIGHT = 12;

    // An integer between -1 and 100 inclusive that somewhat reflects how crowded rooms will be.
    // Note: -1 represents a filled in grid, 100 represents an empty grid.
    public static final int MAPGEN_ANCHOR_SPARSITY = 63;
}
