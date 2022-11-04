package com.mg105.entities;

public class Move {
    private final int healthDamage;
    private final int damageDamage;

    public Move(int healthDamage, int damageDamage) {
        this.healthDamage = healthDamage;
        this.damageDamage = damageDamage;
    }

    public int getHealthDamage() {
        return this.healthDamage;
    }

    public int getDamageDamage() {
        return this.damageDamage;
    }
}
