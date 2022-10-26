package com.mg105.entities;


public class BattleCharacter{
    private int maxHealth;
    private int currentHealth;

    private int attack;
    private int speed;
//    Need to build move class
//    private Moves[4];
//    private int sprite;

//    TODO: create Moves class and implement moves list (and its Methods) and implement character sprite

    public BattleCharacter(int maxHealth, int attack, int speed){
        this.attack = attack;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.speed = speed;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public int getSpeed(){
        return this.speed;
    }


    public void increaseMaxHealth(){
        this.maxHealth++;
    }

    public void increaseAttack(){
        this.attack++;
    }

    public void increaseSpeed(){
        this.speed++;
    }

    public void decreaseCurrentHealth(int damage){

        this.currentHealth = Math.max(0, this.currentHealth - damage);
    }



}
