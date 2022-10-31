package com.mg105.entities;


public abstract class Item {
    final private String NAME;
    final private String DESCRIPTION;


     // Item name and description should never be change after given initial values
     // Item name should be unique for each type of class that extends Item

    public Item(String name, String description) {

        this.NAME = name;
        this.DESCRIPTION = description;
    }

    public String getName() {
        return this.NAME;
    }

    public String getDescription() {
        return this.DESCRIPTION;
    }


}
