package com.mg105.use_cases.outputds;

import org.jetbrains.annotations.NotNull;

/**
 * This is a struct like class whose sole purpose is to represent the state of a particular item in the inventory
 */
public class ItemDetails {
    private final int count;
    @NotNull
    private final String description;
    @NotNull
    private final String name;
    private final Boolean isUsable;

    /**
     * Create new ItemDetails for an item.
     *
     * @param name        the name of the item.
     * @param description the description of the item.
     * @param count       the count of the item.
     * @param isUsable    whether the item is usable.
     */
    public ItemDetails(@NotNull String name, @NotNull String description, int count, boolean isUsable) {
        this.name = name;
        this.description = description;
        this.count = count;
        this.isUsable = isUsable;
    }

    /**
     * The count of the item
     *
     * @return the count of the item
     */
    public int getCount() {
        return this.count;
    }

    /**
     * The description of the item
     *
     * @return the description of the item
     */
    @NotNull
    public String getDescription() {
        return this.description;
    }

    /**
     * The name of the item
     *
     * @return the name of the item
     */
    @NotNull
    public String getName() {
        return this.name;
    }

    /**
     * If the item is usable
     *
     * @return if the item is usable
     */
    @NotNull
    public Boolean isUsable() {
        return this.isUsable;
    }
}
