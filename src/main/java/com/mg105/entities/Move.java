package com.mg105.entities;

public class Move {
    private final int healthDamage;
    private final int damageDamage;
    private final BattleCharacter caster;

    //target might be unnecessary depending on how the combat works
    private BattleCharacter target;

    public Move(int healthDamage, int damageDamage, BattleCharacter caster) {
        this.healthDamage = healthDamage;
        this.damageDamage = damageDamage;
        this.caster = caster;
        this.target = null;
    }

    public int getHealthDamage() {
        return this.healthDamage;
    }

    public int getDamageDamage() {
        return this.damageDamage;
    }

    public BattleCharacter getCaster() {
        return this.caster;
    }

    public BattleCharacter getTarget() {
        return this.target;
    }

    // Not sure how the target system will work, (might be unnecessary)
    public void setTarget(BattleCharacter target) {
        this.target = target;
    }
}
