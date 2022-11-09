package com.mg105.entities;

public class GameState {

    public Room[][] map;
    public Inventory inventory;

    public WalkingCharacter walkingCharacter;

    public GameState(WalkingCharacter walkingCharacter) {
        //this.map = ????
        this.inventory = new Inventory();
        this.walkingCharacter = walkingCharacter;

    }

}
