package com.mg105.entities;


public abstract class Item {
    final public String NAME;
    final public String DESCRIPTION;

    /**
     * Item name and description should never be change after given initial values
     * Item name should be unique for each type of class that extends Item
     */
    public Item(String name, String description) {

        this.NAME = name;
        this.DESCRIPTION = description;
    }
}
