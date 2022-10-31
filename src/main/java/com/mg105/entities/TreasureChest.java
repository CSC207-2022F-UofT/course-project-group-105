package com.mg105.entities;

public class TreasureChest {
    private Item reward;
    private RoomPosition position;
    private boolean opened;

    public TreasureChest(Item reward, RoomPosition position) {
        this.reward = reward;
        this.position = position;
        this.opened = false;
    }

    public RoomPosition getPosition() {
        return position;
    }

    public Item open() {
        assert !opened;
        return reward;
    }

    public boolean isOpened() {
        return opened;
    }
}
