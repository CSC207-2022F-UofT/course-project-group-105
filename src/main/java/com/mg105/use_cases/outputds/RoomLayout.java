package com.mg105.use_cases.outputds;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

/**
 * The layout of a room.
 * <p>
 * Note: There is an implicit, assumed, border around the edge.
 */
public class RoomLayout {
    private final @NotNull List<Point> closedChests;
    private final @NotNull List<Point> openChests;
    private final @NotNull List<Point> opponents;
    private final @NotNull List<Point> doorways;
    private final @NotNull Point player;

    /**
     * Create a room given some number of chests, opponents, and doorways.
     *
     * @param closedChests the closed chests in the room.
     * @param openChests   the open chests in the room.
     * @param opponents    the opponents in this room.
     * @param doorways     the doorways in this room leading to other rooms.
     * @param player       the player's position.
     */
    public RoomLayout(@NotNull List<Point> closedChests,
                      @NotNull List<Point> openChests,
                      @NotNull List<Point> opponents,
                      @NotNull List<Point> doorways,
                      @NotNull Point player) {
        this.closedChests = closedChests;
        this.openChests = openChests;
        this.opponents = opponents;
        this.doorways = doorways;
        this.player = player;
    }

    /**
     * Get the closed treasure chests in this room.
     *
     * @return the closed treasure chest positions in this room.
     */
    public @NotNull List<Point> getClosedChests() {
        return closedChests;
    }

    /**
     * Get the open treasure chests in this room.
     *
     * @return the open treasure chest positions in this room
     */
    public @NotNull List<Point> getOpenChests() {
        return openChests;
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
