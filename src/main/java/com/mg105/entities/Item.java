package com.mg105.entities;
public abstract class Item {

    /**
     * An item represents something that can be used in some manner to change something about the game environment
     *
     * Items can be used on characters, change something about a battle, or alter something else about the game.
     *
     */
    final private String NAME;
    final private String DESCRIPTION;

    public Item(String name, String description) {

        this.NAME = name;
        this.DESCRIPTION = description;
    }

    /**
     * Returns the name of this item
     *
     * @return the name of this item
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Returns the description of this item
     *
     * @return the description of this item
     */

    public String getDescription() {
        return this.DESCRIPTION;
    }
}
