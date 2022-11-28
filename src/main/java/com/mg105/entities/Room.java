package com.mg105.entities;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Room {
    private final @NotNull List<TreasureChest> chests;
    private final @NotNull List<OpponentSet> opponents;
    private final @NotNull List<Doorway> doorways;

    /**
     * Create a room given some number of chests, opponents, and doorways.
     *
     * @param chests    the chests in this room.
     * @param opponents the opponents in this room.
     * @param doorways  the doorways in this room leading to other rooms.
     */
    public Room(@NotNull List<TreasureChest> chests, @NotNull List<OpponentSet> opponents, @NotNull List<Doorway> doorways) {
        this.chests = chests;
        this.opponents = opponents;
        this.doorways = doorways;
    }

    /**
     * Get the treasure chests in this room.
     *
     * @return the treasure chests in this room.
     */
    public List<TreasureChest> getChests() {
        return chests;
    }

    /**
     * Get the opponents in this room.
     *
     * @return the opponents in this room.
     */
    public List<OpponentSet> getOpponents() {
        return opponents;
    }

    /**
     * Get the doorways in this room.
     *
     * @return the doorways in this room.
     */
    public List<Doorway> getDoorways() {
        return doorways;
    }

}
