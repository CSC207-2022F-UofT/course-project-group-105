package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * A Doorway represents a single-direction connection from one room to another.
 */
public class Doorway {
    private final @NotNull Point position;
    private final @NotNull Room nextRoom;

    /**
     * Create a doorway with its position in the room and the next room.
     *
     * @param position the in-room position of this doorway.
     * @param nextRoom the room that should be travelled to if this doorway is entered.
     */
    public Doorway(@NotNull Point position, @NotNull Room nextRoom) {
        this.position = position;
        this.nextRoom = nextRoom;
    }

    /**
     * Gets the position of the doorway.
     *
     * @return the position of the doorway.
     */
    public @NotNull Point getPosition() {
        return position;
    }

    /**
     * Gets the next room.
     *
     * @return the next room.
     */
    public @NotNull Room getNextRoom() {
        return nextRoom;
    }
}
