package com.mg105.utils;

/**
 * Constants used in the map and map generation.
 */
public class MapConstants {
    /**
     * Whether you can walk through (and over) chests.
     * Note: do not commit this variable being equal to true.
     */
    public static final boolean NOCLIP = false;

    /**
     * The width and height of a room in tiles, must be at least 8
     */
    public static final int ROOM_SIZE = 8;
    /**
     * The size in pixels of a single tile on the screen
     */
    public static final int TILE_SIZE = 64;

    /**
     * The minimum possible width of map rooms
     */
    public static final int MAPGEN_MIN_MAP_WIDTH = 6;
    /**
     * The maximum possible width of map rooms
     */
    public static final int MAPGEN_MAX_MAP_WIDTH = 9;
    /**
     * The minimum possible height of map rooms
     */
    public static final int MAPGEN_MIN_MAP_HEIGHT = 8;
    /**
     * The maximum possible height of map rooms
     */
    public static final int MAPGEN_MAX_MAP_HEIGHT = 12;

    /**
     * An integer between -1 and 100 inclusive that somewhat reflects how crowded rooms will be.
     * Note: -1 represents a filled in grid, 100 represents an empty grid.
     */
    public static final int MAPGEN_ANCHOR_SPARSITY = 63;
    /**
     * An integer between -1 and 100 inclusive that somewhat reflects how many battles will be generated.
     * Note: -1 represents always generate battles and 100 represents never generate battles.
     */
    public static final int MAPGEN_BATTLE_SPARSITY = 51;
    /**
     * An integer between -1 and 100 inclusive that somewhat reflects how may chests will be generated.
     * Note: -1 represents always generate battles and 100 represents never generate battles.
     */
    public static final int MAPGEN_CHEST_SPARSITY = 67;

    /**
     * The upper bound on the healing effect of an opponent's friendly move.
     */
    public static final int OPPONENT_HEAL_CAP = 8;

    /**
     * The upper bound on the damage buffing effect of an opponent's friendly move.
     */
    public static final int OPPONENT_BUFF_CAP = 2;

    /**
     * The upper bound on the damaging effect of an opponent's unfriendly move.
     */
    public static final int OPPONENT_DAMAGE_CAP = 11;

    /**
     * The upper bound on the nullifying effect of an opponent's unfriendly move.
     */
    public static final int OPPONENT_NULLIFY_CAP = 0;

    /**
     * The lower bound on the opponent's health stat.
     */
    public static final int OPPONENT_HEALTH_MIN = 5;

    /**
     * The upper bound on the opponent's health stat.
     */
    public static final int OPPONENT_HEALTH_MAX = 41;

    /**
     * The lower bound on the opponent's damage stat.
     */
    public static final int OPPONENT_ATTACK_MIN = 1;

    /**
     * The upper bound on the opponent's damage stat.
     */
    public static final int OPPONENT_ATTACK_MAX = 11;

    /**
     * The lower bound on the opponent's speed stat.
     */
    public static final int OPPONENT_SPEED_MIN = 3;

    /**
     * The upper bound on the opponent's speed stat.
     */
    public static final int OPPONENT_SPEED_MAX = 16;
}
