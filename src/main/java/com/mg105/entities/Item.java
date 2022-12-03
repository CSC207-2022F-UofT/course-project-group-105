package com.mg105.entities;

import org.jetbrains.annotations.NotNull;

/**
 * An item represents something that can be used in some manner to change something about the game environment
 * <p>
 * Items can be used on characters, change something about a battle, or alter something else about the game.
 */

public abstract class Item {
    final private String NAME;
    final private String DESCRIPTION;

    public Item(@NotNull String name, @NotNull String description) {

        this.NAME = name;
        this.DESCRIPTION = description;
    }

    /**
     * Returns the name of this item
     *
     * @return the name of this item
     */
    public @NotNull String getName() {
        return this.NAME;
    }
}
