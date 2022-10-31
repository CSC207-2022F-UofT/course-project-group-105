package com.mg105.entities;

import java.util.List;

public class OpponentSet {
    private RoomPosition position;
    private List<BattleCharacter> opponents;

    public OpponentSet(RoomPosition position, List<BattleCharacter> opponents) {
        this.position = position;
        this.opponents = opponents;
    }

    public RoomPosition getPosition() {
        return position;
    }

    public List<BattleCharacter> getOpponents() {
        return opponents;
    }
}
