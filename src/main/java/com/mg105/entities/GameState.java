package com.mg105.entities;

public class GameState {

    private Room[][] map;

    private BattleCharacter[] party;

    private Inventory inventory;

    public GameState(Inventory inventory, BattleCharacter[] party){
        this.inventory = inventory;
        this.party = party;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public BattleCharacter[] getParty() {
        return this.party;
    }
}
