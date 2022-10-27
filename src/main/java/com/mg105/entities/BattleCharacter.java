package com.mg105.entities;


public class BattleCharacter implements Comparable<BattleCharacter>{
    private int maxHealth;
    private int currentHealth;

    private int attack;
    private int speed;

    private String name;
//    Need to build move class
//    private Moves[4];
//    private int sprite;

//    TODO: create Moves class and implement moves list (and its Methods) and implement character sprite

    public BattleCharacter(int maxHealth, int attack, int speed, String name){
        this.attack = attack;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.speed = speed;
        this.name = name;
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

    public String getName(){return this.name;}


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

    /**
     * Characters are compared by speed
     * */
    @Override
    public int compareTo(BattleCharacter o) {
        if(this.speed < o.speed){
            return -1;

        } else if(this.speed == o.speed){
            return 0;
        }

        return 1;
    }
}
