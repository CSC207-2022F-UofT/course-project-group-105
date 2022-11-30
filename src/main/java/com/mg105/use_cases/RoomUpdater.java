package com.mg105.use_cases;

/**
 * RoomUpdater interface defines an object that needs to be told when the room updates.
 */
public interface RoomUpdater {
    /**
     * Update the current visual representation of the room.
     * <p>
     * It only needs to be called on changes to the underlying room.
     */
    void updateRoom();
}
