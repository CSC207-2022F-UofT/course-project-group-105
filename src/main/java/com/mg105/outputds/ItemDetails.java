package com.mg105.outputds;

public class ItemDetails {
    private final int count;
    private final String description;
    private final String name;
    private final Boolean isUsable;

    /**
     * This is a struct like class who's sole purpose is represent the state of a particular item in the inventory
     */
    public ItemDetails(String name, String description, int count, boolean isUsable) {
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
    public String getDescription() {
        return this.description;
    }

    /**
     * The name of the item
     *
     * @return the name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * If the item is usable
     *
     * @return if the item is usable
     */
    public Boolean isUsable() {
        return this.isUsable;
    }
}
