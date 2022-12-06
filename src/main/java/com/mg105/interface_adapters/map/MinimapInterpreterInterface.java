package com.mg105.interface_adapters.map;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * A class that handles the interpretation of the minimaps internal state.
 */
public interface MinimapInterpreterInterface {
    /**
     * Get the current state of the minimap.
     *
     * @return the current state of the minimap.
     *
     * @see MinimapInterpreterInterface#getCurrentPosition()
     */
    @NotNull MinimapRoomState[][] getMapSoFar();

    /**
     * Get the current room position
     *
     * @return the current room indexes in the result of getMapSoFar().
     *
     * @see MinimapInterpreterInterface#getMapSoFar()
     */
    @NotNull Point getCurrentPosition();
}
