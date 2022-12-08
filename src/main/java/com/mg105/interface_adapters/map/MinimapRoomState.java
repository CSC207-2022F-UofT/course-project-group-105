package com.mg105.interface_adapters.map;

/**
 * Possible knowledge states of a room.
 * <p>
 * Note that we treat 'null' as a roomm state being unknown.
 */
public enum MinimapRoomState {
    /** The room is implied to exist but is unexplored. */
    UNEXPLORED,
    /** The room has been visited at least once */
    EXPLORED
}
