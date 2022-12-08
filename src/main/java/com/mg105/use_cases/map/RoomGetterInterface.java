package com.mg105.use_cases.map;

import com.mg105.use_cases.outputds.RoomLayout;
import org.jetbrains.annotations.NotNull;

/**
 * Get attributes about the current room.
 */
public interface RoomGetterInterface {
    /**
     * Get the current room as a layout.
     *
     * @return the layout of the current room.
     */
    @NotNull RoomLayout getCurrentRoomLayout();

    /**
     * Get the current walking sprite.
     *
     * @return the path of the current walking sprite
     */
    @NotNull String getWalkingSprite();

    /**
     * Get whether the current room is the last room.
     *
     * @return if the current room is the last room.
     */
    boolean isFinalRoom();
}
