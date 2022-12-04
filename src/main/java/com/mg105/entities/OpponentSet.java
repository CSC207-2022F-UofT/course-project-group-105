package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

/**
 * OpponentSet stores a potential battle on a map.
 */
public class OpponentSet {
    private final @NotNull Point position;
    private final @NotNull List<BattleCharacter> opponents;

    /**
     * Create a new set of opponents to be displayed in a room.
     *
     * @param position  the in-room position of this OpponentSet.
     * @param opponents the opponents that will need to be fought.
     */
    public OpponentSet(@NotNull Point position, @NotNull List<BattleCharacter> opponents) {
        this.position = position;
        this.opponents = opponents;
    }

    /**
     * Get the position of the opponent set in the room.
     *
     * @return the position of the OpponentSet.
     */
    public @NotNull Point getPosition() {
        return position;
    }

    /**
     * Get the opponents needed to be fought.
     *
     * @return the opponents needed to be fought to complete this battle.
     */
    public @NotNull List<BattleCharacter> getOpponents() {
        return opponents;
    }
}
