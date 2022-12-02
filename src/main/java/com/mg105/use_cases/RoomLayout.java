package com.mg105.use_cases;

import org.jetbrains.annotations.NotNull;
import java.util.List;

import java.awt.*;

/**
 * The layout of a room.
 * <p>
 * Note: There is an implicit, assumed, border around the edge.
 */
public class RoomLayout {
    private final @NotNull List<Point> chests;
    private final @NotNull List<Point> opponents;
    private final @NotNull List<Point> doorways;
    private final @NotNull Point player;

    /**
     * Create a room given some number of chests, opponents, and doorways.
     *
     * @param chests    the chests in this room.
     * @param opponents the opponents in this room.
     * @param doorways  the doorways in this room leading to other rooms.
     * @param player    the player's position.
     */
    public RoomLayout(@NotNull List<Point> chests,
                      @NotNull List<Point> opponents,
                      @NotNull List<Point> doorways,
                      @NotNull Point player) {
        this.chests = chests;
        this.opponents = opponents;
        this.doorways = doorways;
        this.player = player;
    }

    /**
     * Get the treasure chests in this room.
     *
     * @return the treasure chest positions in this room.
     */
    public @NotNull List<Point> getChests() {
        return chests;
    }

    /**
     * Get the opponents in this room.
     *
     * @return the opponent positions in this room.
     */
    public @NotNull List<Point> getOpponents() {
        return opponents;
    }

    /**
     * Get the doorways in this room.
     *
     * @return the doorway positions in this room.
     */
    public @NotNull List<Point> getDoorways() {
        return doorways;
    }

    /**
     * Get the player position in this room.
     *
     * @return the player position in the room.
     */
    public @NotNull Point getPlayer() {
        return player;
    }
}
