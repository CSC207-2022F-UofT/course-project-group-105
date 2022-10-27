package com.mg105.entities;

public class BattleCharacter implements Comparable<BattleCharacter>{
    private int hp;
    private int dmg;
    private int speed;

    //Length of moves can be changed.
    private Move[] moves = new Move[2];

    //Don't know how to handle the sprite instance

    public BattleCharacter(int health, int attack, int speed, Move first, Move second){
        this.hp = health;
        this.dmg = attack;
        this.speed = speed;
        this.moves[0] = first;
        this.moves[1] = second;
    }

    public int getHp() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public Move getMoveOne() {
        return moves[0];
    }

    public Move getMoveTwo() {
        return moves[1];
    }

    public void modifyHealth(int healthChange){
        this.hp += healthChange;

        // If character faints, reset hp to 0
        if (this.hp <= 0){
            this.hp = 0;
        }
    }

    public void modifyDamage(int damageChange){
        this.dmg += damageChange;
    }

    @Override
    public int compareTo(BattleCharacter other){
        if (this.speed < other.speed){
            return -1;
        } else if (this.speed > other.speed){
            return 1;
        } else {
            return 0;
        }
    }
}
