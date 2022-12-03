package com.mg105.utils;

/**
 * Constants used in the map and map generation.
 */
public class MapConstants {
    /** The width and height of a room in tiles, must be at leats 8 */
    public static final int ROOM_SIZE = 8;
    /** The size in pixels of a single tile on the screen */
    public static final int TILE_SIZE = 64;

    /** The minimum possible width of map rooms */
    public static final int MAPGEN_MIN_MAP_WIDTH  = 6;
    /** The maximum possible width of map rooms */
    public static final int MAPGEN_MAX_MAP_WIDTH  = 9;
    /** The minimum possible height of map rooms */
    public static final int MAPGEN_MIN_MAP_HEIGHT = 8;
    /** The maximum possible height of map rooms */
    public static final int MAPGEN_MAX_MAP_HEIGHT = 12;

    /** An integer between -1 and 100 inclusive that somewhat reflects how crowded rooms will be.
        Note: -1 represents a filled in grid, 100 represents an empty grid. */
    public static final int MAPGEN_ANCHOR_SPARSITY = 63;
    /** An integer between -1 and 100 inclusive that somewhat reflects how many battles will be generated.
        Note: -1 represents always generate battles and 100 represents never generate battles. */
    public static final int MAPGEN_BATTLE_SPARSITY = 75;
    /** An integer between -1 and 100 inclusive that somewhat reflects how may chests will be generated.
     Note: -1 represents always generate battles and 100 represents never generate battles. */
    public static final int MAPGEN_CHEST_SPARSITY = 75;
}
