package com.mg105.entities;

import java.util.ArrayList;
import java.util.Collections;

public class Battle {
    //battlestatus: 0 for in progress, -1 for loss, 1 for win
    private int battleStatus;
    private ArrayList<BattleCharacter> opponents;
    private ArrayList<BattleCharacter> playerCharacters;
    private ArrayList<BattleCharacter> moveQueue = new ArrayList<BattleCharacter>();

    //not sure if numTokens should be randomly generated on creation, or after battleStatus has changed (this
    // implementation will assume the latter).
    private int numTokens;

    public Battle(ArrayList<BattleCharacter> opponents, ArrayList<BattleCharacter> party){
        this.battleStatus = 0;
        this.opponents = opponents;
        this.playerCharacters = party;
        this.numTokens = 0;

        this.moveQueue.addAll(this.opponents);
        this.moveQueue.addAll(playerCharacters);
        Collections.sort(moveQueue);
    }

    public int getBattleStatus() {
        return battleStatus;
    }

    public ArrayList<BattleCharacter> getOpponents() {
        return opponents;
    }

    public ArrayList<BattleCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public int getNumTokens() {
        return numTokens;
    }

    public void setNumTokens(int reward) {
        this.numTokens = reward;
    }

    public BattleCharacter getMovingCharacter(){
        BattleCharacter currentChar = this.moveQueue.get(this.moveQueue.size() - 1);
        this.moveQueue.remove(currentChar);
        this.moveQueue.add(0, currentChar);
        return currentChar;
    }

    public int removeChar(BattleCharacter fainted, boolean isOpponent){
        if (isOpponent){
            this.opponents.remove(fainted);
        } else{
            this.playerCharacters.remove(fainted);
        }
        this.moveQueue.remove(fainted);

        if(this.opponents.isEmpty()) {
            this.battleStatus = 1;
        } else if (this.playerCharacters.isEmpty()){
            this.battleStatus = -1;
        }

        return this.battleStatus;
    }
}
