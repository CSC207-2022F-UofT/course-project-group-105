package com.mg105.entities;

import java.awt.*;

public class TreasureChest {
    private final Item reward;
    private final Point position;
    private boolean opened;

    /**
     * Create a new treasure chest at a position with a certain reward.
     *
     * Note that none of these should be null.
     *
     * @param reward   the reward for opening the chest.
     * @param position the in-room position of the chest.
     */
    public TreasureChest(Item reward, Point position) {
        this.reward = reward;
        this.position = position;
        this.opened = false;
    }

    /**
     * Gets the position of the treasure chest.
     *
     * @return the position of the chest.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * If unopened, this will open the chest and return the reward.
     *
     * @return the reward of the chest.
     */
    public Item open() {
        assert !opened;
        opened = true;
        return reward;
    }

    /**
     * Gets if this chest has been opened.
     *
     * @return true if this chest has been opened, false otherwise.
     */
    public boolean isOpened() {
        return opened;
    }
}
