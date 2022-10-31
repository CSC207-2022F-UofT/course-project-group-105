package com.mg105.entities;

import java.util.List;

public class Room {
    private List<TreasureChest> chests;
    private List<OpponentSet> opponents;
    private List<Doorway> doorways;

    public Room(List<TreasureChest> chests, List<OpponentSet> opponents, List<Doorway> doorways) {
        this.chests = chests;
        this.opponents = opponents;
        this.doorways = doorways;
    }

    public List<TreasureChest> getChests() {
        return chests;
    }

    public List<OpponentSet> getOpponents() {
        return opponents;
    }

    public List<Doorway> getDoorways() {
        return doorways;
    }
}
