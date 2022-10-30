package com.mg105.entities;


public abstract class Item {
    final String name;
    final String description;

    /**
     * Item name and description should never be change after given initial values
     * Item name should be unique for each type of class that extends Item
     */
    public Item(String name, String description){

        this.name = name;
        this.description = description;
    }

    public String getItemName(){
        return this.name;
    }

    public String getItemDescription(){
        return this.description;
    }
}
