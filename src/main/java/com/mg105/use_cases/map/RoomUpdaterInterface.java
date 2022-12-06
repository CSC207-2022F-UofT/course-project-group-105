package com.mg105.use_cases.map;

/**
 * RoomUpdaterInterface is the smallest possible interface to be used by anything that changes anything relating to the
 * current map.
 * <p>
 * Anything includes: changing position, opening a chest, ending a battle, changing rooms, etc.
 */
public interface RoomUpdaterInterface {
    /**
     * Notify all components when something has changed in the room.
     */
    void updateRoom();
}
