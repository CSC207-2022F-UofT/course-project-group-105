package com.mg105.entities;

import java.util.List;

public class OpponentSet {
    private final RoomPosition position;
    private final List<BattleCharacter> opponents;

    /**
     * Create a new set of opponents to be displayed in a room.
     *
     * TODO/NOTE: This does not currently include any logic for handling the removal once opponents are defeated.
     *
     * @param position  the in-room position of this OpponentSet.
     * @param opponents the opponents that will need to be fought.
     */
    public OpponentSet(RoomPosition position, List<BattleCharacter> opponents) {
        this.position = position;
        this.opponents = opponents;
    }

    /**
     * Get the position of the opponent set in the room.
     *
     * @return the position of the OpponentSet.
     */
    public RoomPosition getPosition() {
        return position;
    }

    /**
     * Get the opponents needed to be fought.
     *
     * @return the opponents needed to be fought to complete this battle.
     */
    public List<BattleCharacter> getOpponents() {
        return opponents;
    }
}
